package de.savefeelix.section.exception.sub;

import de.savefeelix.section.exception.SectionException;
import de.savefeelix.section.interfaces.ISection;

public class UnregisterSectionException extends SectionException {

    /**
     * SubException of SectionException
     * @param section ISection
     * @param reason String
     */
    public UnregisterSectionException(ISection section, String reason) {
        super("Cannot unregister Section " + section + "\n" + reason);
    }
}
