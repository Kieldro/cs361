import java.util.*;

public class Foo{
    final static int CONSTANT = 1;
    public enum Operation 
        {READ, WRITE, CREATE, DESTROY, RUN, BAD};
    
    public static void main (String[] args){
        new Foo().wibble();
    }
    
    void wibble(){
        
        
        byte[] A = new byte[]{0x7, 11,13,wobble(1)};
        byte B = (byte)0x93;
        A = qux(A);
        System.out.printf("y = %d\n", -6%4);
    }
    
    byte wobble(int x){
        
        return (byte)(x+2);
    }
    byte[] qux(byte[] B){
        byte[] A = new byte[]{0x7, 11,13};
        B[0] = 23;
        return B = A;
    }
    
}

class Baz{
    
    public void baz(){
        
        System.out.println("baz()");
    }
}