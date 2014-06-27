package Security;

import java.io.*;
import java.util.*;

import static Security.SecureSystem.*;

class CovertChannel{
    public static boolean logging = true;
    static SecureSystem sys;
    static PrintWriter fout;
    static PrintWriter log;
    static final String[] instructionSet = 
        {"RUN HAL", 
        "CREATE HAL  OBJ", 
        "CREATE LYLE OBJ", 
        "WRITE LYLE OBJ 1", 
        "READ  LYLE OBJ", 
        "DESTROY LYLE OBJ", 
        "RUN LYLE"};
    
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
        // create 2 subjects
        sys.createSubject("Lyle", SecurityLevel.LOW);
        sys.createSubject("Hal", SecurityLevel.HIGH);
        
        for(String str : instructionSet){
            // if(DEBUG) System.out.println("str: " + str);
            if(logging) log.println(str);
            InstructionObject instruction = new InstructionObject(str);
            sys.monitor.execute(instruction);
            // fout.println(str);
            sys.printState();
        }
        
        log.close();
        fout.close();
    }
}