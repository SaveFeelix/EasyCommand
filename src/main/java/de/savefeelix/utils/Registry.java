package de.savefeelix.utils;

import de.savefeelix.command.interfaces.ICommand;
import de.savefeelix.section.exception.sub.SectionNotRegisteredException;
import de.savefeelix.section.exception.sub.UnregisterSectionException;
import de.savefeelix.section.interfaces.ISection;

import java.util.ArrayList;
import java.util.List;

public class Registry {

    public static final List<Class<? extends ICommand>> registeredCommands = new ArrayList<>();
    public static final List<Class<? extends ISection>> registeredSections = new ArrayList<>();

    public static boolean isRegistered(ICommand command) {
        return registeredCommands.contains(command.getClass());
    }

    public static boolean isRegistered(ISection section) {
        return registeredSections.contains(section.getClass());
    }

    public static void register(ICommand command) throws SectionNotRegisteredException {
        if (!isRegistered(command)) {
            if (isRegistered(command.getInformation().getSection()))
                registeredCommands.add(command.getClass());
            else
                throw new SectionNotRegisteredException(command.getInformation().getSection());
        }
    }

    public static void register(ISection section) {
        if (!isRegistered(section))
            registeredSections.add(section.getClass());
    }

    public static void unregister(ICommand command) {
        if (isRegistered(command))
            registeredCommands.remove(command.getClass());
    }

    public static void unregister(ISection section) throws UnregisterSectionException {
        if (isRegistered(section)) {
            if (ICommand.bySection(section).isEmpty()) {
                registeredSections.remove(section.getClass());
            } else
                throw new UnregisterSectionException(section, "Commands use this Section");
        }
    }
}
