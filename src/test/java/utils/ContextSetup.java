package utils;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import pageObjects.PageObjectsManager;

public class ContextSetup {
	
	public WebDriver driver;
	public PageObjectsManager pageObjectsManager;
	public TestBase testBase;
	public GenericActions actions;
	
	public ContextSetup() throws IOException {
		this.testBase = new TestBase();
		this.actions = new GenericActions(testBase.WebDriverManager());
		this.pageObjectsManager = new PageObjectsManager(testBase.WebDriverManager(), actions);	
	
	}
	
	
}
