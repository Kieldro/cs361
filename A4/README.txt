FIRSTNAME : Ian;
LASTNAME : Buitrago;
UTEID: ib;
CSACCOUNT : keo;
EMAIL: kieldro@gmail.com;

Assignment 4
7-3-2014
Advanced Encryption Standard (AES-256)

timing info: 
Encrypt = 1170.151238 B/s
Decrypt = 1176.410486 B/s

Both times seem to be similar.

Sample output:
java -ea AES d key plaintext.enc

Decrypting...
CipherKey: 
[ 00 00 00 00 
[ 00 00 00 00 
[ 00 00 00 00 
[ 00 00 00 00 
       +
  00 00 00 00 ]
  00 00 00 00 ]
  00 00 00 00 ]
  00 00 00 00 ]
Ciphertext: 
[ 1C 9E CA 64 ]
[ 06 7E 96 C0 ]
[ 0F A8 1A 5C ]
[ 4C D6 2D 18 ]
addRoundkey state: 
[ 0C CD 8F AF ]
[ FE C1 5F B0 ]
[ 05 DA 63 3F ]
[ 5B 4A CA 9D ]
ROUND 13: 
invShiftRows state: 
[ 0C CD 8F AF ]
[ B0 FE C1 5F ]
[ 63 3F 05 DA ]
[ 4A CA 9D 5B ]
invSubBytes state: 
[ 81 80 73 1B ]
[ FC 0C DD 84 ]
[ 00 25 36 7A ]
[ 5C 10 75 57 ]
addRoundkey state: 
[ F5 F3 51 D5 ]
[ 11 97 8C A4 ]
[ 0B 5B 9B AE ]
[ FD 35 61 6C ]
invMixColumns state: 
[ 71 F7 F1 9C ]
[ 3A F0 3F E1 ]
[ AB 4B 75 9A ]
[ F2 46 9C 54 ]
ROUND 12: 
invShiftRows state: 
[ 71 F7 F1 9C ]
[ E1 3A F0 3F ]
[ 75 9A AB 4B ]
[ 46 9C 54 F2 ]
invSubBytes state: 
[ 2C 26 2B 1C ]
[ E0 A2 17 25 ]
[ 3F 37 0E CC ]
[ 98 1C FD 04 ]
addRoundkey state: 
[ CB 65 3D 92 ]
[ 50 E5 61 9C ]
[ D7 4F 05 D6 ]
[ 04 97 86 66 ]
invMixColumns state: 
[ A8 78 18 BC ]
[ 30 D7 65 F4 ]
[ 24 E2 85 9F ]
[ F4 15 27 69 ]
ROUND 11: 
invShiftRows state: 
[ A8 78 18 BC ]
[ F4 30 D7 65 ]
[ 85 9F 24 E2 ]
[ 15 27 69 F4 ]
invSubBytes state: 
[ 6F C1 34 78 ]
[ BA 08 0D BC ]
[ 67 6E A6 3B ]
[ 2F 3D E4 BA ]
addRoundkey state: 
[ 02 C6 65 94 ]
[ 01 7E C7 CD ]
[ CE 1B 75 42 ]
[ 24 B9 D5 95 ]
invMixColumns state: 
[ F9 3E 68 C1 ]
[ F0 4F E2 21 ]
[ FC D7 7C 10 ]
[ 1C BC F4 7E ]
ROUND 10: 
invShiftRows state: 
[ F9 3E 68 C1 ]
[ 21 F0 4F E2 ]
[ 7C 10 FC D7 ]
[ BC F4 7E 1C ]
invSubBytes state: 
[ 69 D1 F7 DD ]
[ 7B 17 92 3B ]
[ 01 7C 55 0D ]
[ 78 BA 8A C4 ]
addRoundkey state: 
[ 0D 75 A2 45 ]
[ 7D E0 A3 F4 ]
[ FC EC 26 1C ]
[ 2A AD 7A DD ]
invMixColumns state: 
[ E5 38 05 9E ]
[ C2 F9 78 46 ]
[ 43 12 3B 27 ]
[ C2 07 1B 8F ]
ROUND 9: 
invShiftRows state: 
[ E5 38 05 9E ]
[ 46 C2 F9 78 ]
[ 3B 27 43 12 ]
[ 07 1B 8F C2 ]
invSubBytes state: 
[ 2A 76 36 DF ]
[ 98 A8 69 C1 ]
[ 49 3D 64 39 ]
[ 38 44 73 A8 ]
addRoundkey state: 
[ 01 1C 60 62 ]
[ A9 65 D5 7A ]
[ 62 E1 C2 93 ]
[ E7 CB C6 B6 ]
invMixColumns state: 
[ 83 CA E1 75 ]
[ 59 05 F9 60 ]
[ BA 91 3A FE ]
[ 4D 0D 93 D6 ]
ROUND 8: 
invShiftRows state: 
[ 83 CA E1 75 ]
[ 60 59 05 F9 ]
[ 3A FE BA 91 ]
[ 0D 93 D6 4D ]
invSubBytes state: 
[ 41 10 E0 3F ]
[ 90 15 36 69 ]
[ A2 0C C0 AC ]
[ F3 22 4A 65 ]
addRoundkey state: 
[ DF D0 11 F2 ]
[ 3A E4 F0 97 ]
[ 2D 61 23 CE ]
[ DB 67 AD 8C ]
invMixColumns state: 
[ 60 F3 3A CC ]
[ 36 76 18 4D ]
[ B4 26 D6 BE ]
[ F1 91 9B 18 ]
ROUND 7: 
invShiftRows state: 
[ 60 F3 3A CC ]
[ 4D 36 76 18 ]
[ D6 BE B4 26 ]
[ 91 9B 18 F1 ]
invSubBytes state: 
[ 90 7E A2 27 ]
[ 65 24 0F 34 ]
[ 4A 5A C6 23 ]
[ AC E8 34 2B ]
addRoundkey state: 
[ 06 3F 9E CC ]
[ EF D8 7E 33 ]
[ CB AD BC 2F ]
[ 6D B8 0E 80 ]
invMixColumns state: 
[ D7 B7 39 6E ]
[ 98 8A CE 17 ]
[ 84 DA BF 2D ]
[ 84 15 1A 04 ]
ROUND 6: 
invShiftRows state: 
[ D7 B7 39 6E ]
[ 17 98 8A CE ]
[ BF 2D 84 DA ]
[ 15 1A 04 84 ]
invSubBytes state: 
[ 0D 20 5B 45 ]
[ 87 E2 CF EC ]
[ F4 FA 4F 7A ]
[ 2F 43 30 4F ]
addRoundkey state: 
[ 5E 7E 6A 79 ]
[ D3 B9 F8 D4 ]
[ 19 18 C1 FB ]
[ EE 2E 92 41 ]
invMixColumns state: 
[ DF 50 5C A9 ]
[ 2F B2 AA 47 ]
[ 6C CF 6E 05 ]
[ E6 DC 59 FC ]
ROUND 5: 
invShiftRows state: 
[ DF 50 5C A9 ]
[ 47 2F B2 AA ]
[ 6E 05 6C CF ]
[ DC 59 FC E6 ]
invSubBytes state: 
[ EF 6C A7 B7 ]
[ 16 4E 3E 62 ]
[ 45 36 B8 5F ]
[ 93 15 55 F5 ]
addRoundkey state: 
[ 92 BB DA 60 ]
[ 9B 38 B3 14 ]
[ C8 40 35 29 ]
[ F9 84 3F 64 ]
invMixColumns state: 
[ 10 2E CF 5D ]
[ 4D 47 F4 5F ]
[ 3A 5B E6 66 ]
[ 5F 75 BE 5D ]
ROUND 4: 
invShiftRows state: 
[ 10 2E CF 5D ]
[ 5F 4D 47 F4 ]
[ E6 66 3A 5B ]
[ 75 BE 5D 5F ]
invSubBytes state: 
[ 7C C3 5F 8D ]
[ 84 65 16 BA ]
[ F5 D3 A2 57 ]
[ 3F 5A 8D 84 ]
addRoundkey state: 
[ 13 CE 30 80 ]
[ E8 6A 7A B5 ]
[ 99 DC CE 58 ]
[ F0 F6 42 28 ]
invMixColumns state: 
[ 8E 47 DD FC ]
[ 04 C9 45 6D ]
[ 3B 52 B4 AE ]
[ 23 52 EA 7A ]
ROUND 3: 
invShiftRows state: 
[ 8E 47 DD FC ]
[ 6D 04 C9 45 ]
[ B4 AE 3B 52 ]
[ 52 EA 7A 23 ]
invSubBytes state: 
[ E6 16 C9 55 ]
[ B3 30 12 68 ]
[ C6 BE 49 48 ]
[ 48 BB BD 32 ]
addRoundkey state: 
[ 4C BC 63 FF ]
[ 48 CB E9 93 ]
[ 3D 45 B2 B3 ]
[ B3 40 46 C9 ]
invMixColumns state: 
[ 2D 54 A6 AA ]
[ D5 19 76 06 ]
[ 53 49 8B CA ]
[ 21 76 25 70 ]
ROUND 2: 
invShiftRows state: 
[ 2D 54 A6 AA ]
[ 06 D5 19 76 ]
[ 8B CA 53 49 ]
[ 76 25 70 21 ]
invSubBytes state: 
[ FA FD C5 62 ]
[ A5 B5 8E 0F ]
[ CE 10 50 A4 ]
[ 0F C2 D0 7B ]
addRoundkey state: 
[ 98 9F A7 00 ]
[ C6 D6 ED 6C ]
[ AD 73 33 C7 ]
[ 6C A1 B3 18 ]
invMixColumns state: 
[ FB BF 95 B5 ]
[ 85 6F E9 B6 ]
[ EB 7E 8E 0F ]
[ 0A 35 38 BF ]
ROUND 1: 
invShiftRows state: 
[ FB BF 95 B5 ]
[ B6 85 6F E9 ]
[ 8E 0F EB 7E ]
[ 35 38 BF 0A ]
invSubBytes state: 
[ 63 F4 AD D2 ]
[ 79 67 06 EB ]
[ E6 FB 3C 8A ]
[ D9 76 F4 A3 ]
addRoundkey state: 
[ 63 F4 AD D2 ]
[ 79 67 06 EB ]
[ E6 FB 3C 8A ]
[ D9 76 F4 A3 ]
invMixColumns state: 
[ 63 1B C4 4B ]
[ FC EE C1 82 ]
[ AC 28 93 33 ]
[ 16 C3 F5 EA ]
ROUND 0: 
invShiftRows state: 
[ 63 1B C4 4B ]
[ 82 FC EE C1 ]
[ 93 33 AC 28 ]
[ C3 F5 EA 16 ]
invSubBytes state: 
[ 00 44 88 CC ]
[ 11 55 99 DD ]
[ 22 66 AA EE ]
[ 33 77 BB FF ]
addRoundkey state: 
[ 00 44 88 CC ]
[ 11 55 99 DD ]
[ 22 66 AA EE ]
[ 33 77 BB FF ]
Plaintext(state): 
[ 00 44 88 CC ]
[ 11 55 99 DD ]
[ 22 66 AA EE ]
[ 33 77 BB FF ]
Outputting ciphertext to file...
Input size: 129 B
duration: 0.077731 ms
Throughput: 1659.580088 B/s
diff plaintext plaintext.enc.dec
