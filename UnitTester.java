import java.io.*;
import java.util.*;

class UnitTester{
    
    public static void main(String[] args) throws Exception{
        System.out.println("Running unit tests...");
        
        Encoder e = new Encoder();
        OutputStream out;
        String inFile = "binaryfile";
        
        FileOutputStream fout = new FileOutputStream(inFile + ".enc");
        e.dout = new DataOutputStream(fout);
        // e.writeBit((byte)0);
        // e.writeBit((byte)1);
        // e.binaryOut("100011");
        // e.writeBit((byte)0);
        // e.writeBit((byte)1);
        // e.writeBit((byte)1);
        // e.binaryOut("10011");
        // e.binaryOut("01111111");
        // e.binaryOut("10111111");
        
        // e.binaryOut("01011000");       //  'X'/0x58
        // e.binaryOut("01011001");       //  'Y'
        // e.binaryOut("10101011");       // 0xAB
        // e.binaryOut("11001101");       // 0xCD
        // e.binaryOut("00000000");
        e.binaryOut("11011110");
        e.binaryOut("11011110");
        e.binaryOut("11011110");
        e.binaryOut("11011110");
        // e.binaryOut("10001001");
        
        e.flush();
        
        e.sc = new Scanner(new File("frequenciesFile"));
        e.genCodes();
        
        
        FileInputStream fin = new FileInputStream(inFile + ".enc");
        e.din = new DataInputStream(fin);
        e.pout = new PrintWriter(inFile + ".dec");
        e.decode(1);
        
        System.out.println("Unit tests complete.");
    }
}