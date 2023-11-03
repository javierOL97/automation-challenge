package stepDefinition;

import io.cucumber.java.en.*;
import pageObjects.CheckOutPage;
import pageObjects.HomePage;
import pageObjects.LogInPage;
import utils.ContextSetup;

public class HomePageStepDef {
	ContextSetup base;
	HomePage homePage;
	CheckOutPage checkOutPage;
	LogInPage loginPage;
	
	public HomePageStepDef(ContextSetup base) {
		this.base = base;
		this.homePage = base.pageObjectsManager.getHomePage();
		this.checkOutPage = base.pageObjectsManager.getCheckOutPage();
		this.loginPage = base.pageObjectsManager.getLogInPage();
	}

	@Given("User navigates to Liverpool homepage")
	public void user_navigates_to_liverpool_homepage() {
		homePage.verifyMainPageIsLoaded();
	}

	@When("User search for a {string} in the search bar")
	public void user_search_for_a_in_the_search_bar(String searchKeywords) {
		homePage.searchAProduct(searchKeywords);
	}

	@And("User selects {string} in brand filter")
	public void user_selects_in_brand_filter(String brand) {
		homePage.filterProductByBrand(brand);
	}

	@And("User selects a price range between {string} and {string}")
	public void user_selects_a_price_range_between_and(String minorRange, String maxRange) {
		homePage.filterProductByPriceRange(minorRange, maxRange);
	}

	@And("User selects {string} product from search list")
	public void user_selects_product_from_search_list(String productTitle) {
		homePage.selectingAProduct(productTitle);
	}

	@And("User clicks Buy Now button and is navigated to checkout page")
	public void user_clicks_buy_now_button_and_is_navigated_to_checkout_page() {
		base.actions.clickElement(homePage.buyNowButton);
		base.actions.waitForElementPresence(checkOutPage.changeDeliverButton);
	}


}
