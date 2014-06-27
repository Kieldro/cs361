package Security;

import java.util.*;

import static Security.SecureSystem.*;
import static Security.SecureSystem.Operation.*;

class ReferenceMonitor {
    private HashMap<String, SecurityLevel> subjectLabels = new HashMap();
    private HashMap<String, SecurityLevel> objectLabels = new HashMap();
    private ObjectManager objectManager = new ObjectManager();
    private SecureSystem sys;
    
    public ReferenceMonitor(SecureSystem sys){
        this.sys = sys;
    }
    
    public void execute (InstructionObject instruction){
        if (instruction.op == BAD){
            if(DEBUG) System.out.println("Bad Instruction");
            return;
        }
        
        Subject subj = subjects.get(instruction.subj);
        assert subj != null : "subj is null: " + instruction.subj;
        SecurityLevel subjLevel = subjectLabels.get(instruction.subj);
            // if(DEBUG) System.out.println("label: " + instruction.subj);
        String obj  = instruction.obj;
        int val = instruction.value;
        
        SecurityLevel objLevel = objectLabels.get(obj);
        
        switch (instruction.op){
        case CREATE:
            // a new object is added to the state with SecurityLevel equal to the level of the creating subject. 
            // It is given an initial value of 0. If there already exists an object with that name at any level, the operation is a no-op.
            if(!objectManager.exists(obj)){
                createObject(obj, subjLevel);
                if(DEBUG) System.out.println("CREATE: " + obj);
            }else
                if(DEBUG) System.out.println("CREATE(NO-OP): " + -1);
        break;
        case RUN:
            subj.run();
            if(DEBUG) System.out.println("RUN: " + subj);
        break;
        case DESTROY:
            // eliminate the designated object from the state, 
            // assuming that the object exists and the subject has WRITE access to the object according to the *-property of BLP. 
            // Otherwise, the operation is a no-op.
            if (objectManager.exists(obj) && objLevel.dominates(subjLevel)){
                objectManager.destroy(obj);
                if(DEBUG) System.out.println("DESTROY: " + obj);
            }else
                if(DEBUG) System.out.println("DESTROY(NO-OP): " + -1);
        break;
        case READ:
            int result = subjLevel.dominates(objLevel) ? 
                objectManager.read(obj) : 0;
            subj.temp = result;
            if(DEBUG) System.out.println("READ: " + result);
        break;
        case WRITE:
            if (objLevel.dominates(subjLevel)){
                objectManager.write(obj, val);
                if(DEBUG) System.out.println("WRITE: " + val);
            }else
                if(DEBUG) System.out.println("WRITE(NO-OP): " + val);
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
        System.out.println("Objects: " + objectManager.objects.values());
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
        
        public void destroy (String name){
            objects.remove(name);
            objectLabels.remove(name);
        }
        
        public boolean exists(String obj){
            return objects.containsKey(obj);
        }
    }
}
        