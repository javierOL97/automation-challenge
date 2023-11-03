package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericActions {

	private WebDriver driver;
	private final int maxWaitTime = 20;

	public GenericActions(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getWebElement(By locator) {
		return driver.findElement(locator);
	}

	// WaitMethods

	public void waitForElementPresence(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(maxWaitTime));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void waitForElementClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(maxWaitTime));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void waitForElementClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(maxWaitTime));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementVisibility(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(maxWaitTime));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForTextPresentInElement(By locator, String text) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(maxWaitTime));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
	}

	public void waitForElementInvisibility(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(maxWaitTime));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public void waitForElementInvisibility(By locator, int time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public void waitForStalenessOfElement(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(maxWaitTime));
		wait.until(ExpectedConditions.stalenessOf(driver.findElement(locator)));
	}

	public void waitForConfirmationMessage(By locator) {
		waitForElementVisibility(locator);
		waitForElementInvisibility(locator);
	}
	
	public void wait(int time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// GetProperties
	public String getProperty(String property) {
		FileInputStream fis;
		Properties prop = new Properties();
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "//src//test//resources//global.properties");

			prop.load(fis);
			return prop.getProperty(property);
		} catch (IOException e) {
			e.printStackTrace();
			driver.quit();
		}
		return prop.getProperty(property);
	}

	// PopulateField
	public void populateTextField(By locator, String text) {
		waitForElementClickable(locator);
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(text);
	}

	// ClickMethods
	public void clickElement(By locator) {
		waitForElementClickable(locator);
		driver.findElement(locator).click();
	}

	public void clickElement(WebElement element) {
		waitForElementClickable(element);
		element.click();
	}

	public void moveToWebElement(WebElement target) {
		waitForElementClickable(target);
		Actions action = new Actions(driver);
		action.moveToElement(target).perform();
		action.pause(800).perform();
	}

	public void moveToWebElement(By target) {
		waitForElementClickable(target);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(target)).perform();
		action.pause(800).perform();
	}

	// Select dropdown value

	public void selectDropDownValueByText(By locator, String text) {
		waitForElementClickable(locator);
		WebElement element = driver.findElement(locator);

		Select dropDown = new Select(element);
		boolean isOptionSelected = dropDown.getFirstSelectedOption().getText().equalsIgnoreCase(text);

		while (!isOptionSelected) {
			dropDown.selectByVisibleText(text);
			isOptionSelected = (dropDown.getFirstSelectedOption().getText().equalsIgnoreCase(text));
		}

	}

	public boolean isOptionSelected(By locator, String text) {
		Select select = new Select(getWebElement(locator));
		WebElement option = select.getFirstSelectedOption();

		boolean response = (text.equalsIgnoreCase(option.getText())) ? true : false;
		return response;
	}

}
