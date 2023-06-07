@contact-form
Feature: Contact form

  @smoke
  Scenario: As a user I am able to fill and submit the contact form
    Given I open the contact page
    And I reject the cookies
    And I open the form tab
    When I fill in the form fields with random data
    And I submit the form but not really
    Then the form is submitted

  @negative
  Scenario: As a user I see errors when submitting an empty contact form
    Given I open the contact page
    And I reject the cookies
    And I open the form tab
    When I submit the form
    Then I see a message in red under every mandatory field of the form