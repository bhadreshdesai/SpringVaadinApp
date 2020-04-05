Feature: PersonRepository
  Background: People in the system
    Given the following people are in the system
      | firstName  | lastName  | dob        |
      | Peter      | Pan       | 1971-02-02 |
      | Joe        | Bloggs    | 1972-02-02 |
      | Jane       | Doe       | 1973-03-03 |

  Scenario: Find a person by name and surname
    When you search for a person using the name Joe and surname Bloggs
    Then the person is found
    And the name is Joe, the surname is Bloggs and the dob is 1972-02-02

  Scenario: Find a person that does not exists
    When you search for a person using the name Unknown and surname Person
    Then the person is not found
