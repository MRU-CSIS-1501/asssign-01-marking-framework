package step_definitions;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.contentOf;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.File;
import java.util.Queue;
import java.util.LinkedList;

import cucumber.api.java.en.*;
import cucumber.api.java.Before;
import cucumber.api.java.After;
import cucumber.api.Scenario;
import cucumber.api.PendingException;

import java.util.regex.Pattern;


public class TurningJapaneseChallengeSteps {
	
    private static final PrintStream ORIGINAL_OUT = System.out;
    private static final ByteArrayOutputStream OUT_CONTENT = new ByteArrayOutputStream();
    
    private String[] splitOutput;
    private Queue<String> inputQueue = new LinkedList<>();

    @Before
    public void beforeCallingScenario() {
    }

	
    @After
    public void afterRunningScenario(Scenario scenario) {
		System.setOut(ORIGINAL_OUT);
        OUT_CONTENT.reset();
    }


    @When("^I run the Turning Japanese Challenge$")
    public void iRunTheTurningJapaneseChallenge() throws Throwable {
        System.setOut(new PrintStream(OUT_CONTENT, true, "UTF-8"));

        Class.forName("ProblemSetMain").getMethod("runTurningJapaneseChallenge").invoke(null);

        splitOutput = OUT_CONTENT.toString("UTF-8").split("\\R+");
    }

    @Then("^the console should show Ken's expression in Japanese on the first line$")
    public void theConsoleShouldShowKenSExpressionInJapaneseOnTheFirstLine() throws Throwable {
        char[] expectedCharacters = {
            (char)12362, 
            (char)21069, 
            (char)12399, 
            (char)12418, 
            (char)12358, 
            (char)27515, 
            (char)12435, 
            (char)12391, 
            (char)12427};
        String expectedExpression = new String(expectedCharacters);
        assertThat(splitOutput[0]).as("Incorrect Japanese expression found.").isEqualTo(expectedExpression);
    }

    @Then("^the console should show the English translation of that expression on the second line$")
    public void theConsoleShouldShowTheEnglishTranslationOfThatExpressionOnTheSecondLine() throws Throwable {
        assertThat(splitOutput[1])
            .as("Incorrect English translation found.")
            .isEqualToIgnoringCase("You are already dead.");
    }                                                                                                                                                                                                                       
}
