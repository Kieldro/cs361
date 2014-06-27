package Security;

import java.io.*; 
import java.util.*; 

class CovertChannel{
    public static boolean logging = true;
    static SecureSystem sys;
    static PrintWriter out;
    static PrintWriter log;
    
    public static void main (String[] args) throws Exception{
        if(SecureSystem.DEBUG) System.out.println("args: " + args[0]);
        File inFile = new File(args[0]);
        logging = true;
        out = new PrintWriter(args[0] + ".out");
        log = new PrintWriter("log");
        
        sys = new SecureSystem(inFile);
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
            if(SecureSystem.DEBUG) out.println(inString);
            InstructionObject instruction = new InstructionObject(inString);
            sys.monitor.execute(instruction);
            sys.printState();
        }
        
        log.close();
        out.close();
    }
    
}