all: SecureSystem.java
	clear
	javac *.java
	java -ea SecureSystem instructionList
	
clean:
	rm *.class
	
turnin: 
	clean
	zip keo_program1.zip README *.java Makefile instructionList
	turnin --submit zhaos cs361_prog1 keo_program1.zip