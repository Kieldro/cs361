import java.io.*;
import java.util.*;
// import static java.lang.Math.*;

class Encoder{
    static boolean DEBUG = true;
    static HashMap<Character, Double> probabilities = new HashMap();
    static LinkedList<Double> list = new LinkedList();
    
    public static void main (String[] args) throws Exception{
        Random gen = new Random();
        int k = Integer.parseInt(args[1]);
        Scanner sc = new Scanner(new File(args[0]));
        
        if (DEBUG) System.out.println("k: " + k);
        
        int sum = 0;
        while( sc.hasNext() ){
            double x = sc.nextInt();
            list.push(x);
            sum += x;
            if (DEBUG) System.out.println("x: " + x);
        }
        if (DEBUG) System.out.println("sum: " + sum);
        
        for(ListIterator<Double> it = list.listIterator(); it.hasNext(); ){
            double x = it.next();
            it.set(x / (double)sum);
        }
        if (DEBUG) System.out.println("list: " + list);
        
        
        for(int i = 0; i < 10; ++i){
        	System.out.println("random: " + gen.nextInt());
        }
        
        Encoder e = new Encoder();
        double h = e.computeEntropy();
        if (DEBUG) System.out.println("h: " + h + " bits");
        
        
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
    
}