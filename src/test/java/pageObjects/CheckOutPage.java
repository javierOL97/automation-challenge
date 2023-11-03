package pageObjects;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.GenericActions;
import utils.Keywords;

public class CheckOutPage {

	private GenericActions actions;

	public CheckOutPage(WebDriver driver, GenericActions actions) {
		this.actions = actions;
	}

	public By changeDeliverButton = By.id("opc_addressButton_select");
	public By cvv = By.id("cvv");
	public By finalizePurchaseButton = By.id("submitForOther");

	// Address Form Locators
	public By addAddressButton = By.xpath("//button[contains(text(),'Agregar Direcci')]");
	public By addressAliasField = By.cssSelector("input[labeltext='Alias de la dirección']");
	public By aliasField = By.cssSelector("input[name='shortName']");
	public By zipCodeField = By.cssSelector("input[name='postalCode']");
	public By stateField = By.cssSelector("select[name='stateId']");
	public By cityField = By.cssSelector("input[name='city']");
	public By municipalityField = By.cssSelector("select[name='municipality']");
	public By settlementField = By.cssSelector("select[name='colony']");
	public By streetField = By.cssSelector("input[name='street']");
	public By houseNumberField = By.cssSelector("input[name='noExt']");
	public By cellNumberField = By.cssSelector("input[name='cellphone']");
	public By ladaNumberField = By.cssSelector("input[name='LADA']");
	public By phoneNumberField = By.cssSelector("input[name='phone']");
	public By saveAddressButton = By.cssSelector("div[id^='opc_modal_editAddressButton' ] + div");
	public By adressSuccessMessage = By
			.xpath("//div[contains(text(),'La fecha estimada de entrega de tus productos fue actualizada')]");

	public void populateShippingAddress(Map<String, String> customer) {
		actions.waitForElementVisibility(aliasField);
		
		// Populating all shipping fields
		actions.populateTextField(aliasField, Keywords.ALIAS.toString());
		actions.populateTextField(zipCodeField, customer.get(Keywords.ZIPCODE.toString()));
		actions.wait(2000);
		actions.selectDropDownValueByText(stateField, customer.get(Keywords.STATE.toString()).toUpperCase());
		actions.populateTextField(cityField, customer.get(Keywords.CITY.toString()));
		actions.selectDropDownValueByText(municipalityField, customer.get(Keywords.MUNICIPALITY.toString()).toUpperCase());
		actions.selectDropDownValueByText(settlementField, customer.get(Keywords.SETTLEMENT.toString()).toUpperCase());
		actions.populateTextField(streetField, customer.get(Keywords.STREET.toString()));
		actions.populateTextField(houseNumberField, customer.get(Keywords.NUMBER.toString()));
		actions.populateTextField(cellNumberField, Keywords.CELLNUMBER.toString());
		actions.populateTextField(ladaNumberField, Keywords.LADA.toString());
		actions.populateTextField(phoneNumberField, Keywords.PHONENUMBER.toString());
		
		actions.clickElement(saveAddressButton);
		actions.waitForConfirmationMessage(adressSuccessMessage);
	}
	
	public void populateCVV() {
		actions.waitForElementVisibility(cvv);
		actions.populateTextField(cvv, Keywords.CVV.toString());
	}
}
