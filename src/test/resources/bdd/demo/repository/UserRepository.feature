Feature: UserRepository
  Scenario: Find a user by name
    Given user "test" exists in the database
    When you search for user "test"
    Then the user is found
    And the user name is "test"

  Scenario: Find a user that does not exist
    Given user "test" does not exist in the database
    When you search for user "test"
    Then the user is not found
