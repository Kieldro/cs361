public class User{
	String[] name;
	String salt;
	String ePass;
	boolean found = false;
	
	User(String[] n, String salt, String ePass){
		name = n;
		this.salt = salt;
		this.ePass = ePass;
	}
}