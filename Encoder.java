import java.io.*;
import java.util.*;

class Encoder{
    static boolean DEBUG = true;
    HashMap<Character, Double> probabilities = new HashMap();
    
    public static void main (String[] args){
        Random gen = new Random();
        int k = Integer.parseInt(args[1]);

        if (DEBUG) System.out.println("k: " + k);
        
        for(int i = 0; i < 10; ++i){
        	System.out.println("random: " + gen.nextInt());
        }
        Encoder e = new Encoder();
        e.computeEntropy();
    }
    
    // h = Sigma(P * log P)
    double computeEntropy(){
    	double h = 3;
    	probabilities.put('a', 4.0);
    	probabilities.put('b', 3.0);
    	probabilities.put('c', 2.0);
    	probabilities.put('d', 1.0);
    	
    	h = 0;
    	
    	
    	return h;
    }
    
}