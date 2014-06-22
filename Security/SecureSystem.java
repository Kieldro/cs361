package Security;

import java.io.*; 
import java.util.*; 

class SecureSystem{
	private static boolean DEBUG = true;
	private static SecureSystem sys = new SecureSystem();
	private static File inFile;
	public static HashMap<String, Subject> subjects = new HashMap();
	private final InstructionObject badInstruction = 
		new InstructionObject(Operation.BAD, "NA", "NA");
	private ReferenceMonitor monitor = new ReferenceMonitor();
	
	public enum Operation 
		{READ, WRITE, BAD};
	public enum SecurityLevel 
		{LOW, HIGH};
	
	public static void main (String[] args) throws Exception{
		if(DEBUG) System.out.println("args: " + args[0]);
		inFile = new File(args[0]);
		sys.runSystem();
	}
	
	// Methods
	// nonstatic method
	public void runSystem() throws Exception{
		if (DEBUG) printState();
		
		// create 2 subjects
		createSubject("Lyle", SecurityLevel.LOW);
		createSubject("Hal", SecurityLevel.HIGH);
		
		// // create 2 initial objects
		monitor.createObject("Lobj", SecurityLevel.LOW );
		monitor.createObject("Hobj", SecurityLevel.HIGH);
		
		// input
		Scanner in = new Scanner(inFile); 
		
		while(in.hasNext()){
			InstructionObject instruction = input(in.nextLine());
			monitor.execute(instruction);
			printState();
		}

		in.close();
	}
	
	public InstructionObject input(String line) throws Exception{
		if(DEBUG) System.out.println("line: " + line);
		Operation op;
		String subj;
		String obj;
		int val = 0;
		
		String[] command = line.split("\\s");
		for(String s : command)
			if(DEBUG) System.out.println("s: \"" + s + '"');

		if (command[0].equals("read") && command.length == 3){
			op = Operation.READ;
		}else if(command[0].equals("write") && command.length == 4){
			op = Operation.WRITE;
			val = Integer.parseInt(command[3]);
			if(DEBUG) System.out.println("val: " + val);
		}else{
			if(DEBUG) System.out.println("BadInstruction: \"" + line + '"');
			return badInstruction;
		}
		subj = command[1];
		if(DEBUG) System.out.println("subj: " + subj);
		obj = command[2];
		if(DEBUG) System.out.println("obj: " + obj);

		return new InstructionObject(op, subj, obj, val);
	}
	
	public void createSubject(String name, SecurityLevel level){
		Subject subj = new Subject(name);
		subjects.put(name, subj);
		// ReferenceMonitor level
		
	}
	
	public void printState(){
		if(DEBUG) System.out.println("Printing system state...");
		// print subjects
		System.out.println(subjects.entrySet());
		// System.out.println("Hal: " + Hal);
		// System.out.println("Lyle: " + Lyle);
		
		// print objects
		
	}
}