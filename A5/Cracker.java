import java.util.*;

class Cracker extends Thread{
    User user;
    ArrayList<String> mangleList;
	
    Cracker(ArrayList<String> mList, User u){
    	user = u;
    	mangleList = mList;
    }
    
    public void run(){
		for(String w : mangleList){
			String encStr = jcrypt.crypt(user.salt, w);
			if(encStr.equals(user.ePass)){
			    System.out.printf("FOUND: password for %s = \"%s\"          \n", 
			    	Arrays.toString(user.name), w);
				user.found = true;
				return;
			}
		}
    }
}