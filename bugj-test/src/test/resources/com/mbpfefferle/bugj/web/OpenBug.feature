Feature: Open a Bug
    Scenario: Open a valid bug report
        Given I'm at the new bug form
        When I enter text into the fields
            And I click submit
        Then I should be redirected to the bug detail page
