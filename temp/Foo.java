import java.util.*;

public class Foo{
    final static int CONSTANT = 1;
    public enum Operation 
        {READ, WRITE, CREATE, DESTROY, RUN, BAD};
    
    public static void main (String[] args){
        new Foo().wibble();
    }
    
    void wibble(){
        
        
        final byte[] A = new byte[]{0x7, 11,13,17};
        byte[] B = A;
        A[0] = 23;
        
        qux(B);
        
        System.out.printf("y = %d\n", A[0]);
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