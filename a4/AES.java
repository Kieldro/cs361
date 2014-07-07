import java.io.*;
import java.util.*;

class AES{
    static boolean DEBUG = true;
    static final int Nr = 14;       // rounds
    static final int Nk = 8;       // words in cipher key
    static byte[][] sbox = new byte[16][16];
    static byte[][] MDS = new byte[][]
    {
        {2, 3, 1, 1}, 
        {1, 2, 3, 1}, 
        {1, 1, 2, 3}, 
        {3, 1, 1, 2}
    };
    
    // static initializer
    static{
        int[] sboxarray = new int []
        {
           0x63, 0x7C, 0x77, 0x7B, 0xF2, 0x6B, 0x6F, 0xC5, 0x30, 0x01, 0x67, 0x2B, 0xFE, 0xD7, 0xAB, 0x76,
           0xCA, 0x82, 0xC9, 0x7D, 0xFA, 0x59, 0x47, 0xF0, 0xAD, 0xD4, 0xA2, 0xAF, 0x9C, 0xA4, 0x72, 0xC0,
           0xB7, 0xFD, 0x93, 0x26, 0x36, 0x3F, 0xF7, 0xCC, 0x34, 0xA5, 0xE5, 0xF1, 0x71, 0xD8, 0x31, 0x15,
           0x04, 0xC7, 0x23, 0xC3, 0x18, 0x96, 0x05, 0x9A, 0x07, 0x12, 0x80, 0xE2, 0xEB, 0x27, 0xB2, 0x75,
           0x09, 0x83, 0x2C, 0x1A, 0x1B, 0x6E, 0x5A, 0xA0, 0x52, 0x3B, 0xD6, 0xB3, 0x29, 0xE3, 0x2F, 0x84,
           0x53, 0xD1, 0x00, 0xED, 0x20, 0xFC, 0xB1, 0x5B, 0x6A, 0xCB, 0xBE, 0x39, 0x4A, 0x4C, 0x58, 0xCF,
           0xD0, 0xEF, 0xAA, 0xFB, 0x43, 0x4D, 0x33, 0x85, 0x45, 0xF9, 0x02, 0x7F, 0x50, 0x3C, 0x9F, 0xA8,
           0x51, 0xA3, 0x40, 0x8F, 0x92, 0x9D, 0x38, 0xF5, 0xBC, 0xB6, 0xDA, 0x21, 0x10, 0xFF, 0xF3, 0xD2,
           0xCD, 0x0C, 0x13, 0xEC, 0x5F, 0x97, 0x44, 0x17, 0xC4, 0xA7, 0x7E, 0x3D, 0x64, 0x5D, 0x19, 0x73,
           0x60, 0x81, 0x4F, 0xDC, 0x22, 0x2A, 0x90, 0x88, 0x46, 0xEE, 0xB8, 0x14, 0xDE, 0x5E, 0x0B, 0xDB,
           0xE0, 0x32, 0x3A, 0x0A, 0x49, 0x06, 0x24, 0x5C, 0xC2, 0xD3, 0xAC, 0x62, 0x91, 0x95, 0xE4, 0x79,
           0xE7, 0xC8, 0x37, 0x6D, 0x8D, 0xD5, 0x4E, 0xA9, 0x6C, 0x56, 0xF4, 0xEA, 0x65, 0x7A, 0xAE, 0x08,
           0xBA, 0x78, 0x25, 0x2E, 0x1C, 0xA6, 0xB4, 0xC6, 0xE8, 0xDD, 0x74, 0x1F, 0x4B, 0xBD, 0x8B, 0x8A,
           0x70, 0x3E, 0xB5, 0x66, 0x48, 0x03, 0xF6, 0x0E, 0x61, 0x35, 0x57, 0xB9, 0x86, 0xC1, 0x1D, 0x9E,
           0xE1, 0xF8, 0x98, 0x11, 0x69, 0xD9, 0x8E, 0x94, 0x9B, 0x1E, 0x87, 0xE9, 0xCE, 0x55, 0x28, 0xDF,
           0x8C, 0xA1, 0x89, 0x0D, 0xBF, 0xE6, 0x42, 0x68, 0x41, 0x99, 0x2D, 0x0F, 0xB0, 0x54, 0xBB, 0x16
        };
        
        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                sbox[i][j] = (byte)sboxarray[i*16 + j];
            }
        }
    }
    
    public static void main(String[] args) throws Exception{
        String option = args[0].toLowerCase();
        String keyFile = args[1];       // 256 bits
        String plainFile = args[2];
        // if(DEBUG) System.out.printf("option = %s\n", option == "e");
        byte[][] state = new byte[4][4];        // 128 bit block
        byte[][] key = new byte[4][Nk];        // 256 bit key
        byte[][][] roundKeys;
        AES aes = new AES();
        
        // System.out.println("S-box: ");
        // aes.printMatrix(sbox);
        
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
        
        // input
        //If any line contains non-Hex characters, skip it. 
        //If any line is less than 32 Hex characters (128-bits) pad on the right with zeros. 
        //Your program should be able to deal with uppercase, lowercase or mixed input.
        // TODO multiple lines of plaintext input
        
        aes.inputMatrix(key, keyFile);
        System.out.println("CipherKey: ");
        aes.printMatrix(key);
        
        aes.inputMatrix(state, plainFile);
        System.out.println("Plaintext(state): ");
        aes.printMatrix(state);
        
        // key expansion
        byte[][] keySchedule = aes.keyExpansion(key);
        System.out.println("Expanded key: ");
        aes.printMatrix(keySchedule);
        
        // initial addRoundkey
        roundKeys = aes.splitIntoRoundKeys(keySchedule);
        state = aes.addRoundkey(state, roundKeys[0]);
        System.out.println("addRoundkey state: ");
        aes.printMatrix(state);
        
        // 14 cycles for 256-bit key
        for (int r = 1; r < Nr + 1; ++r) 
        {
            System.out.printf("ROUND %d: \n", r);
            // subBytes
            state = aes.subBytes(state);
            System.out.println("subBytes state: ");
            aes.printMatrix(state);
            
            // shiftRows
            state = aes.shiftRows(state);
            System.out.println("shiftRows state: ");
            aes.printMatrix(state);
            
            // mixColumns
            if(r != Nr)
            {
                state = aes.mixColumns(state);
                System.out.println("mixColumns state: ");
                aes.printMatrix(state); 
            }

            if(DEBUG) break;
            // addRoundkey
            state = aes.addRoundkey(state, roundKeys[r]);
            System.out.println("addRoundkey state: ");
            aes.printMatrix(state);
        }

        System.out.println("Ciphertext: ");
        aes.printMatrix(state);
    }
    
    byte[][] subBytes(byte[][] A){
        final int m = A.length;
        final int n = A[0].length;
        
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m; ++i) {
                A[i][j] = SubByte(A[i][j]);
            }
        }
        return A;
    }
    
    byte[][] shiftRows(byte[][] A){
        final int m = A.length;
        final int n = A[0].length;
        
        byte[][] B = new byte[m][n];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                B[i][j] = A[i][(j+i)%4];
            }
        }
        return A = B;
    }
    
    byte[][] mixColumns(byte[][] A){
        // final int m = A.length;
        final int n = A[0].length;
        
        for (int j = 0; j < n; ++j) {
            A = mixCol(A, j);
        }
        
        return A;
    }

    // column vector times MDS matrix
    byte[][] mixCol(byte[][] A, int c){
        final int m = A.length;
        // final int n = A[0].length;
        byte[] word = new byte[4];
        
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < 4; ++j) {
                word[i] ^= gmul(A[j][c], MDS[i][j]);
                if(DEBUG) System.out.printf("A[j][c] = 0x%X\n", A[j][c]);
                if(DEBUG) System.out.printf("MDS[i][j] = 0x%X\n", MDS[i][j]);
                if(DEBUG) System.out.printf("gmul(A[j][c], MDS[i][j]) = 0x%X\n", gmul(A[j][c], MDS[i][j]));
                if(DEBUG) System.out.printf("i = 0x%X\n", i);
                if(DEBUG) System.out.printf("j = 0x%X\n", j);
            }
            if(DEBUG) System.out.printf("word = 0x%X\n\n", word[i]);
        }
        return A = wordToCol(A, word, c);
    }

    void printMatrix(byte[][] A){
        final int m = A.length;
        final int n = A[0].length;
        if(DEBUG) System.out.printf("n = %d\n", n);
        
        for (int b = 0; b < n / 4; ++b) {
            // if(DEBUG) System.out.printf("b = %d\n", b);
            for (int i = 0; i < m; ++i) {
                if (b == 0) System.out.printf("[ ");
                else    System.out.printf("  ");
                for (int j = 4 * b; j < 4*b + 4; ++j) {
                    System.out.printf("%02X ", A[i][j]);
                }
                if (b == n / 4 - 1)System.out.printf("]");
                System.out.println();
            }
            if(b != n/4-1) System.out.println("       +");
        }
        
    }
    
    // Input plaintext block(128 bits) into state
    void inputMatrix(byte[][] A, String file){
        Scanner sc;
        String line;
        try{
            sc = new Scanner(new File(file));
            line = sc.next();
        }catch(Exception e){
            if(DEBUG) System.out.printf("input exception = %s\n", e);
            return;
        }
        final int m = A.length;
        final int n = A[0].length;
        
        while (line.length() < 2*m*n)
            line += "0";
        line = line.substring(0, 2*m*n).toLowerCase();      // truncate extra
        
        for(char c : line.toCharArray()){
            if(c < '0' || c > 'f' || c > '9' && c < 'a')    // non hex character
                break;      // TODO skip line
        }
        
        // if(DEBUG) System.out.printf("line.length() = %s\n", line.length());
        // if(DEBUG) System.out.printf("line = %s\n", line);
        int k = 0;
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m; ++i) {
                String hexStr = line.substring(k, k+2);
                k += 2;
                // if(DEBUG) System.out.printf("hexStr = %s\n", hexStr);
                A[i][j] = (byte)Integer.parseInt(hexStr, 16);
                // if(DEBUG) System.out.printf("A[i][j] = %X\n", A[i][j]);
            }
        }
        // if(DEBUG) System.out.printf("A = %s\n", A[i][j]);
    }
    
    // returns 4x60 byte key schedule
    byte[][] keyExpansion(byte[][] key){
        byte[][] keySchedule = new byte[4][4 * (Nr + 1)];
        
        // copy in cipher key
        for(int i = 0; i < 4; ++i)
            for(int j = 0; j < Nk; ++j){
                keySchedule[i][j] = key[i][j];
            }
        
        for(int j = Nk; j < 4 * (Nr + 1); ++j){
            byte[] word = colToWord(keySchedule, j - 1);        // copy of a column
            if(j % Nk == 0){
                byte[] rconWord = new byte[]{rcon((byte)(j / Nk)), 0, 0, 0};
                // word = SubWord(RotWord(word)) ^ rcon(j / Nk);
                word = RotWord(word);
                word = SubWord(word);
                word = XorWords(word, rconWord);
            }else if (Nk > 6 && j % Nk == 4){
                word = SubWord(word);
            }
            byte[] wordNk = colToWord(keySchedule, j - Nk);
            wordNk = XorWords(wordNk, word);
            keySchedule = wordToCol(keySchedule, wordNk, j);
        }            
            
        return keySchedule;
    }
    
    byte[] RotWord(byte[] word){
        assert word.length == 4;
        
        byte temp = word[0];
        for (int i = 0; i < 4-1; ++i) {
            word[i] = word[i+1];
        }
        word[3] = temp;
        
        return word;
    }
    
    byte[] SubWord(byte[] word){
        assert word.length == 4;
        
        for (int i = 0; i < 4; ++i) {
            word[i] = SubByte(word[i]);
        }
        
        return word;
    }
    
    byte SubByte(byte B){
        int r = B >>> 4 & 0x0F;
        int c = B & 0x0F;            
        // if(DEBUG) System.out.printf("B = 0x%X\n", B);
        // if(DEBUG) System.out.printf("r = 0x%X\n", r);
        // if(DEBUG) System.out.printf("c = %s\n", c);
        B = sbox[r][c];
        return B;
    }
    
    byte[] XorWords(byte[] word, byte[] w2){
        assert word.length == 4;
        
        for (int i = 0; i < 4; ++i) {
            word[i] = (byte)(word[i] ^ w2[i]);
        }
        
        return word;
    }
    
    byte[] colToWord(byte[][] A, final int j){
        byte[] word = new byte[4];
        for(int i = 0; i < 4; ++i)
            word[i] = A[i][j];
        return word;
    }
    
    byte[][] wordToCol(byte[][] A, byte[] word, final int j){
        for(int i = 0; i < 4; ++i)
            A[i][j] = word[i];
        return A;
    }
    
    byte rcon(byte in) {
        byte c = 1;
        if(in == 0)  
            return 0; 
        while(in != 1) {
            c = gmul(c,(byte)2);
            --in;
        }
        return c;
    }
    
    byte gmul(byte a, byte b) {
        byte p = 0;
        byte hi_bit_set;
        for(byte counter = 0; counter < 8; counter++) {
            if((b & 1) != 0) 
                p ^= a;
            hi_bit_set = (byte)(a & 0x80);
            a <<= 1;
            if(hi_bit_set != 0x00) {        // sign extend!
                // if(DEBUG) System.out.printf("hi_bit_set = 0x%X\n", (hi_bit_set));
                a ^= 0x1b;
            }
            b >>= 1;
        }
        return p;
    }
    
    byte[][][] splitIntoRoundKeys(byte[][] keySchedule){
        byte[][][] roundKeys = new byte[Nr + 1][4][4];
        
        for (int r = 0; r < Nr + 1; ++r) {
            for (int j = 0; j < 4; ++j) {
                for (int i = 0; i < 4; ++i) {
                    // if(DEBUG) System.out.printf("r = %d\n", r);
                    // if(DEBUG) System.out.printf("i = %d\n", i);
                    // if(DEBUG) System.out.printf("j = %d\n", j);
                    roundKeys[r][i][j] = keySchedule[i][4 * r + j];
                }
            }
        }
        
        return roundKeys;
    }
    
    // XOR state with round key
    byte[][] addRoundkey(byte[][] A, byte[][] roundKey){
        int m = A.length;
        int n = A[0].length;
        
        // assert
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m; ++i) {
                A[i][j] ^= roundKey[i][j];
            }
        }
        return A;
    }
}