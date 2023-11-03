package pageObjects;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.FWLogger;
import utils.GenericActions;

public class HomePage {

	private WebDriver driver;
	private GenericActions actions;

	public HomePage(WebDriver driver, GenericActions actions) {
		this.driver = driver;
		this.actions = actions;
	}

	// Liverpool Landing Page
	public By liverpoolLogo = By.cssSelector("img[title='Liverpool Logo']");
	public By searchBar = By.id("mainSearchbar");
	public By searchButton = By.cssSelector("button[class='input-group-text']");
	public By logInHomePageButton = By.xpath("//span[contains(text(),'Iniciar sesi')]");

	// Brands filter
	public By viewMoreBrands = By.id("Marcas");
	public By searchBarBrandFilter = By.id("search-filter-brands");
	public By brandCheckbox = By.cssSelector("div[id^='MarcascountViewMore'] > div > div");

	// Price range filter
	public By minPrice = By.id("min-price-filter");
	public By maxPrice = By.id("max-price-filter");
	public By enterPriceButton = By.className("a-price__filterButton");

	// Applied Filters
	public By currentAppliedFilters = By.cssSelector("div[class='mdc-chip__text'");

	// Products in search results
	public By productTitle = By.cssSelector("h5[class='card-title a-card-description']");

	// Detailed product page
	public By selectedProductTitle = By.cssSelector("h1[class='a-product__information--title']");
	public By buyNowButton = By.id("opc_pdp_buyNowButton");

	// Home page functions
	public void verifyMainPageIsLoaded() {
		try {
			actions.waitForElementVisibility(liverpoolLogo);
		} catch (Exception e) {
			String failMessage = "Error: Main page did not load correctly";
			FWLogger.error(failMessage);
			Assert.fail(failMessage);
		}
	}

	public void searchAProduct(String product) {
		actions.populateTextField(searchBar, product);
		actions.clickElement(searchButton);
		// Verifying search is performed correctly
		actions.waitForElementVisibility(productTitle);
	}

	public void verifyIfFilterIsApplied(String filterTitle) {
		
		actions.wait(2500);
		actions.waitForElementPresence(currentAppliedFilters);
		
		List<WebElement> appliedFilters = driver.findElements(currentAppliedFilters);
		boolean isFilterApplied = false;

		for (WebElement webElement : appliedFilters) {
			if (webElement.getText().equalsIgnoreCase(filterTitle)) {
				isFilterApplied = true;
				break;
			}
		}
		if (!isFilterApplied) {
			String failMessage = "Error: Brand filter was not applied correctly ";
			FWLogger.error(failMessage);
			Assert.fail(failMessage);
		}
	}

	public void filterProductByBrand(String brand) {
		actions.clickElement(viewMoreBrands);
		actions.populateTextField(searchBarBrandFilter, brand);
		actions.clickElement(brandCheckbox);
		verifyIfFilterIsApplied(brand);
	}

	public void filterProductByPriceRange(String min, String max) {
		actions.populateTextField(minPrice, min);
		actions.populateTextField(maxPrice, max);
		actions.clickElement(enterPriceButton);
		
		//Creating the price range text style
		String priceRange = "$"+min+".0 -$"+max+".0";
		verifyIfFilterIsApplied(priceRange);
	}
	
	public void selectingAProduct(String productToSelect) {
		actions.waitForElementVisibility(productTitle);
		
		List<WebElement> filteredProducts = driver.findElements(productTitle);
		for (WebElement webElement : filteredProducts) {
			if(webElement.getText().equalsIgnoreCase(productToSelect)) {
				actions.waitForElementClickable(webElement);
				actions.clickElement(webElement);
				break;
			}
		}
		
	}

}
