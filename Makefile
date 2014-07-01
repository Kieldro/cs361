prog_file = keo_program3.zip
a2file = inFile
a3file = frequenciesFile
# a2file = metamorphosis.txt
# a2file = TheIlliad.txt

all: 
	clear
	javac *.java
	java -ea Encoder $(a3file) 40
	diff testText testText.dec1
	
a2: compile
	clear
	java -ea Security.CovertChannel v $(file)
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
	turnin --submit zhaos cs361_prog3 $(prog_file)