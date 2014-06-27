package Security;

public class Subject {
    public String name;
    public int temp = 0;
    
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