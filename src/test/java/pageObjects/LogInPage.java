package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.GenericActions;
import utils.Keywords;

public class LogInPage {

	private WebDriver driver;
	private GenericActions actions;
	
	public LogInPage(WebDriver driver, GenericActions actions) {
		this.driver = driver;
		this.actions = actions;
	}
	
	//LogIn Locators
	public By emailLogInTextField = By.id("username");
	public By pwdTextField = By.id("password");
	public By logInBtn = By.xpath("//button[contains(text(),'Iniciar sesi')]");
	public By enterCodeField = By.id("code");
	
	//Create Account Locators
	public By createAccountHyperlink = By.xpath("//a[contains(text(),'Crear cuenta')]");
	public By createAccountButton = By.xpath("//button[contains(text(), 'Crear cuenta')]");
	public By emailCreateAccount = By.id("email");
	public By emailAlreadyRegistered = By.cssSelector("#error-element-email");
	public By customerName = By.id("input-user__name");
	public By customerLastName = By.id("input-user__apaterno");
	public By dobDay = By.id("daySelectorLabel");
	public By dobMonth = By.id("monthSelectorLabel");
	public By dobYear = By.id("yearSelectorLabel");
	public By genderFemale = By.id("female");
	public By genderMale = By.id("male");
	
	
	
	public void logInLiverpool(String email, String pwd) {
		actions.populateTextField(emailLogInTextField, email);
		actions.populateTextField(pwdTextField, pwd);
		
		List<WebElement> logInButton = driver.findElements(logInBtn);
		actions.clickElement(logInButton.get(1));
	}
	
	public void enterNewAccountEmailAndPwd(String email, String pwd) {
		actions.waitForElementVisibility(emailCreateAccount);
		actions.populateTextField(emailCreateAccount, email);
		actions.populateTextField(pwdTextField, pwd);
	}
	
	public void submitCreateAccountButton() {
		actions.clickElement(createAccountButton);
	}
	
	public void populateNewCustomerTextFields(String name, String lname) {
		actions.clickElement(customerName);
		actions.populateTextField(customerName, name);
		actions.clickElement(customerLastName);
		actions.populateTextField(customerLastName, lname);
	}
	
	public void selectNewCustomerDoB(String dob) {
		dob = dob.replace("/", "");
		String day = dob.substring(0,2);
		String month = dob.substring(2,5);
		String year = dob.substring(5, dob.length());
		
		actions.selectDropDownValueByText(dobDay, day);
		actions.selectDropDownValueByText(dobMonth, month);
		actions.selectDropDownValueByText(dobYear, year);
	}
	
	public void selectGender(String gender) {
		if(gender.equalsIgnoreCase(Keywords.FEMALE.toString())) 
			actions.getWebElement(genderFemale).click();
		else 
			actions.getWebElement(genderMale).click();
	}
}
