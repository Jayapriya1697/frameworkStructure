package com.frameworkstructure.myproject;

import com.frameworkstructure.myproject.utility.ExtentManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Runner {


    @CucumberOptions(
            features = "src/test/resources/features",
            glue = "stepDefinitions",
            plugin = {"pretty", "html:target/cucumber-reports.html"},
            monochrome = true
    )
    public class TestRunner extends AbstractTestNGCucumberTests {
        @BeforeClass
        public void setup() {
            ExtentManager.getStart();
        }

        @AfterClass
        public void tearDown() {
            ExtentManager.flush();
        }
    }

}
