package stepDefinition;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import utils.ContextSetup;

public class Hooks {

	ContextSetup base;
	
	public Hooks(ContextSetup base) {
		this.base = base;
	}

	@After
	public void afterScenario() throws IOException {
		base.testBase.WebDriverManager().quit();
	}
	
	@AfterStep
	public void AddScreenshot(Scenario scenario) throws IOException {
		WebDriver driver = base.testBase.WebDriverManager();
		if(scenario.isFailed()) {
			File sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
			scenario.attach(fileContent, "image/png", "image");
		}
		
	}
}
