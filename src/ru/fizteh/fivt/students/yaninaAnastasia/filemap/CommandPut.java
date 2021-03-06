package ru.fizteh.fivt.students.yaninaAnastasia.filemap;

import ru.fizteh.fivt.students.yaninaAnastasia.shell.Command;

import java.io.IOException;

public class CommandPut extends Command {
    public boolean exec(String[] args, State curState) throws IOException {
        DBState myState = DBState.class.cast(curState);
        if (myState.table == null) {
            System.out.println("no table");
            return false;
        }
        if (args.length != 2) {
            System.err.println("Invalid arguments");
            return false;
        }
        String prevValue = myState.table.put(args[0], args[1]);
        if (prevValue == null) {
            System.out.println("new");
        } else {
            System.out.println("overwrite");
            System.out.println(prevValue);
        }
        return true;
    }

    public String getCmd() {
        return "put";

    }
}
