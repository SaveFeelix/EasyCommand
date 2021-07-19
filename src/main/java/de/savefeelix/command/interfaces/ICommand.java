package de.savefeelix.command.interfaces;

import de.savefeelix.section.interfaces.ISection;
import de.savefeelix.utils.Registry;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public interface ICommand {

    /**
     * Get the Information
     *
     * @return ICommandInformation
     */
    @NotNull
    ICommandInformation getInformation();

    /**
     * Execute of the Command
     *
     * @param args List<String>
     */
    void run(@NotNull List<String> args);


    /**
     * Get all Commands
     *
     * @return List<ICommand>
     */
    static List<ICommand> all() {
        List<ICommand> commands = new ArrayList<>();
        Registry.registeredCommands.forEach(command -> {
            try {
                commands.add(command.getConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        return commands;
    }

    /**
     * Get all Command with a specific Section
     *
     * @param section ISection
     * @return List<ICommand>
     */
    static List<ICommand> bySection(ISection section) {
        return all().stream().filter(command -> command.getInformation().getSection().equals(section)).toList();
    }

    /**
     * Get Command by Name
     *
     * @param name String
     * @return ICommand
     */
    static ICommand byName(String name) {
        AtomicReference<ICommand> tmpCommand = new AtomicReference<>(null);
        all().forEach(command -> {
            if (command.getInformation().getName().equalsIgnoreCase(name))
                tmpCommand.set(command);
        });
        return tmpCommand.get();
    }

    /**
     * Get Command by Alias
     *
     * @param alias String
     * @return ICommand
     */
    static ICommand byAlias(String alias) {
        AtomicReference<ICommand> tmpCommand = new AtomicReference<>(null);
        all().forEach(command -> {
            if (command.getInformation().getAliases().stream().map(String::toLowerCase).toList().contains(alias.toLowerCase()))
                tmpCommand.set(command);
        });
        return tmpCommand.get();
    }

    /**
     * Get Command by Usage
     *
     * @param usage String
     * @return ICommand
     */
    static ICommand byUsage(String usage) {
        AtomicReference<ICommand> tmpCommand = new AtomicReference<>(null);
        all().forEach(command -> {
            if (command.getInformation().getUsage().equalsIgnoreCase(usage))
                tmpCommand.set(command);
        });
        return tmpCommand.get();
    }
}