all: Security/*.java
	clear
	cd Security/;	javac *.java
	java -ea Security.SecureSystem instructionList
	# clean
	
clean:
	rm -r Security/*.class
	
turnin: 
	clean
	zip keo_program1.zip README *.java Makefile instructionList
	turnin --submit zhaos cs361_prog1 keo_program1.zip