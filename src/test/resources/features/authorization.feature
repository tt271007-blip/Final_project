Feature: User authorization

  Scenario: Successful authorization of a registered user
    Given a registered user exists
    When the user logs in with valid credentials
    Then the user is successfully authorized