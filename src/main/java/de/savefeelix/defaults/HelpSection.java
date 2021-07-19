package de.savefeelix.defaults;

import de.savefeelix.section.interfaces.ISection;
import org.jetbrains.annotations.NotNull;

public class HelpSection implements ISection {
    @Override
    public @NotNull String getName() {
        return "Help";
    }
}
