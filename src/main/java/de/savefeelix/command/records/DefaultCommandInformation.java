package de.savefeelix.command.records;

import de.savefeelix.command.interfaces.ICommandInformation;
import de.savefeelix.section.interfaces.ISection;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class DefaultCommandInformation implements ICommandInformation {

    private final String name;
    private final String usage;
    private final ISection section;
    private final List<String> aliases;

    public DefaultCommandInformation(String name, String usage, ISection section, String... aliases) {
        this.name = name;
        this.usage = usage;
        this.section = section;
        this.aliases = Arrays.asList(aliases);
    }

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
        return aliases;
    }
}
