package stepDefinition;


import java.util.Map;

import org.junit.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import pageObjects.CheckOutPage;
import pageObjects.HomePage;
import pageObjects.LogInPage;
import utils.ContextSetup;
import utils.FWLogger;
import utils.Keywords;

public class LogInPageStepDef {

	ContextSetup base;
	LogInPage loginPage;
	HomePage homePage;
	CheckOutPage checkOutPage;

	public LogInPageStepDef(ContextSetup base) {
		this.base = base;
		this.loginPage = base.pageObjectsManager.getLogInPage();
		this.homePage = base.pageObjectsManager.getHomePage();
		this.checkOutPage = base.pageObjectsManager.getCheckOutPage();
	}

	@And("User login succesfully")
	public void user_login_succesfully() {
		String email = base.actions.getProperty(Keywords.EMAIL.toString());
		String pwd = base.actions.getProperty(Keywords.PASSWORD.toString());
		
		base.actions.clickElement(homePage.logInHomePageButton);
		loginPage.logInLiverpool(email, pwd);
		//Waiting for manually code introduction
		base.actions.waitForElementInvisibility(loginPage.enterCodeField, 150);
		base.actions.waitForElementVisibility(homePage.searchBar);
		base.actions.waitForElementInvisibility(homePage.logInHomePageButton);
	}

	@And("Navigates to Create Account Page")
	public void navigates_to_create_account_page() {
		base.actions.clickElement(homePage.logInHomePageButton);
		base.actions.clickElement(loginPage.createAccountHyperlink);
		base.actions.waitForElementPresence(loginPage.emailCreateAccount);
	}

	@When("User enters {string} in email field and {string} in password field")
	public void user_enters_in_email_field_and_in_password_field(String email, String password) {
		loginPage.enterNewAccountEmailAndPwd(email, password);
	}

	@Then("User clicks on Create account button")
	public void user_clicks_on_create_account_button() {
		loginPage.submitCreateAccountButton();
	}

	@And("System gives us an error message saying {string}")
	public void system_gives_us_an_error_message_saying(String expectedMessage) {
		base.actions.waitForElementVisibility(loginPage.emailAlreadyRegistered);
		String actualMessage = base.actions.getWebElement(loginPage.emailAlreadyRegistered).getText();
		
		if(!expectedMessage.equalsIgnoreCase(actualMessage)) {
			String failMessage = "Expected Message: "+expectedMessage+" Actual Message: "+actualMessage;
			FWLogger.error(failMessage);
			Assert.fail(failMessage);
		}
		FWLogger.info(actualMessage);
		
	}

	@And("System show us the requirements for a valid password")
	public void system_show_us_the_requirements_for_a_valid_password() throws InterruptedException {
		base.actions.clickElement(loginPage.createAccountButton);
		base.actions.waitForElementVisibility(loginPage.createAccountButton);
	}

	@And("User enters their data in order to complete account creation")
	public void user_enters_their_data_in_order_to_complete_account_creation(DataTable customerTable) {
		//List<List<String>> table = customerData.cells();
		Map<String, String> customer = customerTable.asMap(String.class, String.class);
		
		loginPage.populateNewCustomerTextFields(customer.get(Keywords.NAME.toString()),
				customer.get(Keywords.LNAME.toString()));
		loginPage.selectNewCustomerDoB(customer.get(Keywords.DOB.toString()));
		loginPage.selectGender(customer.get(Keywords.GENDER.toString()));
		FWLogger.info("**************************************************");
		FWLogger.info("**************************************************");
		FWLogger.info("TEST CASE END AS IT IS A PRODUCTIVE ENVIRONMENT");
		FWLogger.info("**************************************************");
		FWLogger.info("**************************************************");
	}

}
