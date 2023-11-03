Feature: Buying a product
	
	@ApexTest1
	Scenario: User is able to select and buy a product
		Given User navigates to Liverpool homepage
		And User login succesfully 
		When User search for a "Television" in the search bar
		And User selects a price range between "5000" and "12500"
		And User selects "Samsung" in brand filter
		And User selects "Pantalla Smart TV Samsung LED de 55 pulgadas HD Un55cu8000fxzx con Tizen" product from search list
		And User clicks Buy Now button and is navigated to checkout page
		And User is able to setup a shipping address
      |ZipCode|82128|
      |State 	|Sinaloa|
      |City  	|Mazatlan|
      |Municipality |Mazatlan|
      |Settlement|Los Mangos I|
      |Street |Fake Street|
      |Number |608|
		Then User selects a payment method
		And User completes the purchase
		
		