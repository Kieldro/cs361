import java.util.*;

public class Foo{
    final static int CONSTANT = 1;
    public enum Operation 
        {READ, WRITE, CREATE, DESTROY, RUN, BAD};
    
    public static void main (String[] args){
        new Foo().wibble();
    }
    
    void wibble(){
        
        
        final byte[] A = new byte[]{0x7, 11,13,wobble(1)};
        byte B = (byte)0x93;
        System.out.printf("y = 0x%X\n", (byte)(B >>> 8));
    }
    
    byte wobble(int x){
        
        return (byte)(x+2);
    }
    void qux(byte[] B){
        
        B[0] = 23;
    }
    
}

class Baz{
    
    public void baz(){
        
        System.out.println("baz()");
    }
}