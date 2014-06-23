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
        
        if(instruction.op == Operation.READ){
            int result;
            if (subjLevel.dominates(objLevel)){
                result = objectManager.read(obj);
            }else{
                result = 0;
            }
            subj.temp = result;
            if(SecureSystem.DEBUG) System.out.println("READ: " + result);
        }else if(instruction.op == Operation.WRITE){
            if (objLevel.dominates(subjLevel)){
                objectManager.write(obj, val);
                if(SecureSystem.DEBUG) System.out.println("WRITE: " + val);
            }
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
        