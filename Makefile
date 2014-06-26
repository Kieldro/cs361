all: compile
	clear
	java -ea Security.CovertChannel inFile
	
a1:	
	clear
	cd Security/;	javac *.java
	java -ea Security.SecureSystem instructionList
	
compile: Security/*.java
	cd Security/; javac *.java
	
clean:
	rm -rf Security/*.class
	
turnin: clean
	zip -r keo_program1.zip *
	turnin --submit zhaos cs361_prog1 keo_program1.zip