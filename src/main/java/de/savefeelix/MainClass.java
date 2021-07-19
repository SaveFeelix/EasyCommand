package de.savefeelix;

import de.savefeelix.defaults.ExitCommand;
import de.savefeelix.defaults.HelpSection;
import de.savefeelix.section.exception.sub.SectionNotRegisteredException;
import de.savefeelix.utils.Registry;
import de.savefeelix.utils.interfaces.CommandInitializer;
import de.savefeelix.utils.record.CommandListener;

public class MainClass implements CommandInitializer {

    private static CommandListener listener;

    public static void main(String[] args) {
        MainClass mainClass = new MainClass();
        listener = CommandListener.initialize();
        mainClass.initSections();
        mainClass.initCommands();
        listener.listen();
        System.exit(0);
    }

    @Override
    public void initCommands() {
        try {
            Registry.register(new ExitCommand());
        } catch (SectionNotRegisteredException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initSections() {
        Registry.register(new HelpSection());
    }

    public static CommandListener getListener() {
        return listener;
    }
}
