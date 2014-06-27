package Security;

import java.util.*;

import static Security.SecureSystem.*;
import static Security.SecureSystem.Operation.*;

class InstructionObject {
    Operation op;
    String subj;
    String obj;
    int value = 0;
    private static HashMap<String, Operation> opStrings = new HashMap();
    // static initializer
    static{
        opStrings.put("read", READ);
        opStrings.put("write", WRITE);
        opStrings.put("create", CREATE);
        opStrings.put("destroy", DESTROY);
        opStrings.put("run", RUN);
    }
    
    public InstructionObject(String line) throws Exception{
        if(DEBUG) System.out.println("line: " + line);
        
        String[] command = line.trim().toLowerCase().split("\\s+");
        // if(DEBUG) for(String s : command) System.out.println("s: \"" + s + '"');
        op = opStrings.get(command[0]);
        if (op == null || command.length < 2)
            op = BAD;
        
        switch (op){
        case WRITE:
            if(command.length == 4){
                value = Integer.parseInt(command[3]);
                subj = command[1];
                // if(DEBUG) System.out.println("subj: " + subj);
                obj = command[2];
                // if(DEBUG) System.out.println("value: " + value);
            }else{
                op = BAD;
            }
        break;
        case READ:
        case CREATE:
        case DESTROY:
            if (command.length == 3){
                subj = command[1];
                obj = command[2];
            }else{
                op = BAD;
            }
        break;
        case RUN:
            if (command.length != 2)
                op = BAD;
        break;
        case BAD:
        default:
            // if(DEBUG) System.out.println("BadInstruction: \"" + line + '"');
        }
    }
}