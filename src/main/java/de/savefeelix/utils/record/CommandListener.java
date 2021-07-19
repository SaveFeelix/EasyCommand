package de.savefeelix.utils.record;

import de.savefeelix.command.interfaces.ICommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandListener {

    private final String prefix;
    private static boolean isRunning;

    static {
        isRunning = true;
    }

    public CommandListener(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Listen for Commands
     */
    public void listen() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String commandLine;
        String commandName;
        List<String> args;
        ICommand command;

        while (isRunning) {
            boolean commandFound = true;
            try {

                System.out.print(prefix);
                commandLine = reader.readLine();
                String[] commandLineAsArray = commandLine.split(" ");
                commandName = commandLineAsArray[0];


                if (commandLineAsArray.length > 1) {
                    args = Arrays.asList(commandLineAsArray).subList(1, commandLineAsArray.length);
                } else {
                    args = new ArrayList<>();
                }

                command = ICommand.byName(commandName);
                if (command == null) {
                    command = ICommand.byAlias(commandName);
                    if (command == null)
                        commandFound = false;
                }

                if (commandFound)
                    command.run(args);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Stop listening
     */
    public void stop() {
        isRunning = false;
    }


    /**
     * Method to create an CommandListener Instance
     * @param prefix String
     * @return CommandListener
     */
    public static CommandListener initialize(String prefix) {
        return new CommandListener(prefix);
    }

    /**
     * Method to create an CommandListener Instance
     * @return CommandListener
     */
    public static CommandListener initialize() {
        return initialize("> ");
    }

}
