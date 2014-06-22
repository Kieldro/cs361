package Security;

public class Subject {
    public String name;
    public int temp = 0;
    
    public Subject(String name){
        this.name = name;
    }
    
    public String toString(){
        return /*"(" +*/ name + ": " + temp/* + ") (name, temp)"*/;
    }
}