package dataObjects;

import java.util.Random;
import com.github.javafaker.Faker;

public class FormEntry {
    private final Faker faker = new Faker();
    private final String name;
    private final String personalCode;
    private final String issueNumber;
    private final String contactNumber;
    private final String emailAddress;
    private final String personalAddress;
    private final String comment;
    private final String responseOption;

    public FormEntry() {
        this.name = randomName();
        this.personalCode = randomPersonalCode();
        this.issueNumber = randomIssueNumber();
        this.contactNumber = randomContactNumber();
        this.emailAddress = randomEmailAddress();
        this.personalAddress = randomPersonalAddress();
        this.comment = randomComment();
        this.responseOption = randomResponseOption();
    }

    private String randomName() {
        return faker.name().fullName();
    }

    private String randomPersonalCode() {
        return faker.idNumber().valid();
    }

    private String randomIssueNumber() {
        return faker.number().digits(4);
    }

    private String randomContactNumber() {
        return faker.phoneNumber().cellPhone();
    }

    private String randomEmailAddress() {
//        return faker.internet().emailAddress();
        return faker.number().digits(8); // intentionally putting wrong format to avoid accidental spam
    }

    private String randomPersonalAddress() {
        return faker.address().fullAddress();
    }

    private String randomComment() {
        return faker.lorem().sentence();
    }

    private String randomResponseOption() {
        Random random = new Random();
        return random.nextInt(2) == 0 ? "E-pasts" : "Pasts";
    }

    public String getName() {
        return name;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPersonalAddress() {
        return personalAddress;
    }

    public String getComment() {
        return comment;
    }

    public String getResponseOption() {
        return responseOption;
    }
}
