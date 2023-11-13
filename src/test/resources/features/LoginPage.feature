Feature: Login feature
  
Background: 
	Given user is on app login page "https://www.way2automation.com/angularjs-protractor/banking/#/login"

  @tag1 @UI
  Scenario: Logging in to the system scenrio one
    Then User logs into the system
    Then User logs out the system
    Then user navigates to "URL.cucumber"

  @tag2 @tag1 @UI
  Scenario: Logging in to the system scenrio two
    Then User logs into the system
    Then User logs out the system
    Then user navigates to "URL.youtube"

  @tag3 @tag2 @UI @tag11
  Scenario: Logging in to the system scenrio three
    #Then User logs into the system
    #Then User logs out the system
    Then user navigates to "URL.youtube"
    Then fetch "% Gain" for company "ONGC"
    Then fetch "High" for company "Nestle"
    Then fetch "Low" for company "Nestle"
    
    

  @tag3 @tag2 @UI @12112023
  Scenario: Selenium 4 features test
    Then user navigates to "URL.amazon"
    Then user validates tab switch feature
    Then user practices the relative locators in selenium 4
    Then user practices the types of windows in selenium 4
    
    