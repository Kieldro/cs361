package Security;

import java.util.*;
// import Security.*;
import Security.SecureSystem.*;

class ReferenceMonitor {
    public void execute (InstructionObject instruction){
        if (instruction.op == Operation.BAD)
            return;
        
        Subject subj = SecureSystem.subjects.get(instruction.subj);
        String obj  = instruction.obj;
        int val = instruction.val;
        
        
        subj.temp = 0;
    }
    
    public void createObject(String name, SecurityLevel level){
        
    }
    
    class ObjectManager{
            private HashMap objects = new HashMap();
            
    }
}
        