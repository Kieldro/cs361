package Security;

public class Subject {
    public String name;
    public int temp;
    
    public Subject(String name){
        this.name = name;
        temp = 0;
    }
    
    public void read(Object obj){
        temp = obj.value;
    }
    
    public void write(Object obj, int value){
        if (true)
            obj.value = value;
        
    }
    
    public String toString(){
        return "(" + name + ", " + temp + ") (name, temp)";
    }
}