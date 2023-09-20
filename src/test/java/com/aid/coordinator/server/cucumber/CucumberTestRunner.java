package com.aid.coordinator.server.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "classpath:create_user.feature",
    glue = "com.aid.coordinator.server.cucumber"
)
public class CucumberTestRunner {

}
