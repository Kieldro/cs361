prog_file = ib_program5.zip
assignment = cs361_prog5
# a5file = plaintext

all: a5
	
a5:
	clear
	javac *.java
	java -ea PasswordCrack newshort-words.txt passwd2.txt
	
clean:
	rm -rf *.class Security/*.class *.out *.log *.enc* *.dec* keo/ *.class
	
turnin: clean
	zip -r $(prog_file) *
	turnin --submit zhaos $(assignment) $(prog_file)
	turnin --list zhaos $(assignment)
	
verify: 
	turnin --list zhaos $(assignment)
	rm -rf keo/
	turnin --verify zhaos $(assignment)
	cd keo; unzip $(prog_file) -d temp; cd temp; make
