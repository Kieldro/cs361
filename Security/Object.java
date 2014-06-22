package Security;

public class Object {
    public String name;
    public int value = 0;
    
    public Object(String name){
        this.name = name;
    }
    
    public String toString(){
        return /*"(" +*/ name + ": " + value/* + ") (name, value)"*/;
    }
}