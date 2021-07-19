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
        <version>c5aaeb9fcf</version>
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
    implementation 'com.github.SaveFeelix:EasyCommand:c5aaeb9fcf'
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
    public @NotNull String getName() {
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
