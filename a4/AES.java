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
        // Scanner sc = new Scanner(plainFile);
        // sc.next
        byte[] B = new byte[2];
        for (int j = 0; j < 4; ++j) {
            for (int i = 0; i < 4; ++i) {
                fin.read(B);
                String hexStr = String.valueOf((char)B[0]);
                hexStr += String.valueOf((char)B[1]);
                if(DEBUG) System.out.printf("hexStr = %s\n", hexStr);
                state[i][j] = (byte)Integer.parseInt(hexStr, 16);
                if(DEBUG) System.out.printf("state[i][j] = %X\n", state[i][j]);
            }
        }
        // if(DEBUG) System.out.printf("state = %s\n", state[i][j]);
        System.out.println("state: " + state);
        printMatrix(state);
        
        
        
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
    
    static void printMatrix(byte[][] A){
        
        
        for (int i = 0; i < 4; ++i) {
            System.out.printf("[ ");
            
            for (int j = 0; j < 4; ++j) {
                
                System.out.printf("%02X ", A[i][j]);
            }
            System.out.printf("]\n");
        }
        
    }
}