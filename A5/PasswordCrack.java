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
    
        // String encStr = jcrypt.crypt("ab", "integriTY");
        // if(DEBUG) System.out.println("encStr = " + encStr);

        double startTime = System.nanoTime();
        
        // compare generated encrypted passwords
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

        int i = 0;
        for(String word : dict){
            // progress bar
            double progress = (double)i++ / dict.size();
            // double progress = (double)(fLetter - 'a') / ('z' - 'a');
            String bar = spin(progress);
            double endTime = System.nanoTime();
            double duration = (endTime - startTime)/1000000000;
            System.out.printf("%s %.3f sec \"%s\"                        \r", 
                bar, duration, word);
            
            ArrayList<String> mangledWords = mangle(word);
            processList(mangledWords);
            // if(DEBUG)System.out.prTntf("Threads active: %s\n", Thread.activeCount());
            // 2 mangles
            for(String w : mangledWords){
                ArrayList<String> doubleMangle = mangle(w);
                processList(doubleMangle);
            }
        }
        if(DEBUG)System.out.println("\nSearch complete.");
        
        // bandwidth calculations
        double endTime = System.nanoTime();
        double duration = (endTime - startTime)/1000000000;     // seconds
        // System.out.printf("Input size: %d B\n", nBytes);
        System.out.printf("duration: %.3f sec\n", duration);
        
        // System.out.printf("Throughput: %f B/s\n", nBytes/duration);
    }
    
    static void processList(ArrayList<String> list) throws  Exception{
		// if(DEBUG)System.out.println("list.size() = " + list.size());
		ArrayList<Thread> threads = new ArrayList<Thread>(users.size());
		for(User u : users){
			if(u.found){
				//TODO remove user
				continue;		// password already found
			}
	    	Cracker crack = new Cracker(list, u);
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
    	
    	mangledWords.add(word);		// lower case
    	
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

    	// mangledWords.addAll(caps(word));
    	
    	mWord = word + word;
    	mangledWords.add(mWord);
    	
    	mWord = new StringBuilder(word).reverse().toString();
    	mangledWords.add(mWord);
	    
        for(char c = '!'; c <= '~'; ++c){
            mWord = word + String.valueOf(c);
            mangledWords.add(mWord);

            mWord = String.valueOf(c) + word;
            mangledWords.add(mWord);
        }
	    // if(DEBUG)System.out.println("mangledWords.size() = " + mangledWords.size());
    	
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
    
    protected static String spin(double progress) throws Exception{
        // if(DEBUG)System.out.println("progress = " + progress);
        int percent = (int)(progress * 100);
        int x = (int)(progress * 50);
        // if(DEBUG)System.out.println("x = " + x);
        int y = 50 - x;
        String eqStr = new String(new char[x]).replace("\0", "=");
        String spaceStr = new String(new char[y]).replace("\0", " ");
        assert (x + y) == 50;
        String bar = eqStr + spaceStr;
        return String.format("[%s] %d%%", bar, percent);
    }
    
}