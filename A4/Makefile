prog_file = ib_program4.zip
assignment = cs361_prog4
a2file = inFile
# a2file = metamorphosis.txt
# a2file = TheIlliad.txt
a3file = frequenciesFile
a4file = plaintext
dir = .

all: a4
	
a4:
	clear
	javac $(dir)/*.java
	java -ea AES e key $(a4file)
	java -ea AES d key $(a4file).enc
	diff $(a4file) $(a4file).enc.dec
	
a4test:
	clear
	javac *.java
	java -ea UnitTester

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
	rm -rf *.class Security/*.class *.out *.log *.enc* *.dec* keo/ *.class
	
turnin: 
	rm -f $(prog_file)
	zip -r $(prog_file) *
	# turnin --submit zhaos $(assignment) $(prog_file)
	# turnin --list zhaos $(assignment)
	
verify: 
	turnin --list zhaos $(assignment)
	rm -rf keo/
	turnin --verify zhaos $(assignment)
	cd keo; unzip $(prog_file); make
	
# don't confuse folders with rules
.PHONY: a1 a2 a3 a4 a5