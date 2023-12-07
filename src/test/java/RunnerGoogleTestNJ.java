import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "classpath:features/",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        tags = "@googleFinance")
public class RunnerGoogleTestNJ extends AbstractTestNGCucumberTests{
}
