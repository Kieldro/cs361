FIRSTNAME : Ian;
LASTNAME : Buitrago;
UTEID: ib;
CSACCOUNT : keo;
EMAIL: kieldro@gmail.com;

Assignment 3
6-27-14
Calculates the entropy of a language. Implements Huffman algorithm.

Uses HuffmanCode.java code from http://rosettacode.org/
Extra credit: Encrypts to and decrypts from pure binary with 1 metabyte of overhead.

Sample Input: 
1
1
2
3
4
5
6
67
Sample Output: 

javac *.java
Note: Some input files use unchecked or unsafe operations.
Note: Recompile with -Xlint:unchecked for details.
java -ea Encoder frequenciesFile 200
freqFile: frequenciesFile
SYMBOL	WEIGHT	HUFFMAN CODE
c	2	0000
b	1	00010
a	1	00011
f	5	001
g	6	010
d	3	0110
e	4	0111
h	67	1
h: 1.438629175442825 bits
1 symbol alphabet
Encoding... 
efficiency: 1.69 bits/symbol

Decoding... 
1 symbol encoding compared to entropy = 17.472941%

2 symbol alphabet
SYMBOL	WEIGHT	HUFFMAN CODE
hf	42292	0000
fh	42292	0001
dh	25375	00100
hd	25375	00101
hg	50751	0011
gh	50751	0100
de	1514	010100000
bg	757	0101000010
ag	757	0101000011
gb	757	0101000100
cd	757	0101000101
dc	757	0101000110
ga	757	0101000111
ed	1514	010100100
cg	1514	010100101
eg	3029	01010011
ge	3029	01010100
ff	3156	01010101
gc	1514	010101100
df	1893	010101101
gf	3787	01010111
fg	3787	01011000
fd	1893	010110010
bb	126	0101100110000
ba	126	0101100110001
aa	126	0101100110010
ab	126	0101100110011
ac	252	010110011010
cb	252	010110011011
be	504	01011001110
ea	504	01011001111
ae	504	01011010000
cc	504	01011010001
eb	504	01011010010
bc	252	010110100110
ca	252	010110100111
ce	1009	0101101010
ec	1009	0101101011
ee	2019	010110110
gd	2272	010110111
hb	8458	0101110
bh	8458	0101111
ah	8458	0110000
ha	8458	0110001
ch	16917	011001
he	33834	01101
eh	33834	01110
hc	16917	011110
gg	4544	01111100
dg	2272	011111010
dd	1136	0111110110
cf	1262	0111110111
fc	1262	0111111000
fb	631	01111110010
bf	631	01111110011
ef	2524	011111101
fe	2524	011111110
af	631	01111111100
fa	631	01111111101
db	378	011111111100
ad	378	011111111101
bd	378	011111111110
da	378	011111111111
hh	566721	1
Encoding... 
efficiency: 1.52 bits/symbol

Decoding... 
2 symbol encoding compared to entropy = 5.656136%
# hd binaryfile.enc1
diff testText testText.dec1
diff testText testText.dec2
