package Security;

import java.util.*;
// import Security.*;
import Security.SecureSystem.*;

class ReferenceMonitor {
    private HashMap<String, SecurityLevel> subjectLabels = new HashMap();
    // private HashMap<String, SecurityLevel> objLabels = new HashMap();
    private ObjectManager objManager = new ObjectManager();
    
    public void execute (InstructionObject instruction){
        if (instruction.op == Operation.BAD)
            return;
        
        Subject subj = SecureSystem.subjects.get(instruction.subj);
        String obj  = instruction.obj;
        int val = instruction.val;
        
        
        subj.temp = 0;
    }
    
    public void createObject(String name, SecurityLevel level){
        Object obj = new Object(name);
        objManager.objects.put(name, obj);
        
    }
    
    public void printObjects(){
        
        System.out.println(objManager.objects.values());
        
        
    }
    
    class ObjectManager{
            private HashMap<String, Object> objects = new HashMap();
            
            public int read (String name){
                
                
                return objects.get(name).value;
            }
            
            public int write (String name, int val){
                
                
                objects.get(name).value = val;
                
                return 0;
            }
    }
}
        