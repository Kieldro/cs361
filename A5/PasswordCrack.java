import java.io.*;
import java.util.*;

class PasswordCrack{
    static final boolean DEBUG = true;
    
    public static void main(String[] args) throws Exception{
        String dictStr = args[0];
        String passwords = args[1];
        if(DEBUG)System.out.println("dictStr = " + dictStr);
        
        // create array of users' info
        ArrayList<User> users = userInfo(passwords);
        
        
        // input dictionary
        File dFile = new File(dictStr);
        Scanner sc = new Scanner(dFile);
        ArrayList<String> dict = new ArrayList<String>(400);
        
        while(sc.hasNext()){
        	dict.add(sc.next());
        	// if(DEBUG)System.out.println("dict[i] = " + dict.get(i));
        }
        // dict[390] = name[0];
        // dict[391] = name[1];
        
        
        
        // compare generated encrypted passwords
        double startTime = System.nanoTime();
        
        // String s= genEncryptedPass();
        User u = users.get(0);
        for(String word : dict){
	        // if(DEBUG)System.out.println("word = " + word);
	        String encStr = jcrypt.crypt(u.salt, word);
	        if(u.ePass.equals(encStr)){
	    	    System.out.printf("FOUND: password for %s = %s\n", Arrays.toString(u.name), word);
	        	return;
	    	}
        }
	   	if(DEBUG)System.out.println("password not.");
        
        
        // bandwidth calculations
        double endTime = System.nanoTime();
        double duration = (endTime - startTime);     // seconds
        
        // System.out.printf("Input size: %d B\n", nBytes);
        System.out.printf("duration: %f ms\n", duration);
        // System.out.printf("Throughput: %f B/s\n", nBytes/duration);
    }
    
    static ArrayList<User> userInfo(String passwords) throws Exception{
    	File pFile = new File(passwords);
        Scanner sc = new Scanner(pFile);
        ArrayList<User> users = new ArrayList<User>();
        while(sc.hasNext()){
	        String line = sc.nextLine();
	        // if(DEBUG)System.out.println("line = " + line);
	        String[] tokens = line.split(":");
	        // if(DEBUG) for(String s : tokens) System.out.println("tokens[i] = " + s);
	        String[] name = tokens[4].split(" ");
	        String ePass = tokens[1];
	        String salt = ePass.substring(0, 2);
	        User user = new User(name, salt, ePass);
	        users.add(user);
	        if(DEBUG)System.out.println("ePass = " + ePass);
	        if(DEBUG)System.out.println("salt = " + salt);
	        if(DEBUG)System.out.println("first name = " + name[0]);
	        
	    }
    	
    	return users;
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