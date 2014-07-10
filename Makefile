all: a5

a1:
	cd A1; make a1
	
a2:
	cd A1; make a2
	
a3:
	cd A3; make

a4:
	cd A4; make	

a5:
	cd A5; make	
	
foo:
	javac temp/*.java
	cd temp; java Foo
	
.PHONY: A1 A2 A3 A4 A5