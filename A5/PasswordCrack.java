import java.io.*;
import java.util.*;

class PasswordCrack extends Thread{
    static final boolean DEBUG = true;
    static ArrayList<User> users;
    static ArrayList<String> dict;
    String currentWord;
    
    PasswordCrack(String w){
    	currentWord = w;
    }
    
    public void run(){
	   	// if(DEBUG)System.out.printf("Thread %s starting...\n", Thread.currentThread().getName());
	   	
		// if(DEBUG)System.out.println("currentWord = " + currentWord);
		ArrayList<String> mangledWords = mangle();
		for(String w : mangledWords){
			for(User u : users){
				String encStr = jcrypt.crypt(u.salt, w);
				if(u.found) continue;
				if(encStr.equals(u.ePass)){
				    System.out.printf("FOUND: password for %s = \"%s\"\n", 
				    	Arrays.toString(u.name), w);
					u.found = true;
					continue;
				}
			}
		}
    	
    }
    ArrayList<String> mangle (){
    	ArrayList<String> mangledWords = new ArrayList<String>();
    	String mWord = currentWord.toUpperCase();
    	
    	mangledWords.add(currentWord);		// lower case
    	mangledWords.add(mWord);
    	mWord = currentWord.substring(0, 1).toUpperCase() + currentWord.substring(1);
    	mangledWords.add(mWord);
    	mWord = currentWord.substring(0, 1) + currentWord.toUpperCase().substring(1);
    	mangledWords.add(mWord);
    	
    	return mangledWords;
    }
    
    public static void main(String[] args) throws Exception{
        String dictStr = args[0];
        String passwords = args[1];
        if(DEBUG)System.out.println("dictStr = " + dictStr);
        
        // create array of users' info
        users = userInfo(passwords);
        
        // input dictionary
        File dFile = new File(dictStr);
        Scanner sc = new Scanner(dFile);
        dict = new ArrayList<String>(400);
        while(sc.hasNext()){
        	dict.add(sc.next().toLowerCase());
        	// if(DEBUG)System.out.println("dict[i] = " + dict.get(i));
        }
        
        double startTime = System.nanoTime();
        
        // compare generated encrypted passwords
        // String s= genEncryptedPass();
        for(String word : dict){
	        PasswordCrack pc = new PasswordCrack(word);
	        // pc.run();		// single thread
	        Thread t = new Thread(pc);
	        t.start();
	        
	       
        }
	   	if(DEBUG)System.out.println("Search complete.");
        
        // bandwidth calculations
        double endTime = System.nanoTime();
        double duration = (endTime - startTime)/1000000;     // seconds
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
	        // if(DEBUG)System.out.println("ePass = " + ePass);
	        // if(DEBUG)System.out.println("salt = " + salt);
	        // if(DEBUG)System.out.println("first name = " + name[0]);
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