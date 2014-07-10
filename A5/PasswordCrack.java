import java.io.*;
import java.util.*;

class PasswordCrack{
    static final boolean DEBUG = true;
    
    public static void main(String[] args) throws Exception{
        String dictStr = args[0];
        String passwords = args[1];
        if(DEBUG)System.out.println("dictStr = " + dictStr);
        
        File pFile = new File(passwords);
        Scanner sc = new Scanner(pFile);
        
        
        String line = sc.nextLine();
        if(DEBUG)System.out.println("line = " + line);
        String[] tokens = line.split(":");
        for(String s : tokens)
        	if(DEBUG)System.out.println("tokens[i] = " + s);
        
        String ePass = tokens[1];
        String salt = ePass.substring(0, 2);
        String[] name = tokens[4].split(" ");
        if(DEBUG)System.out.println("ePass = " + ePass);
        if(DEBUG)System.out.println("salt = " + salt);
        if(DEBUG)System.out.println("name[] = " + name[0]);
        
        
        // input Dictionary
        File dFile = new File(dictStr);
        sc = new Scanner(dFile);
        String[] dict = new String[400];
        int i = 0;
        
        while(sc.hasNext()){
        	if(DEBUG)System.out.println("i = " + i);
        	dict[i] = sc.next();
        	if(DEBUG)System.out.println("dict[i] = " + dict[i]);
        	++i;
        }
        dict[390] = name[0];
        dict[391] = name[1];
        
        
        // String s= genEncryptedPass();
        
        
        String str = jcrypt.crypt("(b", "amazing");
        if(DEBUG)System.out.println("str = " + str);
        
        
        
    }
    
    protected void spin() throws Exception{
        String eqStr = new String(new char[25]).replace("\0", "=");
        String spaceStr = new String(new char[25]).replace("\0", " ");
        int i = 0;
        while(true){
        	assert (eqStr.length() + spaceStr.length()) == 50;
        	String bar = eqStr + spaceStr;
        	System.out.printf("[%s]\r", bar);
        	Thread.sleep(100);
        	
        	// c = (char)(++c % ('Z' - 'A' + 1) + 'A');
        	
        	++i;
        }
    }
    
}