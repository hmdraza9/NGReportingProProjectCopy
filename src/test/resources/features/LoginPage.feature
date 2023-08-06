Feature: Login feature
  
Background: 
	Given user is on app login page "https://www.way2automation.com/angularjs-protractor/banking/#/login"

  @tag1 @UI
  Scenario: Logging in to the system scenrio one
    Then User logs into the system
    Then User logs out the system
    Then user navigates to "https://cucumber.io/"

  @tag2 @tag1 @UI
  Scenario: Logging in to the system scenrio two
    Then User logs into the system
    Then User logs out the system
    Then user navigates to "https://yahoo.com"

  @tag3 @tag2 @UI @tag1
  Scenario: Logging in to the system scenrio three
    #Then User logs into the system
    #Then User logs out the system
    Then user navigates to "https://youtube.com"
    Then fetch "% Gain" for company "ONGC"
    Then fetch "High" for company "Nestle"
    Then fetch "Low" for company "Nestle"
    
    