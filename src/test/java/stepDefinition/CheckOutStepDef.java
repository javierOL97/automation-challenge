package stepDefinition;

import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.CheckOutPage;
import pageObjects.HomePage;
import pageObjects.LogInPage;
import utils.ContextSetup;
import utils.FWLogger;

public class CheckOutStepDef {

	
	ContextSetup base;
	LogInPage loginPage;
	HomePage homePage;
	CheckOutPage checkOutPage;

	public CheckOutStepDef(ContextSetup base) {
		this.base = base;
		this.loginPage = base.pageObjectsManager.getLogInPage();
		this.homePage = base.pageObjectsManager.getHomePage();
		this.checkOutPage = base.pageObjectsManager.getCheckOutPage();
	}
	
	
	@And("User is able to setup a shipping address")
	public void user_is_able_to_setup_a_shipping_address(DataTable addressData) {
		Map<String, String> address = addressData.asMap(String.class, String.class);
		base.actions.waitForElementVisibility(checkOutPage.changeDeliverButton);
		base.actions.clickElement(checkOutPage.changeDeliverButton);
		base.actions.waitForElementVisibility(checkOutPage.addAddressButton);
		base.actions.clickElement(checkOutPage.addAddressButton);
		checkOutPage.populateShippingAddress(address);
	}

	@Then("User selects a payment method")
	public void user_selects_a_payment_method() {
		checkOutPage.populateCVV();
	}

	@And("User completes the purchase")
	public void user_completes_the_purchase() {
		FWLogger.info("******************************************************************");
		FWLogger.info("------------------------------------------------------------------");
		FWLogger.info("NOT PRESSING FINALIZE PURCHASE DUE THIS IS A PRODUCTIVE ENVIRONMENT");
		FWLogger.info("------------------------------------------------------------------");
		FWLogger.info("******************************************************************");
	}
	
	
}
