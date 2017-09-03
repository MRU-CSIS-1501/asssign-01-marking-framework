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


public class FinancialReportingChallengeSteps {
	
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


    @When("^I run the Financial Reporting Challenge$")
    public void iRunTheFinancialReportingChallenge() throws Throwable {
        System.setOut(new PrintStream(OUT_CONTENT, true, "UTF-8"));

        Class.forName("ProblemSetMain").getMethod("runFinancialReportingChallenge").invoke(null);

        splitOutput = OUT_CONTENT.toString("UTF-8").split("\\R+");
    }


    @Then("^the console should show a properly formatted '\\(Decrease\\) increase in cash' entry on the first line$") 
    public void theConsoleShouldShowAProperlyFormattedDecreaseIncreaseInCashEntryOnTheFirstLine() throws Throwable {  
        final String targetSentence = "(Decrease) increase in cash        (24,531)          2,553 ";
        final String solutionOutput = splitOutput[0];

        assertThat(solutionOutput)
            .as("Expected '%s', instead got: '%s'", targetSentence, solutionOutput)
            .isEqualTo(targetSentence);    
    }                                                                                                                 
                                                                                                                  
    @Then("^the console should show a properly formatted 'Cash, beginning of year' entry on the second line$")        
    public void theConsoleShouldShowAProperlyFormattedCashBeginningOfYearEntryOnTheSecondLine() throws Throwable {    
        final String targetSentence = "Cash, beginning of year             51,850          49,297 ";                                                                                                                        
        final String solutionOutput = splitOutput[1];

        assertThat(solutionOutput)
            .as("Expected '%s', instead got: '%s'", targetSentence, solutionOutput)
            .isEqualTo(targetSentence);                                                                                     
    }                                                                                                                 
                                                                                                                  
    @Then("^the console should show a properly formatted 'Cash, end of year' entry on the third line$")               
    public void theConsoleShouldShowAProperlyFormattedCashEndOfYearEntryOnTheThirdLine() throws Throwable {           
        final String targetSentence = "Cash, end of year             $     27,319    $     51,850 ";
        final String solutionOutput = splitOutput[2];

        assertThat(solutionOutput)
            .as("Expected '%s', instead got: '%s'", targetSentence, solutionOutput)
            .isEqualTo(targetSentence);                                                                                     
    }
                                                                                                                                                                                                               
}
