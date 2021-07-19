package de.savefeelix.section.exception.sub;

import de.savefeelix.section.exception.SectionException;
import de.savefeelix.section.interfaces.ISection;

public class UnregisterSectionException extends SectionException {

    public UnregisterSectionException(ISection section, String reason) {
        super("Cannot unregister Section " + section + "\n" + reason);
    }
}
