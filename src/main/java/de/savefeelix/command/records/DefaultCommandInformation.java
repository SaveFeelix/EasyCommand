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
    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public @NotNull String getUsage() {
        return usage;
    }

    @Override
    public @NotNull ISection getSection() {
        return section;
    }

    @Override
    public @NotNull List<String> getAliases() {
        return Arrays.asList(aliases);
    }
}
