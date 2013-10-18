package ru.fizteh.fivt.students.kislenko.shell;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ru.fizteh.fivt.students.kislenko.filemap.*;

public class CmdLauncher {
    private Map<String, Command> commandList = new HashMap<String, Command>();

    public void addCommand(Command command) {
        commandList.put(command.getName(), command);
    }

    private String getCommand(String inputString) {
        int start = 0;
        int finish = inputString.indexOf(" ");
        if (finish == -1) {
            finish = inputString.length();
        }
        return inputString.substring(start, finish);
    }

    private String[] getArgs(String inputString) {
        int start = inputString.indexOf(" ");
        if (start == -1) {
            return new String[0];
        }
        String[] result = inputString.substring(start + 1, inputString.length()).trim().split("\\s+");
        for (int i = 0; i < result.length; ++i) {
            result[i] = result[i].trim();
        }
        return result;
    }

    public void launch(State state, String input) throws IOException {
        String command = getCommand(input.trim());
        String[] args = getArgs(input.trim());
        if (command.isEmpty()) {
            throw new IOException("Empty input.");
        }
        if (!commandList.containsKey(command)) {
            throw new IOException("Wrong command.");
        }
        if (args.length != commandList.get(command).getArgCount()) {
            throw new IOException("Incorrect argument count.");
        }
        commandList.get(command).run(state, args);
    }
}