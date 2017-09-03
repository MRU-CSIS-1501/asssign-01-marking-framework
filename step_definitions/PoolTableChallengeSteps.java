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


public class PoolTableChallengeSteps {
	
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


    @When("^I run the Pool Table Challenge$")
    public void iRunThePoolTableChallenge() throws Throwable {
        System.setOut(new PrintStream(OUT_CONTENT, true, "UTF-8"));

        Class.forName("ProblemSetMain").getMethod("runPoolTableChallenge").invoke(null);

        splitOutput = OUT_CONTENT.toString("UTF-8").split("\\R+");

    }


    @Then("^the console should display 9 entries representing the ball's path until it enters a corner pocket$")       
    public void theLogFileShouldHaveEntriesRepresentingTheBallSPathUntilItEntersACornerPocket() throws Throwable {
        assertThat(splitOutput[0]).as("Ball should bounce from (0,0) ⇨ (3,3)").isEqualTo("(0,0) ⇨ (3,3)");  
        assertThat(splitOutput[1]).as("Ball should bounce from (3,3) ⇨ (6,0)").isEqualTo("(3,3) ⇨ (6,0)");
        assertThat(splitOutput[2]).as("Ball should bounce from (6,0) ⇨ (7,1)").isEqualTo("(6,0) ⇨ (7,1)");
        assertThat(splitOutput[3]).as("Ball should bounce from (7,1) ⇨ (5,3)").isEqualTo("(7,1) ⇨ (5,3)");
        assertThat(splitOutput[4]).as("Ball should bounce from (5,3) ⇨ (2,0)").isEqualTo("(5,3) ⇨ (2,0)");
        assertThat(splitOutput[5]).as("Ball should bounce from (2,0) ⇨ (0,2)").isEqualTo("(2,0) ⇨ (0,2)");
        assertThat(splitOutput[6]).as("Ball should bounce from (0,2) ⇨ (1,3)").isEqualTo("(0,2) ⇨ (1,3)");
        assertThat(splitOutput[7]).as("Ball should bounce from (1,3) ⇨ (4,0)").isEqualTo("(1,3) ⇨ (4,0)");
        assertThat(splitOutput[8]).as("Ball should bounce from (4,0) ⇨ (7,3)").isEqualTo("(4,0) ⇨ (7,3)");                                                                           
    }                                                                                                                                                                                                                                      
}
