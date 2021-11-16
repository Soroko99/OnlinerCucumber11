package Onliner.test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features ={"src/test/resources/TVFilter.feature"},
        glue = "Onliner/steps",
        plugin = {"pretty","html:target/cucumber.html",
                "json:target/cucumber.json","json:target/cucumber-reports/CucumberTestReport.json"}
)
public class TestRun extends AbstractTestNGCucumberTests {
}