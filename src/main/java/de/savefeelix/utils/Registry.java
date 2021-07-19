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

    /**
     * Check if the Command is registered
     * @param command ICommand
     * @return boolean
     */
    public static boolean isRegistered(ICommand command) {
        return registeredCommands.contains(command.getClass());
    }

    /**
     * Check if the Section is registered
     * @param section ISection
     * @return boolean
     */
    public static boolean isRegistered(ISection section) {
        return registeredSections.contains(section.getClass());
    }

    /**
     * Register Commands
     * @param command ICommand
     * @throws SectionNotRegisteredException Throw it, if section isn't registered
     */
    public static void register(ICommand command) throws SectionNotRegisteredException {
        if (!isRegistered(command)) {
            if (isRegistered(command.getInformation().getSection()))
                registeredCommands.add(command.getClass());
            else
                throw new SectionNotRegisteredException(command.getInformation().getSection());
        }
    }

    /**
     * Register Section
     * @param section ISection
     */
    public static void register(ISection section) {
        if (!isRegistered(section))
            registeredSections.add(section.getClass());
    }

    /**
     * Unregister Command
     * @param command ICommand
     */
    public static void unregister(ICommand command) {
        if (isRegistered(command))
            registeredCommands.remove(command.getClass());
    }

    /**
     * Unregister Section
     * @param section ISection
     * @throws UnregisterSectionException Throw it if the Section doesn't include Commands
     */
    public static void unregister(ISection section) throws UnregisterSectionException {
        if (isRegistered(section)) {
            if (ICommand.bySection(section).isEmpty()) {
                registeredSections.remove(section.getClass());
            } else
                throw new UnregisterSectionException(section, "Commands use this Section");
        }
    }
}
