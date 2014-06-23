all: Security/*.java
	clear
	cd Security/;	javac *.java
	java -ea Security.SecureSystem instructionList
	# make clean
	
clean:
	rm -rf Security/*.class
	
turnin: clean
	zip -r keo_program1.zip *
	turnin --submit zhaos cs361_prog1 keo_program1.zip