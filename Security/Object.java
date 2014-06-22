package Security;

public class Object {
        public String name;
        public int value;
        
        public Object(String name, int value){
            this.name = name;
            this.value = value;
        }
        
        public String toString(){
            return "(" + name + ", " + value + ") (name, value)";
        }
    }