import java.io.*;
import java.util.*;

class Encoder{
    static boolean DEBUG = true;
    static int k = 0;
    static HashMap<String, Double> probabilities = new HashMap();
    
    // IO variables
    static final String generatedText = "testText";
    
    static Scanner sc;
    static DataInputStream din;
    static DataOutputStream dout;
    static PrintWriter pout;
    
    static File outFile;
    static FileOutputStream fout;
    
    public static void main (String[] args) throws Exception{
        k = Integer.parseInt(args[1]);
        String freqFile = args[0];
        if (DEBUG) System.out.println("freqFile: " + freqFile);
        sc = new Scanner(new File(freqFile));
        // if (DEBUG) System.out.println("k: " + k);
        Encoder e = new Encoder();
        
        // generate codes
        e.genCodes();
        
        // Entropy
        double h = e.computeEntropy();
        if (DEBUG) System.out.println("h: " + h + " bits");
        
        // Generate text
        pout = new PrintWriter(generatedText);
        e.genText();        // generate text
        
        // Encode
        FileInputStream fin = new FileInputStream(generatedText);
        din = new DataInputStream(fin);
        FileOutputStream fout = new FileOutputStream(generatedText + ".enc1");
        dout = new DataOutputStream(fout);
        double efficiency = e.encode(1);
        
        // Decode
        fin = new FileInputStream(generatedText + ".enc1");
        din = new DataInputStream(fin);
        pout = new PrintWriter(generatedText + ".dec1");
        e.decode(1);
        
        double percentDiff = 100 * efficiency / h - 100;
        System.out.printf("1 symbol encoding compared to entropy = %f%%\n", percentDiff);
        
        // 2 symbol alphabet
        // e.nSymbolAlpha(2);
        
        // efficiency = e.encode("testText.enc2", 2);
        // e.decode("testText.dec2", 2);
        // percentDiff = 100 * efficiency / h - 100;
        // System.out.printf("2 symbol encoding compared to entropy = %f%%\n", percentDiff);
    }
    
    void genCodes(){
        // input frequencies
        int sum = 0, i = 0;
        HashMap<String, Integer> charFreqs = new HashMap();
        while( sc.hasNext() ){
            int x = sc.nextInt();
            // if (DEBUG) System.out.println("x: " + x);
            charFreqs.put(String.valueOf((char)('a' + i++)), x);
            sum += x;
        }
        // if (DEBUG) System.out.println("sum: " + sum);
        
        // calculate probabilities
        for(String key : charFreqs.keySet() ){
            // if (DEBUG) System.out.println("key: " + key);
            double x = charFreqs.get(key);
            probabilities.put(key, x / (double)sum);
            
        }
        // if (DEBUG) System.out.println("probabilities: " + probabilities);
        
        // Huffman Algorithm
        HuffmanTree tree = HuffmanCode.buildTree(charFreqs);
        HuffmanCode.printCodes(tree);
    }
    
    // h = - Sigma(P * log P)
    double computeEntropy(){
    	double h = 0;
    	
        for(Double prob : probabilities.values()){
    	   h -= prob *log2(prob);
        }
        
    	return h;
    }
    
    protected double log2(double x){ return Math.log(x) / Math.log(2); }
    
    void genText(){
        Random gen = new Random();
        if (DEBUG) gen = new Random(0);
        int rand = -1;
        int mark = -1;
        final int range = 1000;
        
        for (int i = 0; i < k; ++i){
            rand = Math.abs(gen.nextInt() % range);
            mark = 0;
            // if (DEBUG) System.out.println("rand: " + rand);
            
            for(String key : probabilities.keySet()){
                double p = probabilities.get(key);
                int pMark = (int)(p * range);
                mark += pMark;
                // if (DEBUG) System.out.println("p: " + p);
                if(rand <= mark){
                    // if (DEBUG) System.out.println("key: " + key);
                    pout.print(key);
                    break;
                }
            }
        }
        pout.close();
    }
    
