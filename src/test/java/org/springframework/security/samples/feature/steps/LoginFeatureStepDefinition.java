package org.springframework.security.samples.feature.steps;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration
@SpringBootTest
public class LoginFeatureStepDefinition {
    private String username;
    private String password;
    private WebClient webDriver;
    private HtmlPage page;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        webDriver = MockMvcWebClientBuilder
                .webAppContextSetup(context)
                .build();
    }

    @After
    public void tearDown() {
        webDriver.close();
    }

    @Given("^a registered user with the username \"([^\"]*)\"$")
    public void aRegisteredUserWithTheUsername(String username) {
        this.username = username;

        assertThat(this.username).isNotNull();
        assertThat(this.username).isNotEmpty();
    }

    @And("^the user has the password \"([^\"]*)\"$")
    public void theUserHasThePassword(String password) {
        this.password = password;

        assertThat(this.password).isNotNull();
        assertThat(this.password).isNotEmpty();
    }

    @Given("^the user has opened the login page$")
    public void theUserHasOpenedTheLoginPage() throws IOException {
        page = webDriver.getPage("http://localhost:8080/login");
        assertThat(page.getUrl().toString()).isEqualTo("http://localhost:8080/login");
    }

    @When("^the user enters a valid username$")
    public void theUserEntersAValidUsername() {
        HtmlTextInput input = (HtmlTextInput)page.getElementById("username");
        input.setText(username);
        assertThat(input.getText()).isEqualTo(username);
    }

    @And("^the user enters a valid password$")
    public void theUserEntersAValidPassword() {
        HtmlPasswordInput passwd = (HtmlPasswordInput)page.getElementById("password");
        passwd.setText(password);
        assertThat(passwd.getText()).isEqualTo(password);
    }

    @And("^the user clicks the login button$")
    public void theUserClicksTheLoginButton() throws IOException {
        page = page.getElementById("login").click();
    }

    @Then("^the user should be forwarded to the users index.html$")
    public void theUserShouldBeForwardedToTheUsersDashboard() {
        assertThat(page.getUrl().toString()).isEqualTo("http://localhost:8080/user/index");
    }
}
