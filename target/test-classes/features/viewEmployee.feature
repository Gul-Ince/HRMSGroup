@viewEmployee
Feature: viewEmployee
  
  Description: TOD-2  As an admin, I should be able to add Emergency contacts
  
  Description: TOD-4  As an admin, I should be able to add Employee's work experience

  Background: 
    Given user is navigated to HRMS with valid Admin credentials
    And user is navigated to the employee list page
    And user enters employee id as "12077"
    And user clicks employee id "12077"

  @TOD-2
  Scenario: Validate that User can add Emergency contacts
    When user clicks on Emergency Contacts
    And user clicks Add button
    And user fills out Emergency Contacts with the following specification
      | Name   | Relationship | Home Telephone | Mobile     | Work Telephone |
      | Miguel | Husband      | 972989-3456    | 2128305542 | 975374-8596    |
    And user clicks Save button
    Then user verifies the following Emergency specifications are displayed
      """
      Miguel Husband 972989-3456 2128305542 975374-8596

      """

  @TOD-4
  Scenario: Validate that User can add employees Work Experience
    When user clicks on Qualifications
    And  user clicks add button
    And user fills out Add Work Experience with the following specification
      | Company  | Job Title | From | To     | Comment |
      | Syntax | Mentor      | 2019-Jun-20    | 2020-Jan-12 | Supported with Interview Questions   |
    And user clicks save button 
     Then user verifies the following Work Experience specifications are displayed
      """
      Syntax Mentor 2019-Jun-12 2020-Jan-12 Supported with IQ

      """