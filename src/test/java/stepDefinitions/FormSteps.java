package stepDefinitions;

import dataObjects.FormEntry;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static pageObjects.FormPage.*;

public class FormSteps {
    @Given("I open the contact page")
    public void iOpenTheContactPage() {
        openHomepage();
    }

    @And("I reject the cookies")
    public void iRejectTheCookies() {
        rejectCookies();
    }

    @And("I open the form tab")
    public void openTheFormTab() {
        clickFormButton();
    }

    @When("I fill in the form fields with random data")
    public void iFillInTheFormFieldsWithRandomData() {
        FormEntry formEntry = new FormEntry();

        fillName(formEntry.getName());
        fillPersonalCodeField(formEntry.getPersonalCode());
        fillIssueNumberField(formEntry.getIssueNumber());
        fillContactNumberField(formEntry.getContactNumber());
        fillEmailAddressField(formEntry.getEmailAddress());
        fillPersonalAddressField(formEntry.getPersonalAddress());
        fillCommentField(formEntry.getComment());
        chooseResponseOptionEmail(formEntry.getResponseOption());
    }

    @And("I submit the form")
    public void iSubmitTheForm() {
        submitForm();
    }

    @Then("the form is submitted")
    public void theFormIsSubmitted() {
        // assertions for what would happen if form was submitted
        // TODO: verify acceptance criteria by using assertions against the page
    }

    @And("I submit the form but not really")
    public void iSubmitTheFormButNotReally() {
        // Skipping to avoid spam
    }

    @Then("I see a message in red under every mandatory field of the form")
    public void iSeeAMessageInRedUnderEveryMandatoryFieldOfTheForm() {
        validateMandatoryFieldErrors();
    }
}