import java.io.*; 
import java.util.*; 

class SecureSystem{
	private static boolean DEBUG = true;
	
	public static void main (String[] args) throws Exception{
		System.out.println("Victory!");
		
		
		Obj Hal = new Obj("Hal", 3);
		Obj Lyle = new Obj("Lyle", 5);
		
		if(DEBUG) System.out.println("Hal: " + Hal);
		if(DEBUG) System.out.println("Lyle: " + Lyle);
		if(DEBUG) System.out.println("args: " + args[0]);
		
		
		// input
		Scanner in = new Scanner(new File(args[0])); 
		String text = in.nextLine(); 
		in.close();
		if(DEBUG) System.out.println("text: " + text);
		
		
		
		
		
		
		
		
		// // LOW and HIGH are constants defined in the SecurityLevel 
		// // class, such that HIGH dominates LOW.

		// SecurityLevel low  = SecurityLevel.LOW;
		// SecurityLevel high = SecurityLevel.HIGH;

		// // We add two subjects, one high and one low.

		// sys.createSubject("Lyle", low);
		// sys.createSubject("Hal", high);

		// // We add two objects, one high and one low.

		// sys.getReferenceMonitor().createNewObject("Lobj", low);
		// sys.getReferenceMonitor().createNewObject("Hobj", high);
		
	}
	
	public static class Subj {
		public String name;
		public int temp;
		
		public Subj(String name){
			this.name = name;
			temp = 0;
		}
		
		public read(Obj obj){
			temp = obj.value;
		}
		
		public write(Obj obj, int value){
			if (true)
				obj.value = value;
			
		}
		
		public String toString(){
			return "(" + name + ", " + temp + ") (name, temp)";
		}
	}
	
	public static class Obj {
		public String name;
		public int value;
		
		public Obj(String name, int value){
			this.name = name;
			this.value = value;
		}
		
		public String toString(){
			return "(" + name + ", " + value + ") (name, value)";
		}
	}
	
	class InstructionObject {
		
		
	}
	
	class ObjecManager{
		
		
	}

	class ReferenceMonitor {
		
		
	}
	
}