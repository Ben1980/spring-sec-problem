Feature: Login

  Background:
    Given a registered user with the username "user"
    And the user has the password "password"
    And the user has opened the login page

  Scenario: Successful Login with username
    When the user enters a valid username
    And the user enters a valid password
    And the user clicks the login button
    Then the user should be forwarded to the users index.html