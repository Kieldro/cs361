import java.io.*;
import java.util.*;

class PasswordCrack{
    static final boolean DEBUG = true;
    static ArrayList<String> dict;
    static LinkedList<User> users;
    
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
	        processWord(word);
    		// if(DEBUG)System.out.printf("Threads active: %s\n", Thread.activeCount());
        }
		for(User u : users){
			if(u.found) continue;		// password already found
			ArrayList<String> mangledWords = new ArrayList<String>();
			for(String n : u.name)
				mangledWords.addAll(mangle(n));
	    	Cracker crack = new Cracker(mangledWords, u);
	        crack.run();		// single thread
	        // Thread t = new Thread(crack);
	        // t.start();
	        // threads.add(t);
		}
        
	   	if(DEBUG)System.out.println("Search complete.");
        
        // bandwidth calculations
        double endTime = System.nanoTime();
        double duration = (endTime - startTime)/1000000;     // seconds
        // System.out.printf("Input size: %d B\n", nBytes);
        System.out.printf("duration: %f ms\n", duration);
        
        // System.out.printf("Throughput: %f B/s\n", nBytes/duration);
    }
    
    static void processWord(String word) throws	Exception{
	   	
		// if(DEBUG)System.out.println("currentWord = " + currentWord);
		ArrayList<String> mangledWords = mangle(word);
		ArrayList<Thread> threads = new ArrayList<Thread>(users.size());
		for(User u : users){
			if(u.found){
				//TODO remove user
				continue;		// password already found
			}
	    	Cracker crack = new Cracker(mangledWords, u);
	        // crack.run();		// single thread
	        Thread t = new Thread(crack);
	        t.start();
	        threads.add(t);
		}
		
		// wait for all threads to terminate
		for(Thread t : threads){
			t.join();
		}
    }
    
    static LinkedList<User> userInfo(String passwords) throws Exception{
    	File pFile = new File(passwords);
        Scanner sc = new Scanner(pFile);
        LinkedList<User> users = new LinkedList<User>();
        while(sc.hasNext()){
	        String line = sc.nextLine();
	        // if(DEBUG)System.out.println("line = " + line);
	        String[] tokens = line.split(":");
	        // if(DEBUG) for(String s : tokens) System.out.println("tokens[i] = " + s);
	        String[] name = tokens[4].toLowerCase().split(" ");
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
    
    static ArrayList<String> mangle (String word){
    	ArrayList<String> mangledWords = new ArrayList<String>();
    	String mWord;
    	
    	// mangledWords.add(word);		// lower case
    	
    	mWord = word.substring(1);
    	mangledWords.add(mWord);
    	mWord = word.substring(0, word.length()-1);
    	mangledWords.add(mWord);
    	
    	// capitalization
    	mWord = word.toUpperCase();
    	mangledWords.add(mWord);
    	mWord = word.substring(0, 1).toUpperCase() + word.substring(1);
    	mangledWords.add(mWord);
    	mWord = word.substring(0, 1) + word.toUpperCase().substring(1);
    	mangledWords.add(mWord);

    	// mangledWords.addAll(capitalizations(word));
    	
    	
    	
    	mWord = word + word;
    	mangledWords.add(mWord);
    	
    	mWord = new StringBuilder(word).reverse().toString();
    	mangledWords.add(mWord);
	    
	    // if(DEBUG)System.out.println("mangledWords.size() = " + mangledWords.size());
    	
    	
    	
    	return mangledWords;
    }
    
    static ArrayList<String> capitalizations (String word){
    	ArrayList<String> mangledWords = new ArrayList<String>();
    	String mWord;
    	
    	// capitalization
    	mangledWords = caps(word);
    	
	    // if(DEBUG) for(String s : mangledWords) System.out.println("s = " + s);
    	
    	return mangledWords;
    }
    
    static ArrayList<String> caps(String word){
    	ArrayList<String> list = new ArrayList<String>();
    	if(word.length() == 1)
    		list.add(word);
    	else for(String sub : caps(word.substring(1))){
    		list.add(word.substring(0,1) + sub);
    		list.add(word.substring(0,1).toUpperCase() + sub);
    	}
    	
    	return list;
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