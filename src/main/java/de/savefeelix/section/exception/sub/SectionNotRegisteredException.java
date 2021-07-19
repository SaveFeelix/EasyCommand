package de.savefeelix.section.exception.sub;

import de.savefeelix.section.exception.SectionException;
import de.savefeelix.section.interfaces.ISection;

public class SectionNotRegisteredException extends SectionException {
    public SectionNotRegisteredException(ISection section) {
        super("Section " + section.getName() + " not Registered");
    }
}
