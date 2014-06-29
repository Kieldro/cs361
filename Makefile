prog_file = keo_program2.zip
file = inFile

all: compile
	clear
	java -ea Security.CovertChannel $(file)
	diff $(file) $(file).out 
	
a1:	
	clear
	javac Security/*.java
	java -ea Security.SecureSystem instructionList
	
compile: Security/*.java
	javac  Security/*.java
	
clean:
	rm -rf Security/*.class *.out *.log
	
turnin: clean
	zip -r $(prog_file) *
	turnin --submit zhaos cs361_prog2 $(prog_file)