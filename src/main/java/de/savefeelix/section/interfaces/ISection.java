package de.savefeelix.section.interfaces;

import de.savefeelix.command.interfaces.ICommand;
import de.savefeelix.utils.Registry;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public interface ISection {

    /**
     * Get the Name of the Section
     * @return String
     */
    @NotNull
    String getName();

    /**
     * Get all REGISTERED Sections
     * @return List<ISection>
     */
    static List<ISection> all() {
        List<ISection> sections = new ArrayList<>();
        Registry.registeredSections.forEach(section -> {
            try {
                sections.add(section.getConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        });
        return sections;
    }

    /**
     * Get Section by Name
     * @param name String
     * @return ISection
     */
    static ISection byName(String name) {
        AtomicReference<ISection> tmpSection = new AtomicReference<>(null);
        all().forEach(section -> {
            if (section.getName().equalsIgnoreCase(name))
                tmpSection.set(section);
        });
        return tmpSection.get();
    }

    static ISection byCommand(ICommand command) {
        return command.getInformation().getSection();
    }

}
