prog_file = ib_program3.zip
assignment = cs361_prog3
a2file = inFile
# a2file = metamorphosis.txt
# a2file = TheIlliad.txt
a3file = frequenciesFile

all: a3
	
a3:
	clear
	javac *.java
	java -ea Encoder $(a3file) 200
	# hd binaryfile.enc1
	diff testText testText.dec1
	diff testText testText.dec2
	# cat testText.dec2
	
a3test:
	clear
	javac *.java
	java -ea UnitTester
	hexdump binaryfile.enc
	# diff binaryfile binaryfile.dec
	
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
	rm -rf Security/*.class *.out *.log *.enc* *.dec* *.zip *.class
	
turnin: 
	zip -r $(prog_file) *
	# turnin --submit zhaos $(assignment) $(prog_file)
	# turnin --list zhaos $(assignment)
	
verify: 
	turnin --list zhaos $(assignment)
	rm -r keo/
	turnin --verify zhaos $(assignment)
	cd keo; unzip $(prog_file); make
	
