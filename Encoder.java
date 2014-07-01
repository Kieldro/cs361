import java.io.*;
import java.util.*;
// import static java.lang.Math.*;

class Encoder{
    static boolean DEBUG = true;
    static HashMap<Character, Double> probabilities = new HashMap();
    static LinkedList<Double> list = new LinkedList();
    static int k = 0;
    
    public static void main (String[] args) throws Exception{
        k = Integer.parseInt(args[1]);
        Scanner sc = new Scanner(new File(args[0]));
        
        if (DEBUG) System.out.println("k: " + k);
        
        
        // input
        int sum = 0;
        int[] charFreqs = new int[256];
        while( sc.hasNext() ){
            double x = sc.nextInt();
            list.add(x);
            sum += x;
            if (DEBUG) System.out.println("x: " + x);
        }
        if (DEBUG) System.out.println("sum: " + sum);
        
        int i = 0;
        for(ListIterator<Double> it = list.listIterator(); it.hasNext(); ){
            double x = it.next();
            charFreqs[i++ + 'a'] = (int)x;
            it.set(x / (double)sum);
        }
        if (DEBUG) System.out.println("list: " + list);
        
        
        // for(int i = 0; i < 10; ++i){
        //  System.out.println("random: " + gen.nextInt());
        // }
        
        Encoder e = new Encoder();
        
        // Entropy
        double h = e.computeEntropy();
        if (DEBUG) System.out.println("h: " + h + " bits");
        
        // Huffman Algorithm
        HuffmanTree tree = HuffmanCode.buildTree(charFreqs);
 
        // print out results
        System.out.println("SYMBOL\tWEIGHT\tHUFFMAN CODE");
        HuffmanCode.printCodes(tree, new StringBuffer());
        
        // generate text
        e.genText();
        
        e.encode();
        e.decode();
        
    }
    
    // h = - Sigma(P * log P)
    double computeEntropy(){
    	double h = 0;
    	probabilities.put('a', .40);
    	probabilities.put('b', .30);
    	probabilities.put('c', .20);
    	probabilities.put('d', .10);
    	
        for(Double prob : list){
    	   h -= prob * Math.log(prob) / Math.log(2);
        }
    	
    	// h *= -1;
        
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
            
            for(int j = 0; j < list.size(); ++j){
                double p = list.get(j);
                int pMark = (int)(p * range);
                mark += pMark;
                // if (DEBUG) System.out.println("p: " + p);
                if(rand <= mark){
                // if (DEBUG) System.out.println("BOOYA: " + p);
                    out.print((char)('a' + j));
                    break;
                }
                
            }
            
        }
        out.close();
    }
    
    //  encodes each character in testText to a single byte in testText.enc1
    void encode() throws Exception{
        FileInputStream fin = new FileInputStream("testText");
        DataInputStream din = new DataInputStream(fin);
        
        FileOutputStream fout = new FileOutputStream("testText" + ".enc1");
        DataOutputStream dout = new DataOutputStream(fout);
        
        
        char c = 0;
        if (DEBUG) System.out.println("din.available(): " + din.available());
        while(din.available() > 0)
        {
            c = (char)din.readByte();
            // if (DEBUG) System.out.println("c: " + c);
            String code = HuffmanCode.encodings.get(c);
            // if (DEBUG) System.out.println("encodings: " + code);
            byte codeByte = (byte)Integer.parseInt(HuffmanCode.encodings.get(c), 2);
            // if (DEBUG) System.out.println("encodings: " + codeByte);
            dout.write(codeByte);
            
        }
        
        dout.close();
    }
    
    //  encodes each character in testText to a single byte in testText.enc1
    void decode() throws Exception{
        // FileInputStream fin = new FileInputStream("testText");
        // DataInputStream din = new DataInputStream(fin);
        
        // FileOutputStream fout = new FileOutputStream("testText" + ".enc1");
        // DataOutputStream dout = new DataOutputStream(fout);
        
        // char c = 0;
        // if (DEBUG) System.out.println("din.available(): " + din.available());
        // while(din.available() > 0)
        // {
        //     c = (char)din.readByte();
        //     if (DEBUG) System.out.println("c: " + c);
        //     String code = HuffmanCode.encodings.get(c);
        //     if (DEBUG) System.out.println("encodings: " + code);
        //     byte codeByte = (byte)Integer.parseInt(HuffmanCode.encodings.get(c), 2);
        //     if (DEBUG) System.out.println("encodings: " + codeByte);
        //     dout.write(codeByte);
            
        // }
        
        // dout.close();
    }
}