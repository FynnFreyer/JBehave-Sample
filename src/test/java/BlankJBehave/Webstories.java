package BlankJBehave;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.*;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.MarkUnmatchedStepsAsPending;

import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.*;


public class Webstories extends JUnitStories {

    @Override
    public Configuration configuration() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL codeLocation = codeLocationFromClass(this.getClass());
        Keywords keywords = new LocalizedKeywords(new Locale("de"));
        Properties properties = new Properties();
        properties.setProperty("encoding", "UTF-8");
        Configuration configuration = new MostUsefulConfiguration()
                .useKeywords(keywords)
                .useStepCollector(new MarkUnmatchedStepsAsPending(keywords))
                .useStoryParser(new RegexStoryParser(keywords))
                .useStoryLoader(new LoadFromClasspath(classLoader))
                .useDefaultStoryReporter(new ConsoleOutput(keywords))
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withCodeLocation(codeLocation)
                        .withPathResolver(new FilePrintStreamFactory.ResolveToSimpleName())
                        .withDefaultFormats()
                        .withFormats(CONSOLE, TXT, HTML, XML)
                        .withViewResources(properties)
                        .withFailureTrace(true)
                        .withKeywords(keywords));
        return configuration;
    }
 
    @Override
    public InjectableStepsFactory stepsFactory() {        
        return new InstanceStepsFactory(configuration(), new StepDefinition());
    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(codeLocationFromClass(this.getClass()).getFile(), "**/*.story", null);
    }
}
