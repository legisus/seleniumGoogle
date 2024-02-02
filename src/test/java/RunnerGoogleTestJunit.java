import io.cucumber.core.cli.Main;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features/",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        tags = "@googleFinance")
public class RunnerGoogleTestJunit {
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
