package Security;

class SecurityLevel {
    public final static SecurityLevel HIGH = new SecurityLevel(1);
    public final static SecurityLevel LOW = new SecurityLevel(0);
    
    private int val = 0;
    
    public SecurityLevel(int val){
        this.val = val;
    }
    
    public boolean dominates(SecurityLevel level){
        return this.val >= level.val;
    }
}