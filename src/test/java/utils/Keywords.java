package utils;

public enum Keywords {

	EMAIL("email"),
	PASSWORD("password"),
	URL("url"),
	BROWSER("browser"),
	CHROME("chrome"),
	FEMALE("female"),
	NAME("Name"),
	LNAME("LastName"),
	DOB("DoB"),
	GENDER("Gender"),
	ZIPCODE("ZipCode"),
	STATE("State"),
	CITY("City"),
	MUNICIPALITY("Municipality"),
	SETTLEMENT("Settlement"),
	STREET("Street"),
	NUMBER("Number"),
	ALIAS("TestAddress"),
	CELLNUMBER("6691794419"),
	LADA("669"),
	PHONENUMBER("1794419"),
	CVV("111")
	;
	
	private final String value;
	
	private Keywords(final String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
