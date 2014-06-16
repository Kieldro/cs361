import java.io.*; 
import java.util.*; 

class SecureSystem{
	private static boolean DEBUG = true;
	
	public static void main (String[] args) throws Exception{
		SecureSystem sys = new SecureSystem();
		
		if (DEBUG)sys.printState();
		
		
		// create 2 initial objects
		Obj HObj = new Obj("HObj", 3);
		Obj LObj = new Obj("LObj", 5);
		
		// create 2 subjects
		
		
		// if(DEBUG) System.out.println("Hal: " + Hal);
		// if(DEBUG) System.out.println("Lyle: " + Lyle);
		if(DEBUG) System.out.println("args: " + args[0]);
		
		
		// input
		Scanner in = new Scanner(new File(args[0])); 


		while(in.hasNext()){
			InstructionObject instruction = sys.input(in.nextLine());

		}

		in.close();

		
		
		
		
		
		
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
	
	public InstructionObject input(String line) throws Exception{
		if(DEBUG) System.out.println("line: " + line);
		String[] command = line.split("\\s");
		for(String s : command)
			if(DEBUG) System.out.println("s: \"" + s + '"');

		if (command[0].equals("read") && command.length == 3){

		}else if(command[0].equals("write") && command.length == 4){
			String val = command[3];
			if(DEBUG) System.out.println("val: " + val);
		}else{
			if(DEBUG) System.out.println("BadInstruction: \"" + line + '"');
			return this.new InstructionObject();
		}
		String subj = command[1];
		if(DEBUG) System.out.println("subj: " + subj);
		String obj = command[2];
		if(DEBUG) System.out.println("obj: " + obj);

		return new InstructionObject();
	}
	
	public void createSubject(){
		
		
	}
	
	public void printState(){
		// print objects
		
		// print subjects
		
	}
	
	public static class Subj {
		public static String name;
		public int temp;
		
		public Subj(String name){
			this.name = name;
			temp = 0;
		}
		
		public void read(Obj obj){
			temp = obj.value;
		}
		
		public void write(Obj obj, int value){
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
	// clas
	
	class ObjectManager{
		
		
	}

	class ReferenceMonitor {
		
		
	}

	class SecurityLevel{
		final int HIGH = 1;
		final int LOW = 0;

	}
	
}