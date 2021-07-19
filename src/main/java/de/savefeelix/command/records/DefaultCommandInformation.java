package de.savefeelix.command.records;

import de.savefeelix.command.interfaces.ICommandInformation;
import de.savefeelix.section.interfaces.ISection;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public record DefaultCommandInformation(String name,
                                        String usage,
                                        ISection section,
                                        String... aliases) implements ICommandInformation {
    /**
     * Get Name of the Command
     * @return String
     */
    @Override
    public @NotNull String getName() {
        return name;
    }

    /**
     * Get Usage of the Command
     * @return String
     */
    @Override
    public @NotNull String getUsage() {
        return usage;
    }

    /**
     * Get Section of the Command
     * @return ISection
     */
    @Override
    public @NotNull ISection getSection() {
        return section;
    }

    /**
     * Get Aliases of the Command
     * @return List<String>
     */
    @Override
    public @NotNull List<String> getAliases() {
        return Arrays.asList(aliases);
    }
}
