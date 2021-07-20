# EasyCommand

<hr />

## Description

API to create your own **CONSOLE** Command
<hr />

## API

### Maven

```xml

<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

```xml

<dependencies>
    <dependency>
        <groupID>com.github.SaveFeelix</groupID>
        <artifactID>EasyCommand</artifactID>
        <version>765fa82661</version>
    </dependency>
</dependencies>
```

<hr />

### Gradle

```groovy
repositories {
    maven { url = 'https://jitpack.io' }
}
```

```groovy
dependencies {
    implementation 'com.github.SaveFeelix:EasyCommand:765fa82661'
}
```

<hr />

### Java

#### Create own Command

```java
import de.savefeelix.command.abstracts.DefaultCommand;
import de.savefeelix.command.information.DefaultCommandInformation;

public class MyCommand extends DefaultCommand {

    public MyCommand() {
        super(new DefaultCommandInformation(
                "MyCommandName",
                "MyCommandUsage",
                new DefaultSection("SectionName"),
                "Alias1", "Alias2", "Alias3"
        ));
    }

    @Override
    public void run(@NotNull List<String> args) {
        /*
         * Your Code
         */
    }
}
```

<hr />

#### Create own Section

```java
import de.savefeelix.section.interfaces.ISection;

public class MySection implements ISection {

    @Override
    public @NotNull
    String getName() {
        return "SectionName";
    }
}
```

<hr />

#### Instance CommandListener

```java
import de.savefeelix.CommandListener;

public class MyClass {
    public static void main(String[] args) {
        CommandListener listener = CommandListener.initialize("myPrefix");
        listener.listen();
    }
}
```

<hr />

#### Include Override Methodes to register Commands/Sections

```java
import de.savefeelix.CommandListener;
import de.savefeelix.utils.interfaces.CommandInitializer;

public class MyClass implements CommandInitializer {
    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        CommandListener listener = CommandListener.initialize("myPrefix");
        myClass.initCommands();
        myClass.initSections();
        listener.listen();
    }

    @Override
    public void initCommands() {
    }

    @Override
    public void initSections() {
    }
}
```

<hr />

####  Recommend Usage

```java
import de.savefeelix.CommandListener;
import de.savefeelix.command.abstracts.DefaultCommand;
import de.savefeelix.command.information.DefaultCommandInformation;
import de.savefeelix.command.interfaces.ICommandInformation;
import de.savefeelix.section.exception.sub.SectionNotRegisteredException;
import de.savefeelix.section.interfaces.ISection;
import de.savefeelix.utils.Registry;
import de.savefeelix.utils.interfaces.CommandInitializer;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyClass implements CommandInitializer {

    private static MyClass INSTANCE;
    private static CommandListener listener;

    public static void main(String[] args) {
        INSTANCE = new MyClass();

        // Example 1
        listener = new CommandListener("MyPrefix");
        // Example 2
        listener = new CommandListener();

        // Start Listening
        listener.listen();
    }

    @Override
    public void initCommands() {
        try {
            Registry.register(new MyCommand());
        } catch (SectionNotRegisteredException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initSections() {
        Registry.register(new MySection());
    }

    public static MyClass getINSTANCE() {
        return INSTANCE;
    }
    public static CommandListener getListener() {
        return listener;
    }

    // Example Command
    static class MyCommand extends DefaultCommand {
        public MyCommand() {
            super(new DefaultCommandInformation(
                    "myCommand",
                    "/myCommand [your Params]",
                    new MySection(),
                    "firstAlias",
                    "secondAlias"
            ));
        }

        @Override
        public void run(@NotNull List<String> args) {
            // yourCode
        }
    }

    // Example Section
    static class MySection implements ISection {
        @Override
        public @NotNull String getName() {
            return "MySection";
        }
    }
}
```