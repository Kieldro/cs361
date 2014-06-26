package Security;

import java.io.*; 
import java.util.*; 
// import Security.SecurityLevel;

class CovertChannel{
    static SecureSystem sys;
    
    public static void main (String[] args) throws Exception{
        if(SecureSystem.DEBUG) System.out.println("args: " + args[0]);
        File inFile = new File(args[0]);
        sys = new SecureSystem(inFile);
        CovertChannel covert = new CovertChannel();
        covert.run();
    }
    
    // Methods
    public void run() throws Exception{
        // // create 2 subjects
        sys.createSubject("Lyle", SecurityLevel.LOW);
        sys.createSubject("Hal", SecurityLevel.HIGH);
        
        sys.runSystem();
    }
    
}