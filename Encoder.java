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
        double efficiency = e.encode();
        e.decode();
        double percentDiff = 100 * efficiency / h - 100;
        System.out.printf("percent compared to entropy = %f%%\n", percentDiff);
        
        // 2 symbol alphabet
        e.nSymbolAlpha(2);
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
    double encode() throws Exception{
        FileInputStream fin = new FileInputStream("testText");
        DataInputStream din = new DataInputStream(fin);
        
        FileOutputStream fout = new FileOutputStream("testText" + ".enc1");
        DataOutputStream dout = new DataOutputStream(fout);
        double efficiency = 0;
        double totalBits = 0;
        double nSymbols = din.available();
        
        
        String c;
        if (DEBUG) System.out.println("din.available(): " + nSymbols);
        while(din.available() > 0)
        {
            c = String.valueOf((char)din.readByte());
            // if (DEBUG) System.out.println("c: " + c);
            String code = HuffmanCode.encodings.get(c);
            totalBits += code.length();
            // if (DEBUG) System.out.println("encodings: " + code);
            byte codeByte = (byte)Integer.parseInt(HuffmanCode.encodings.get(c), 2);
            // if (DEBUG) System.out.println("encodings: " + codeByte);
            dout.write(codeByte);
            
        }
        efficiency = totalBits / nSymbols;
        if (DEBUG) System.out.println("efficiency: " + efficiency + " bits/symbol");
        dout.close();
        
        return efficiency;
    }
    
    //  encodes each character in testText to a single byte in testText.enc1
    void decode() throws Exception{
        if (DEBUG) System.out.println("Decoding... ");
        FileInputStream fin = new FileInputStream("testText" + ".enc1");
        DataInputStream din = new DataInputStream(fin);
        
        PrintWriter out = new PrintWriter("testText" + ".dec1");
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
        
        
        
        
    }
}