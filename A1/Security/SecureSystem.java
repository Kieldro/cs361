package Security;

import java.io.*;
import java.util.*;

class SecureSystem{
	public static final boolean DEBUG = true;
	public static HashMap<String, Subject> subjects = new HashMap();
	public static SecurityLevel low  = SecurityLevel.LOW;
	public static SecurityLevel high = SecurityLevel.HIGH;
	public static File inFile;
	
	private static SecureSystem sys;
	public static ReferenceMonitor monitor = new ReferenceMonitor(sys);
	
	public enum Operation 
		{READ, WRITE, CREATE, DESTROY, RUN, BAD};
	
	public static void main (String[] args) throws Exception{
		// if(DEBUG) System.out.println("args: " + args[0]);
		sys = new SecureSystem();
		inFile = new File(args[0]);
		
		// create 2 subjects
		sys.createSubject("Lyle", low);
		sys.createSubject("Hal", high);
		
		// create 2 initial objects
		monitor.createObject("LObj", low );
		monitor.createObject("HObj", high);
		
		sys.runSystem();
	}
	
	// Methods
	// nonstatic method
	public void runSystem() throws Exception{
		if (DEBUG) printState();
		// input
		Scanner in = new Scanner(inFile); 
		
		while(in.hasNext()){
			InstructionObject instruction = new InstructionObject(in.nextLine());
			monitor.execute(instruction);
			printState();
		}

		in.close();
	}
	
	
	
	public void createSubject(String name, SecurityLevel level){
		Subject subj = new Subject(name);
        name = name.toLowerCase();
		subjects.put(name, subj);
		monitor.setSubjLabel(name, level);
		
	}
	
	public void printState(){
		if(DEBUG) System.out.println("Printing system state...");
		monitor.printObjects();			// print objects
		System.out.println("Subjects: " + subjects.values());		// print subjects
		System.out.println();
	}
}