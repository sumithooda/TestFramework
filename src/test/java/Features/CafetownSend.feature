@test
Feature: As an user , I should be able to navigate to the cafetownsend website and be able to add new employee, update and delete


  Background:
    Given Browser is opened and user is navigated to sign in page
  @parallel
  Scenario: verify user is able to login and logout to cafe system with valid credentials
    When user try to login with below credentials:
      | Luke | Skywalker |
    Then user should be navigated to home page and should able to see text Hello "Luke"
    When user clicks on Logout button and navigated to sign in page
  @parallel
  Scenario: verify user is able to add new employee to system
    When user try to login with below credentials:
      | Luke | Skywalker |
    And user clicks on create button
    Then user should able to navigate to add new employee section
    When user fill in registration details and click on Add button
      | Alex | waise | 2020-01-01 | alex@f.c |
    Then newly added employee should be displayed on home page

  Scenario: verify user is able to update details of existing employee
    When user try to login with below credentials:
      | Luke | Skywalker |
    And user selects a employee from cafe and user clicks on edit button
    Then user should be able to see employee details
    And user should be able to update the employee email to "abc@g.nl" and click on update button
    And user selects same employee and click on update button
    And employee email details should be as updated

  Scenario: verify user is able to delete the exisiting employee from cafe
    When user try to login with below credentials:
      | Luke | Skywalker |
    Then user selects a employee from cafe employee list
    And user clicks on delete button
    Then user confirmation alert should appear and user clicks on yes button
    And selected employee should get deleted and not available in cafe employee list


