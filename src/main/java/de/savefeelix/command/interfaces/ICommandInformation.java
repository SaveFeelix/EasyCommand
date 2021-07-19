package de.savefeelix.command.interfaces;

import org.jetbrains.annotations.NotNull;
import de.savefeelix.section.interfaces.ISection;

import java.util.List;

public interface ICommandInformation {

    /**
     * Get Name of the Command
     * @return String
     */
    @NotNull
    String getName();

    /**
     * Get Usage of the Command
     * @return String
     */
    @NotNull
    String getUsage();

    /**
     * Get Section of the Command
     * @return ISection
     */
    @NotNull
    ISection getSection();

    /**
     * Get Aliases of the Command
     * @return List<String>
     */
    @NotNull
    List<String> getAliases();

}
