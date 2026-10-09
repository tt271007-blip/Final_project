Feature: Advertisement creation

  Scenario: Successful creation of an advertisement
    Given a registered user exists
    When the user logs in with valid credentials
    And the user creates a new advertisement
    Then the advertisement is displayed

    Scenario: Successful editing of an own advertisement
        Given a registered user exists
        When the user logs in with valid credentials
        And the user creates a new advertisement
        And the user edits the advertisement
        Then the updated advertisement is displayed

    Scenario: Successful deletion of an own advertisement
      Given a registered user exists
      When the user logs in with valid credentials
      And the user creates a new advertisement
      And the user deletes the advertisement
      Then the advertisement is no longer displayed