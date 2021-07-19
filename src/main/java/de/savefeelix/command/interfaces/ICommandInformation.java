package de.savefeelix.command.interfaces;

import org.jetbrains.annotations.NotNull;
import de.savefeelix.section.interfaces.ISection;

import java.util.List;

public interface ICommandInformation {

    @NotNull
    String getName();

    @NotNull
    String getUsage();

    @NotNull
    ISection getSection();

    @NotNull
    List<String> getAliases();

}
