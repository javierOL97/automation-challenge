package pageObjects;

import org.openqa.selenium.WebDriver;

import utils.GenericActions;

public class PageObjectsManager {

	private WebDriver driver;
	public HomePage homePage;
	public LogInPage logInPage;
	public CheckOutPage checkOutPage;
	public GenericActions actions;
	
	public PageObjectsManager(WebDriver driver, GenericActions actions) {
		this.driver = driver;
		this.actions = actions;
	}
	
	public HomePage getHomePage() {
		return homePage = new HomePage(driver, actions);
	}
	
	public LogInPage getLogInPage() {
		return logInPage = new LogInPage(driver, actions);
	}
	
	public CheckOutPage getCheckOutPage() {
		return checkOutPage = new CheckOutPage(driver, actions);
	}
	
}
