package de.savefeelix.command.abstracts;

import de.savefeelix.command.interfaces.ICommand;
import de.savefeelix.command.interfaces.ICommandInformation;
import org.jetbrains.annotations.NotNull;

public abstract class DefaultCommand implements ICommand {

    private final ICommandInformation information;

    public DefaultCommand(ICommandInformation information) {
        this.information = information;
    }

    @NotNull
    @Override
    public ICommandInformation getInformation() {
        return information;
    }
}
