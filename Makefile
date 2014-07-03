prog_file = keo_program3.zip
a2file = inFile
# a2file = metamorphosis.txt
# a2file = TheIlliad.txt
a3file = frequenciesFile

all: 
	clear
	javac *.java
	java -ea Encoder $(a3file) 100
	diff testText testText.dec1
	# diff testText testText.dec2
	
a2: compile
	clear
	java -ea Security.CovertChannel v $(a2file)
	diff $(a2file) $(a2file).out
	
a1:	
	clear
	javac Security/*.java
	java -ea Security.SecureSystem instructionList
	
foo:
	javac temp/*.java
	cd temp; java Foo
	
compile: Security/*.java
	javac  Security/*.java
	
clean:
	rm -rf Security/*.class *.out *.log
	
turnin: clean
	zip -r $(prog_file) *
	turnin --submit zhaos cs361_prog3 $(prog_file)