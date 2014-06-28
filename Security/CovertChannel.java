package Security;

import java.io.*;
import java.util.*;

import static Security.SecureSystem.*;

class CovertChannel{
    public static boolean logging = true;
    
    private static SecureSystem sys = new SecureSystem();
    private static FileInputStream fin;
    private static PrintWriter fout;
    private static PrintWriter log;
    private static final String[] instructionSet = 
        {
        //     "RUN HAL", 
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
        
        fin = new FileInputStream(new File(inFile));
        fout = new PrintWriter(inFile + ".out");
        log = new PrintWriter("log");
        CovertChannel covert = new CovertChannel();
        
        covert.run();
    }
    
    // Methods
    public void run() throws Exception{
        // create 2 subjects
        sys.createSubject("Lyle", SecurityLevel.LOW);
        sys.createSubject("Hal", SecurityLevel.HIGH);
        
        // input file byte by byte
        int nBytes = fin.available();
        byte[] buffer = new byte[nBytes];
        fin.read(buffer);
        
        for(byte B : buffer){
            if(DEBUG) System.out.println("B: 0b" + Integer.toBinaryString(B));
            for(int i = 0; i < 8; ++i){
                byte bit = (byte)(B >> i & 0x01);
                if(DEBUG) System.out.println("bit: 0x" + Integer.toHexString(bit));
                transmitBit(bit);
            }
            
        }
        
    }
    
    private void transmitBit(byte bit) throws Exception{
        for(int i = bit; i < instructionSet.length; ++i){
            String str = instructionSet[i];
            // if(DEBUG) System.out.println("str: " + str);
            if(logging) log.println(str);
            // TODO constant static instructions
            InstructionObject instruction = new InstructionObject(str);
            sys.monitor.execute(instruction);
            // fout.println(str);
            sys.printState();
        }
        
        log.close();
        fout.close();
    }
}