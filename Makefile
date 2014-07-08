all: A1 A2 A3 A4 A5

A1:
	cd A1; make a1
	
A2:
	cd A1; make a2
	
A3:
	cd A3; make

A4:
	cd A4; make	

A5:
	cd A5; make	
	
foo:
	javac temp/*.java
	cd temp; java Foo
	
.PHONY: A1 A2 A3 A4 A5