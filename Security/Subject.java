package Security;

import java.io.*;
import static Security.SecureSystem.*;

public class Subject {
    public String name;
    public int temp = 0;
    private int mark = 0;
    private int B = 0;
    
    private static FileOutputStream fout;
    private static DataOutputStream dout;
    
    public Subject(String name){
        this.name = name;
        try{
            fout = new FileOutputStream(CovertChannel.inFile + ".out");
            dout = new DataOutputStream(fout);
        }catch(Exception e){
            ;
            
        }
    }
    
    // add it to a byte he's creating, and if the byte is complete, write it to the output.
    public void run() throws Exception{
        assert temp == 0 || temp == 1: "Subject.temp must be 0 or 1.";
        
        // if(DEBUG) System.out.println("mark: " + mark);
        
            
        B = B | temp << mark;
        ++mark;
        
        if(mark > 7){
            // if(DEBUG) System.out.println("writing byte: 0x" + Integer.toHexString(B));
            // if(DEBUG) System.out.println("writing byte: " + B);
            dout.write(B);
            dout.flush();
            B = 0;
            mark = 0;
        }
    }
    
    public String toString(){
        return /*"(" +*/ name + ": " + temp/* + ") (name, temp)"*/;
    }
}