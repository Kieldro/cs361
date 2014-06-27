package Security;

import java.util.*; 
import Security.SecureSystem.*;

class InstructionObject {
    Operation op;
    String subj;
    String obj;
    int value = 0;
    private static HashMap<String, Operation> opStrings = new HashMap();
    // static initializer
    static{
        opStrings.put("read", Operation.READ);
        opStrings.put("write", Operation.WRITE);
        opStrings.put("create", Operation.CREATE);
        opStrings.put("destroy", Operation.DESTROY);
        opStrings.put("run", Operation.RUN);
    }
    
    public InstructionObject(String line) throws Exception{
        if(SecureSystem.DEBUG) System.out.println("line: " + line);
        
        String[] command = line.trim().toLowerCase().split("\\s+");
        // if(SecureSystem.DEBUG) for(String s : command) System.out.println("s: \"" + s + '"');
        op = opStrings.get(command[0]);
        if (op == null || command.length < 2)
            op = Operation.BAD;
        
        switch (op){
        case WRITE:
            if(command.length == 4){
                value = Integer.parseInt(command[3]);
                subj = command[1];
                // if(SecureSystem.DEBUG) System.out.println("subj: " + subj);
                obj = command[2];
                // if(SecureSystem.DEBUG) System.out.println("value: " + value);
            }else{
                op = Operation.BAD;
            }
        break;
        case READ:
        case CREATE:
        case DESTROY:
            if (command.length == 3){
                subj = command[1];
                obj = command[2];
            }
        break;
        case RUN:
            if(command.length == 2){
                op = Operation.RUN;
            }
        break;
        case BAD:
        default:
            if(SecureSystem.DEBUG) System.out.println("BadInstruction: \"" + line + '"');
        }
    }
}