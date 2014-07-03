import java.util.*;

public class Foo{
    final static int CONSTANT = 1;
    public enum Operation 
        {READ, WRITE, CREATE, DESTROY, RUN, BAD};
    
    public static void main (String[] args){
        HashMap map = new HashMap();
        
        map.put("zero", 0);
        map.put("x", 3);
        map.put("y", 5);
        
        double x = 0.4;
        
        System.out.printf("x = %d\n", (int)x);
    }
}

class Baz{
    
    public void baz(){
        
        System.out.println("baz()");
    }
}