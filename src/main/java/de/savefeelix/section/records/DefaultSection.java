package de.savefeelix.section.records;

import de.savefeelix.section.interfaces.ISection;
import org.jetbrains.annotations.NotNull;

public record DefaultSection(String name) implements ISection {
    @Override
    public @NotNull String getName() {
        return name;
    }
}