    //  encodes each character in testText into bits in testText.enc1
    double encode(int jSymbols) throws Exception{
        double efficiency = 0;
        double totalBits = 0;
        double nSymbols = din.available();
        
        String str;
        // if (DEBUG) System.out.println("din.available(): " + nSymbols);
        while(din.available() >= jSymbols)
        {
            str = "";
            for(int i = 0; i < jSymbols; ++i){
                String c = String.valueOf((char)din.readByte());
                str += c;
            }
            // if (DEBUG) System.out.println("c: " + c);
            String code = HuffmanCode.encodings.get(str);
            totalBits += code.length();
            // if (DEBUG) System.out.println("encodings: " + code);
            int codeByte = Integer.parseInt(HuffmanCode.encodings.get(str), 2);
            String codeStr = HuffmanCode.encodings.get(str);
            // if (DEBUG) System.out.printf("\ncodeStr: 0b%s\n", codeStr);
            // if (DEBUG) System.out.printf("codeByte: 0x%X\n", codeByte);
            // dout.write((byte)codeByte);         // seperate bytes
            binaryOut(codeStr);
        }
        efficiency = totalBits / nSymbols;
        if (DEBUG) System.out.println("efficiency: " + efficiency + " bits/symbol");
        flush();
        dout.close();
        
        return efficiency;
    }
    
    protected void binaryOut(String codeStr) throws Exception{
        for(char c : codeStr.toCharArray()){
            byte bit = (byte)(c - '0');
            // if (DEBUG) System.out.printf("bit: %d\n", bit);
            writeBit(bit);
            
        }
    }
    
    protected final int capOfBuff = 4;
    protected byte[] buffer = new byte[capOfBuff];
    protected int mark = 0;
    protected int positionInByte = 7;
        
    public void writeBit(byte bit) throws Exception{    
        if(positionInByte == 7){
            buffer[mark] = 0;
            assert buffer[mark] == 0 : 
            String.format("buffer[mark] must be 0: 0x%02X", buffer[mark]);       // initialized to zero
        }
        
        // if (DEBUG) System.out.printf("positionInByte: %d\n", positionInByte);
        // if (DEBUG) System.out.printf("mark: %d\n", mark);
        buffer[mark] |= bit << positionInByte;      // place bit in position
            // Integer.toBinaryString(buffer[mark]));
        --positionInByte;   // next bit positon in byte
        if(positionInByte <= -1){
            if (DEBUG) System.out.printf("buffer[mark]: 0x%02X\n", buffer[mark]);
            // if (DEBUG) System.out.printf("buffer[mark]: 0b%s\n", 
            ++mark;     // next byte in buffer
            positionInByte = 7;
        }
        
        if(mark >= capOfBuff)       // full buffer
            flush();
    }
    
    static HashMap<String, String> decodings = new HashMap();
    // encodes each character in testText to a single byte in testText.enc1
    void decode(int jSymbols) throws Exception{
        if (DEBUG) System.out.println("Decoding... ");
        
        // generate code to string map
        for(String key : HuffmanCode.encodings.keySet()){
            String s = HuffmanCode.encodings.get(key);
            decodings.put(s, key);
        }
        if (DEBUG) System.out.println("entrySet(): " + decodings.entrySet());
        
        String codeStr = "";
        mark = 0;
        int i = 0;
        
        // if (DEBUG) System.out.println("din.available(): " + din.available());
        while(din.available() > 0){
            byte B = din.readByte();
            for(i = 0; i < 8; ++i)
            {
                byte bit = (byte)(B & 0x80 >> i);
                
                codeStr += bit != 0 ? "1" : "0";
                assert codeStr.length() < 16 : "Code too long/ not found: " + codeStr;
                if (DEBUG) System.out.println("codeStr: " + codeStr);
                String symbol = decodings.get(codeStr);
                if(symbol != null){
                    if (DEBUG) System.out.println("symbol: " + symbol);
                    pout.print(symbol);
                    codeStr = "";
                }
            }
            // ++mark;
        }
        pout.close();
    }
        
    void nSymbolAlpha(int n) throws Exception{
        HashMap<String, Double> probN = new HashMap();
        outFile = new File(generatedText + ".dec2");
        fout = new FileOutputStream(outFile);
        
        for(String c1 : probabilities.keySet())
            for(String c2 : probabilities.keySet()){
                double p1 = probabilities.get(c1);
                double p2 = probabilities.get(c2);
                probN.put(c1 + c2, p1 * p2);
                
            }
        // if (DEBUG) System.out.println("probN: " + probN);
        
        HuffmanTree tree = HuffmanCode.buildTree(probN, 0);
        HuffmanCode.printCodes(tree);
        
        encode(2);
        
        // flush();
    }
    
    // writes buffer to file and resets counters
    protected void flush() throws Exception{
        if (DEBUG) System.out.println("flushing: ");
        if(mark == 0 && positionInByte == 7)        // buffer already flushed
            return;
        assert dout != null : "dout is null";
        dout.write(buffer);
        mark = 0;
        // positionInByte = 0;
    }
}