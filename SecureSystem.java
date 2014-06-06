class SecureSystem{
	private static boolean DEBUG = true;
	
	public static void main (String[] args){
		System.out.println("Victory!");
		
		
		Obj Hal = new Obj("Hal", 3);
		Obj Lyle = new Obj("Lyle", 5);
		
		if(DEBUG) System.out.println("Hal: " + Hal);
		if(DEBUG) System.out.println("Lyle: " + Lyle);
		
		
		
		
		
		
		
		
		
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
	
}