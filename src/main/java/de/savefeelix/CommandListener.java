package de.savefeelix;

import de.savefeelix.command.interfaces.ICommand;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandListener {

    private final String prefix;
    private boolean isRunning;
    private boolean reload;

    public CommandListener(@Nullable String prefix) {
        if (prefix != null)
            this.prefix = prefix;
        else this.prefix = "";
    }

    /**
     * Listen for Commands
     */
    public void listen() {
        reload = true;
        isRunning = true;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String commandLine;
        String commandName;
        List<String> args;
        ICommand command;

        while (reload) {
            reload = false;
            while (isRunning) {
                boolean commandFound = true;
                try {

                    System.out.print(prefix + " > ");
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
    }

    /**
     * Stop listening
     */
    public void stop() {
        isRunning = false;
    }

    /**
     * Check if the Listener is Running
     * @return Boolean
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Check if the Listener can reload
     * @return Boolean
     */
    public boolean isReload() {
        return reload;
    }

    /**
     * Set isRunning
     * @param running Boolean
     */
    public void setRunning(boolean running) {
        isRunning = running;
    }

    /**
     * Set reload
     * @param reload Boolean
     */
    public void setReload(boolean reload) {
        this.reload = reload;
    }

    /**
     * Method to create an CommandListener Instance
     * @param prefix String
     * @return CommandListener
     */
    public static CommandListener initialize(@Nullable String prefix) {
        return new CommandListener(prefix);
    }

    /**
     * Method to create an CommandListener Instance
     * @return CommandListener
     */
    public static CommandListener initialize() {
        return initialize(null);
    }

}
