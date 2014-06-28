package Security;

import java.io.*;

public class Subject {
    public String name;
    public int temp = 0;
    
    private ByteArrayInputStream byteStream = new ByteArrayInputStream(new byte[8]);
    
    public Subject(String name){
        this.name = name;
    }
    
    // add it to a byte he's creating, and if the byte is complete, write it to the output.
    public void run(){
        assert temp == 0 || temp == 1;
        
        
    }
    
    public String toString(){
        return /*"(" +*/ name + ": " + temp/* + ") (name, temp)"*/;
    }
}