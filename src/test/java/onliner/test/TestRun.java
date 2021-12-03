package onliner.test;

import framework.TestListener;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
@CucumberOptions(
        features ={"src/test/java/onliner/feature/TVFilter.feature"},
        glue = "onliner/steps",
        plugin = {"pretty","html:target/cucumber.html",
                "json:target/cucumber.json","json:target/cucumber-reports/CucumberTestReport.json",
        }
)
public class TestRun extends AbstractTestNGCucumberTests {
}