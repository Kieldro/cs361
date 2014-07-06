import java.io.*;
import java.util.*;

class UnitTester{
    
    public static void main(String[] args) {
        AES aes = new AES();
        
        for (int i = 0; i < 256; ++i) {
            
            System.out.printf("aes.rcon(%d) = 0x%X\n", i, aes.rcon((byte)i));
        }
        
    }
}