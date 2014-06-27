prog_file = keo_program2.zip

all: compile
	clear
	java -ea Security.CovertChannel v FILENAME
	
a1:	
	clear
	javac Security/*.java
	java -ea Security.SecureSystem instructionList
	
compile: Security/*.java
	javac  Security/*.java
	
clean:
	rm -rf Security/*.class *.out
	
turnin: clean
	zip -r $(prog_file) *
	turnin --submit zhaos cs361_prog2 $(prog_file)