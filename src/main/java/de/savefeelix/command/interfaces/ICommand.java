package de.savefeelix.command.interfaces;

import de.savefeelix.section.interfaces.ISection;
import de.savefeelix.utils.Registry;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public interface ICommand {

    @NotNull
    ICommandInformation getInformation();

    void run(@NotNull List<String> args);


    static List<ICommand> all() {
        List<ICommand> commands = new ArrayList<>();
        Registry.registeredCommands.forEach(command -> {
            try {
                commands.add(command.getConstructor().newInstance());
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        });
        return commands;
    }

    static List<ICommand> bySection(ISection section) {
        return all().stream().filter(command -> command.getInformation().getSection().equals(section)).toList();
    }

    static ICommand byName(String name) {
        AtomicReference<ICommand> tmpCommand = new AtomicReference<>(null);
        all().forEach(command -> {
            if (command.getInformation().getName().equalsIgnoreCase(name))
                tmpCommand.set(command);
        });
        return tmpCommand.get();
    }
    static ICommand byAlias(String alias) {
        AtomicReference<ICommand> tmpCommand = new AtomicReference<>(null);
        all().forEach(command -> {
            if (command.getInformation().getAliases().stream().map(String::toLowerCase).toList().contains(alias.toLowerCase()))
                tmpCommand.set(command);
        });
        return tmpCommand.get();
    }
    static ICommand byUsage(String usage) {
        AtomicReference<ICommand> tmpCommand = new AtomicReference<>(null);
        all().forEach(command -> {
            if (command.getInformation().getUsage().equals(usage))
                tmpCommand.set(command);
        });
        return tmpCommand.get();
    }
}