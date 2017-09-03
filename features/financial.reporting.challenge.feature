Feature: Problem Set 01, Financial Reporting Challenge

    Scenario: Desired output

        When I run the Financial Reporting Challenge
        Then the console should show a properly formatted '(Decrease) increase in cash' entry on the first line
        And the console should show a properly formatted 'Cash, beginning of year' entry on the second line
        And the console should show a properly formatted 'Cash, end of year' entry on the third line