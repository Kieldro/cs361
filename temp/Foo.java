// import java.util.*;

public class Foo extends Thread{
    final static int CONSTANT = 1;
    
    public void run(){
        
        System.out.printf("Thread.currentThread().getName() = %s\n", Thread.currentThread().getName());
        
        System.out.println("Hello!");
    }
    public static void main (String[] args){
        
        new Thread(new Foo()).start();
        System.out.printf("Thread.currentThread().getName() = %s\n", Thread.currentThread().getName());
        new Thread(new Foo()).start();
        new Thread(new Foo()).start();
        new Thread(new Foo()).start();
        new Thread(new Foo()).start();
        
        
        
    }
    
    void wibble(){
        
        
        
        
        
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

