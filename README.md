## Installation
füge folgendes in die `pom.xml` ein:

    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>org.jbehave</groupId>
        <artifactId>jbehave-core</artifactId>
        <version>4.6.1</version>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>org.jbehave.web</groupId>
        <artifactId>jbehave-web-selenium</artifactId>
        <version>3.5.5</version>
    </dependency>

wie JUnit5 Support realisiert wird kann ich bisher nicht sagen, aber es sieht auch nicht gut aus.

## Runner

Zu Beginn definieren wir eine leere Klasse, in `src.test.java.${Projektname}` die als Einstiegspunkt für JUnit fungiert.
Darin wird auch die Konfiguration vorgenommen. 

Wenn die gleiche Konfiguration für mehrere Stories benutzt wird, leitet man von der `JUnitStories` Klasse ab, ansonsten von `JUnitStory`. (many to one vs. one to one)

    public class MyTestStory extends JUnitStory {
        ...
    }

    public class MyTestStories extends JUnitStories {
        ...
    }

in unserer neuen Klasse müssen nun die `public Configuration configuration()`-Methode und die
`public InjectableStepsFactory stepsFactory()`-Methode definiert werden. 

In `config()` wird das Verhalten des Frameworks bestimmt, e.g. welche Schlüsselwörter beachte ich beim parsen der Stories. 

Und in `stepsFactory()` werden die Schrittdefinitionen eingetragen.

Für ein Many to one mapping mittels der JUnitStoriesKlasse muss auch noch `protected List<String> storyPaths()` implementiert werden.

Darin wird festgelegt wo die Storydateien zu finden sind.


Rest ist TODO

## Schrittdefinitionen

sehen im Großen und Ganzen aus wie in Cucumber

## Userstories

sehen im Großen und Ganzen aus wie in Cucumber