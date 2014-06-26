package Security;

import java.io.*; 
import java.util.*; 
// import Security.SecurityLevel;

class CovertChannel{
    
    public static void main (String[] args) throws Exception{
        // if(DEBUG) System.out.println("args: " + args[1]);
        File inFile = new File(args[0]);
        SecureSystem sys = new SecureSystem();
        
        // create 2 subjects
        sys.createSubject("Lyle", SecurityLevel.LOW);
        sys.createSubject("Hal", SecurityLevel.HIGH);
        
        
    }
    
    // Methods
    
}