package com.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:target/cucumber-html-report",
                "junit:target/cucumber-junit.xml",
                "json:target/cucumber.json",
                "pretty:target/cucumber-pretty.txt",
                "usage:target/cucumber-usage.json"
        },
        features = {"src/test/java/com/features/"},
        //tags = {"@current"},
        glue = {"com/steps"}
)
public class BddTest {
}
