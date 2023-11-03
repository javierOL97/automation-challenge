package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/features/"},
				 glue = { "stepDefinition" },
				 monochrome = true,
				 tags = "@ApexTest",
				 plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
				 dryRun=false)
public class ApexTestRunner {

}
