Feature: Login feature
    
	@testParams @DB @tag1 
	Scenario: Testing params in steps
		Then user multiplies "12" and "15"
		Then tests pipe separated params
#		|Hello|My|Name|Is|Mr.|Hamid|Raza|
		|Hello|
		|My|
		|Name|
		|Is|
		|Mr|
		|Hamid|
		|Raza|
    
	@testParams @DB @tag1
	Scenario Outline: Testing params in steps
		Then user multiplies "12" and "15"
		Then user multiplies "<num1>" and "<num2>"

		Examples:
		|num1|num2|
		|21|17|
		|51|11|

		

		