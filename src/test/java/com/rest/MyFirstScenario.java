package com.rest;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MyFirstScenario {

	@Given("^some context$")
	public void user() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^some action is carried out$")
	public void navigatesTo(String url) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^a particular set of observable consequences should obtain$")
	public void mainPageIsDisplayed() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}
}