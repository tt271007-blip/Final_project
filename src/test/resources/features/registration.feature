Feature: User registration

  Scenario: Successful registration of a new user
    Given the user opens the home page
    When the user registers with unique data
    Then the user is successfully registered

  Scenario: Registration with an already registered email
    Given a registered user exists
    When the user tries to register again with the same email
    Then the error "Ошибка" is displayed