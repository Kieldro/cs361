import java.io.*;
import java.util.*;

class Encoder{
    static boolean DEBUG = true;
    static int k = 0;
    static HashMap<String, Double> probabilities = new HashMap();
    
    public static void main (String[] args) throws Exception{
        k = Integer.parseInt(args[1]);
        Scanner sc = new Scanner(new File(args[0]));
        if (DEBUG) System.out.println("k: " + k);
        
        // input frequencies
        int sum = 0, i = 0;
        HashMap<String, Integer> charFreqs = new HashMap();
        while( sc.hasNext() ){
            int x = sc.nextInt();
            if (DEBUG) System.out.println("x: " + x);
            charFreqs.put(String.valueOf((char)('a' + i++)), x);
            sum += x;
        }
        if (DEBUG) System.out.println("sum: " + sum);
        
        // calculate probabilities
        for(String key : charFreqs.keySet()){
            if (DEBUG) System.out.println("key: " + key);
            double x = charFreqs.get(key);
            probabilities.put(key, x / (double)sum);
            
        }
        if (DEBUG) System.out.println("probabilities: " + probabilities);
        
        // Entropy
        Encoder e = new Encoder();
        double h = e.computeEntropy();
        if (DEBUG) System.out.println("h: " + h + " bits");
        
        // Huffman Algorithm
        HuffmanTree tree = HuffmanCode.buildTree(charFreqs);
        HuffmanCode.printCodes(tree);
 
        // Encode and decode
        e.genText();        // generate text
        double efficiency = e.encode("testText.enc1", 1);
        e.decode("testText.dec1", 1);
        double percentDiff = 100 * efficiency / h - 100;
        System.out.printf("1 symbol encoding compared to entropy = %f%%\n", percentDiff);
        
        // 2 symbol alphabet
        // e.nSymbolAlpha(2);
        // efficiency = e.encode("testText.enc2", 2);
        // e.decode("testText.dec2", 2);
        // percentDiff = 100 * efficiency / h - 100;
        // System.out.printf("2 symbol encoding compared to entropy = %f%%\n", percentDiff);
    }
    
    // h = - Sigma(P * log P)
    double computeEntropy(){
    	double h = 0;
    	
        for(Double prob : probabilities.values()){
    	   h -= prob * Math.log(prob) / Math.log(2);
        }
        
    	return h;
    }
    
    void genText(){
        Random gen = new Random();
        if (DEBUG) gen = new Random(0);
        int rand = -1;
        int mark = -1;
        final int range = 1000;
        PrintWriter out;
        try{
            out = new PrintWriter("testText");
        }catch(Exception e){
            return;
        }
        
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
                    out.print(key);
                    break;
                }
            }
        }
        out.close();
    }
    
    //  encodes each character in testText to a single byte in testText.enc1
    double encode(String file, int jSymbols) throws Exception{
        FileInputStream fin = new FileInputStream("testText");
        DataInputStream din = new DataInputStream(fin);
        
        FileOutputStream fout = new FileOutputStream(file);
        DataOutputStream dout = new DataOutputStream(fout);
        double efficiency = 0;
        double totalBits = 0;
        double nSymbols = din.available();
        
        String str;
        if (DEBUG) System.out.println("din.available(): " + nSymbols);
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
            if (DEBUG) System.out.printf("codeByte: 0x%h\n", codeByte);
            dout.write((byte)codeByte);
            writeBits(codeByte);
            
        }
        efficiency = totalBits / nSymbols;
        if (DEBUG) System.out.println("efficiency: " + efficiency + " bits/symbol");
        dout.close();
        
        return efficiency;
    }
    
    //  encodes each character in testText to a single byte in testText.enc1
    void decode(String file, int jSymbols) throws Exception{
        if (DEBUG) System.out.println("Decoding... ");
        FileInputStream fin = new FileInputStream("testText" + ".enc1");
        DataInputStream din = new DataInputStream(fin);
        
        PrintWriter out = new PrintWriter(file);
        HashMap<String, String> decodings = new HashMap();
        
        // generate code to string map
        for(String key : HuffmanCode.encodings.keySet()){
            String s = HuffmanCode.encodings.get(key);
            decodings.put(s, key);
        }
        if (DEBUG) System.out.println("entrySet(): " + decodings.entrySet());
        
        char c = 0;
        if (DEBUG) System.out.println("din.available(): " + din.available());
        while(din.available() > 0)
        {
            c = (char)din.readByte();
            String code = Integer.toBinaryString(c);
            // if (DEBUG) System.out.println("code: " + code);
            String symbol = decodings.get(code);
            // if (DEBUG) System.out.println("symbol: " + symbol);
            out.print(symbol);
        }
        
        out.close();
    }
    
    void nSymbolAlpha(int n){
        HashMap<String, Double> probN = new HashMap();
        
        for(String c1 : probabilities.keySet())
            for(String c2 : probabilities.keySet()){
                double p1 = probabilities.get(c1);
                double p2 = probabilities.get(c2);
                probN.put(c1 + c2, p1 * p2);
                
            }
        // if (DEBUG) System.out.println("probN: " + probN);
        
        
        HuffmanTree tree = HuffmanCode.buildTree(probN, 0);
        HuffmanCode.printCodes(tree);
        
        
    }
    
    private ByteArrayInputStream byteStream = new ByteArrayInputStream(new byte[8]);
    private void writeBits(int bits){
        if(bits == 0){
            // write 0;
            return;
        }
        
        // shift first 1 bit to the MSB
        int places = 31 - (int)log2(bits);
        bits <<= places;
        if (DEBUG) System.out.printf("places: %d\n", places);
        if (DEBUG) System.out.printf("bits: 0x%H\n", bits);
    }
    
    private double log2(double x){
        return Math.log(x) / Math.log(2);
    }
    private void flush(){
        
        
    }
}