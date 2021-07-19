package de.savefeelix.defaults;

import de.savefeelix.MainClass;
import de.savefeelix.command.abstracts.DefaultCommand;
import de.savefeelix.command.records.DefaultCommandInformation;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ExitCommand extends DefaultCommand {

    public ExitCommand() {
        super(new DefaultCommandInformation("exit", "/exit", new HelpSection(), "!", "end", "stop"));
    }

    @Override
    public void run(@NotNull List<String> args) {
        MainClass.getListener().stop();
    }
}
