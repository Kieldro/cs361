prog_file = ib_program2.zip
assignment = cs361_prog2
a2file = inFile
# a2file = metamorphosis.txt
# a2file = TheIlliad.txt

all: a1
	
a1:	
	clear
	javac Security/*.java
	java -ea Security.SecureSystem instructionList
	
a2: compile
	clear
	java -ea Security.CovertChannel v $(a2file)
	diff $(a2file) $(a2file).out
	
compile: Security/*.java
	javac  Security/*.java
	
clean:
	rm -rf Security/*.class *.out *.log *.enc* *.dec* *.zip
	
turnin: 
	zip -r $(prog_file) *
	# turnin --submit zhaos $(assignment) $(prog_file)
	# turnin --list zhaos $(assignment)
	
verify: 
	turnin --list zhaos $(assignment)
	rm -r keo/
	turnin --verify zhaos $(assignment)
	cd keo; unzip $(prog_file); make
