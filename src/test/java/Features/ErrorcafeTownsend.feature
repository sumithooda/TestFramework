@test
Feature: As an user, I should be displayed with proper error message according to neccassry action.

  Background:
    Given Browser is opened and user is navigated to sign in page


  Scenario Outline: verify correct error message is displayed on screen when user try to login with invalid credentials
    When user try to login with below credentials:
      | <username> | <password> |
    Then  "<errormessage>" should be displayed on "<errorfield>"
    Examples:
      | username         | password  | errormessage                  | errorfield |
      |                  |           | Please fill out this field.   | username   |
      | invalidusername1 |           | Please fill out this field.   | password   |
      | abcsdf           | Asdf@1234 | Invalid username or password! | errorlabel |


  Scenario Outline: verify user is not able to create a employee without filling all mandatory field and getting proper error message
    When user try to login with below credentials:
      | Luke | Skywalker |
    And user clicks on create button
    Then user should be navigated to add new employee section
    When user fill in registration form and click on Add button
      | <firstName> | <lastName> | <startDate> | <email> |
    Then error message "Please fill out this field." should be displayed for "<field>"
    Examples:
      | firstName | lastName | startDate  | email   | field      |
      |           | abcd     | 2020-01-10 | a@g.com | firstname  |
      | firstas   |          | 2020-01-10 | a@g.com | lastName   |
      | firstas   | abcd     |            | a@g.com | StarteDate |
      | firstas   | abcd     | 2020-01-10 |         | email      |



