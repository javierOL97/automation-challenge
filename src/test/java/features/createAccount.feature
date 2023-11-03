Feature: Create a Liverpool account

	Background:
		Given User navigates to Liverpool homepage
		And Navigates to Create Account Page
	
	@ApexTest
	Scenario: User is not allowed to create an account due to invalid email
		When User enters "fcoortiz972@gmail.com" in email field and "GoodPwd@1" in password field
		Then User clicks on Create account button
		And System gives us an error message saying "El correo electrónico ya ha sido registrado"
		
		@ApexTest
	Scenario: User is not allowed to create an account due to invalid password
		When User enters "valid@test.com" in email field and "wrong" in password field
		Then User clicks on Create account button
		And System show us the requirements for a valid password

		@ApexTest
	Scenario: User is allowed to create a new account
		When User enters "valid21@test.com" in email field and "GoodPwd@1" in password field
		Then User clicks on Create account button
		And User enters their data in order to complete account creation
			|Name		|John|
      |LastName|Doe|
      |DoB 		|30/Ago/1997|
      |Gender |Male|
 
		
		