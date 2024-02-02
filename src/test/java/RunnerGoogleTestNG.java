import io.cucumber.core.cli.Main;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "classpath:features/",
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json"},
        tags = "@googleFinance")
public class RunnerGoogleTestNG extends AbstractTestNGCucumberTests {
    public static void main(String[] args) {
        Main.main(new String[]{
                "-g", "com.selenium.steps",
                "-p", "pretty",
                "-p", "html:target/cucumber-reports.html",
                "-p", "json:target/cucumber.json",
                "--tags", "@googleFinance",
                "classpath:features/"
        });
    }
}
