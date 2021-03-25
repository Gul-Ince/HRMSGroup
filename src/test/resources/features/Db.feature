
@dbTest
Feature: Test SYNTAX HRM Database
Background:   
Given I create a connection with SyntaxHRM database
And I create statement object

  @location
  Scenario: Title of your scenario
 
    When I reterive all locations from database
    Then I validate locations fro SyntaxHRM
    |1| North Office| US|
    |3|Smart Office|CA|
      |4|White Office|CU|
        |3|Black Office|US|
    And I close all connections

