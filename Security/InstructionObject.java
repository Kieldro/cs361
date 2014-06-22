package Security;

import Security.SecureSystem.*;

class InstructionObject {
    Operation op;
    String subj;
    String obj;
    int val = 0;
    
    // constructors
    public InstructionObject(Operation operation, String s, String o){
        this(operation, s, o, 0);
    }
    public InstructionObject(Operation operation, String s, String o, int v){
        op = operation;
        subj = s;
        obj = o;
        val  = v;
    }
}