prog_file = keo_program2.zip


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
	zip -r $(prog_file) *
	turnin --submit zhaos cs361_prog2 $(prog_file)