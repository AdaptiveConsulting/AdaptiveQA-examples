package com.BiBox.features;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitions {
    public Actionwords actionwords = new Actionwords();

    @Given("^users exist$")
    public void usersExist() {
        actionwords.usersExist();
    }

    @When("^Super Admin deletes a user$")
    public void superAdminDeletesAUser() {
        actionwords.superAdminDeletesAUser();
    }

    @Then("^the user should be removed from the application$")
    public void theUserShouldBeRemovedFromTheApplication() {
        actionwords.theUserShouldBeRemovedFromTheApplication();
    }

    @Given("^users form exists$")
    public void usersFormExists() {
        actionwords.usersFormExists();
    }

    @When("^Super Admin creates a new user$")
    public void superAdminCreatesANewUser() {
        actionwords.superAdminCreatesANewUser();
    }

    @Then("^a new user should be created$")
    public void aNewUserShouldBeCreated() {
        actionwords.aNewUserShouldBeCreated();
    }

    @When("^super admin submits a change$")
    public void superAdminSubmitsAChange() {
        actionwords.superAdminSubmitsAChange();
    }

    @Then("^change is submitted$")
    public void changeIsSubmitted() {
        actionwords.changeIsSubmitted();
    }

    @Then("^changes are applied to user account$")
    public void changesAreAppliedToUserAccount() {
        actionwords.changesAreAppliedToUserAccount();
    }

    @Then("^users are updated$")
    public void usersAreUpdated() {
        actionwords.usersAreUpdated();
    }
}