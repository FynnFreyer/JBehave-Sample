package BlankJBehave;

import org.jbehave.core.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;


public class StepDefinition {

    private WebDriver driver;

    @BeforeScenario
    public void setUp() {
        driver = new FirefoxDriver();
    }

    @AfterScenario
    public void tearDown() {
        driver.close();
    }

    @Given("die Seite $seite wurde geöffnet")
    public void öffneSeite(String seite) {
        driver.get(seite);
    }

    @When("ich auf das Element $id klicke")
    public void klickeElement(String id) {
        WebElement element = driver.findElement(By.id(id));
        element.click();
    }

    @Then("sollte der Titel der Seite $title lauten")
    public void titelStimmt(String title) {
        assertEquals(title, driver.getTitle());
    }

    }
