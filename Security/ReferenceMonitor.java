package Security;

import java.util.*;
// import Security.*;
import Security.SecureSystem.*;

class ReferenceMonitor {
    private HashMap<String, SecurityLevel> subjectLabels = new HashMap();
    private HashMap<String, SecurityLevel> objectLabels = new HashMap();
    private ObjectManager objectManager = new ObjectManager();
    private SecureSystem sys;
    
    public ReferenceMonitor(SecureSystem sys){
        this.sys = sys;
    }
    
    public void execute (InstructionObject instruction){
        if (instruction.op == Operation.BAD)
            return;
        
        Subject subj = SecureSystem.subjects.get(instruction.subj);
        SecurityLevel subjLevel = subjectLabels.get(instruction.subj);
            // if(SecureSystem.DEBUG) System.out.println("label: " + instruction.subj);
        String obj  = instruction.obj;
        SecurityLevel objLevel = objectLabels.get(instruction.obj);
        int val = instruction.val;
        assert objLevel != null && subjLevel != null : "NULL label.";
        
        switch (instruction.op){
            case READ:
                int result = subjLevel.dominates(objLevel) ? 
                    objectManager.read(obj) : 0;
                subj.temp = result;
                if(SecureSystem.DEBUG) System.out.println("READ: " + result);
            break;
            case WRITE:
                if (objLevel.dominates(subjLevel)){
                    objectManager.write(obj, val);
                    if(SecureSystem.DEBUG) System.out.println("WRITE: " + val);
                }
            break;
            case CREATE:
                // a new object is added to the state with SecurityLevel equal to the level of the creating subject. 
                // It is given an initial value of 0. If there already exists an object with that name at any level, the operation is a no-op.
                
            break;
            case DESTROY:
                // eliminate the designated object from the state, 
                // assuming that the object exists and the subject has WRITE access to the object according to the *-property of BLP. 
                // Otherwise, the operation is a no-op.
                
            break;
            case RUN:
                
                
        }
        
    }
    
    public void createObject(String name, SecurityLevel level){
        Object obj = new Object(name);
        name = name.toLowerCase();
        objectManager.objects.put(name, obj);
        objectLabels.put(name, level);
        
    }
    
    public void setSubjLabel(String name, SecurityLevel level){
        subjectLabels.put(name, level);
    }
    
    public void printObjects(){
        
        System.out.println(objectManager.objects.values());
        
        
    }
    
    class ObjectManager{
            private HashMap<String, Object> objects = new HashMap();
            
            public int read (String name){
                assert objects.containsKey(name): "Object doesn't not exist: " + name;
                
                return objects.get(name).value;
            }
            
            public int write (String name, int val){
                
                
                objects.get(name).value = val;
                
                return 0;
            }
    }
}
        