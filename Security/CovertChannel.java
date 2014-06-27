package Security;

import java.io.*;
import java.util.*;

import static Security.SecureSystem.*;

class CovertChannel{
    public static boolean logging = true;
    static SecureSystem sys;
    static PrintWriter fout;
    static PrintWriter log;
    
    public static void main (String[] args) throws Exception{
        String inFile;
        
        if(DEBUG) System.out.println("args: " + args[0]);
        if(DEBUG) System.out.println("length: " + args.length);
        if(args.length == 2 && args[0].equals("v")){
            inFile = args[1];
            logging = true;
        }else if(args.length == 1){
            inFile = args[0];
            logging = false;
        }else{
            System.out.println("Usage: java Security.CovertChannel [v] FILE");
            return;
        }
        
        fout = new PrintWriter(inFile + ".out");
        log = new PrintWriter("log");
        sys = new SecureSystem();
        CovertChannel covert = new CovertChannel();
        
        covert.run();
    }
    
    // Methods
    public void run() throws Exception{
        
        // // create 2 subjects
        sys.createSubject("Lyle", SecurityLevel.LOW);
        sys.createSubject("Hal", SecurityLevel.HIGH);
        
        // sys.runSystem();
        
        for(int i = 0; i < 3; ++i){
            String inString = "RUN HAL";
            // String inString = "CREATE HAL OBJ";
            // String inString = "CREATE LYLE OBJ";
            // String inString = "WRITE LYLE OBJ 1";
            // String inString = "READ LYLE OBJ";
            // String inString = "DESTROY LYLE OBJ";
            // String inString = "RUN LYLE";
            if(logging) log.println(inString);
            if(DEBUG) fout.println(inString);
            InstructionObject instruction = new InstructionObject(inString);
            sys.monitor.execute(instruction);
            sys.printState();
        }
        
        log.close();
        fout.close();
    }
    
}