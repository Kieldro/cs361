all: Security/*.java
	clear
	cd Security/;	javac *.java
	java -ea Security.SecureSystem instructionList
	clean
	
clean:
	rm -r Security/*.class
	
turnin: 
	clean
	zip -r keo_program1.zip *
	turnin --submit zhaos cs361_prog1 keo_program1.zip