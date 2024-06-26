package co.udea.reservation_service;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features"},
        plugin = {"json:target/cucumber.json", "pretty",
                "html:target/cucumber-reports"},
        glue = {"co.udea.reservation_service.service.steps"})
public class RunCucumberTests {

}
