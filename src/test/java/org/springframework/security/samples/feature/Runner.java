package org.springframework.security.samples.feature;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = { "pretty", "html:build/reports/cucumber" },
        glue = "org.springframework.security.samples.feature.steps",
        features = "src/test/resources/feature/"
)
@ContextConfiguration
public class Runner {}