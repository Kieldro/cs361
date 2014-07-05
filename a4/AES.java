import java.io.*;
import java.util.*;

class AES{
    static boolean DEBUG = true;
    
    public static void main(String[] args) throws Exception{
        String option = args[0].toLowerCase();
        String keyFile = args[1];       // 256 bits
        String plainFile = args[2];
        // if(DEBUG) System.out.printf("option = %s\n", option == "e");
        
        if (option.equals("e")){
            System.out.println("\nEncrypting...");
            ;      // encrpyt
        }else if (option.equals("d")){
            System.out.println("\nDecrypting...");
            ;       // decrypt
        }else{
            System.out.println("Usage: java -ea AES e|d key plaintext");
            return;
        }
        
        byte[][] state = new byte[4][4];        // 128 bit block
        byte[][] key = new byte[4][8];        // 256 bit key
        
        
        // input
        //If any line contains non-Hex characters, skip it. 
        //If any line is less than 32 Hex characters (128-bits) pad on the right with zeros. 
        //Your program should be able to deal with uppercase, lowercase or mixed input.
        AES aes = new AES();
        
        // aes.input();
        FileInputStream fin;
        try{
            fin = new FileInputStream(plainFile);
        }catch(Exception e){
            System.out.printf("plainFile: %s not found\n", plainFile);
            return;
        }
        char c = (char)fin.read();
        c = (char)fin.read();
        c = (char)fin.read();
        c = (char)fin.read();
        if(DEBUG) System.out.printf("c = %s\n", c);
        // System.out.println("Plaintext: " + fin.next());
        
        
        
        System.out.println("Plaintext: ");
        
        
        System.out.println("CipherKey: ");
        
        
        System.out.println("Expanded key: ");
        
        
        
        
        
        
        // key expansion
        
        
        // 14 cycles for 256-bit key
        
        // subBytes
        
        
        
        // shiftRows
        
        
        
        
        // mixColumns
        
        
        
        // addRoundkey
        
        
        System.out.println("Ciphertext: ");
        
        
    }
}