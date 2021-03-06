Narrative:
In order to use the advanced features of the system
As a non-register user
I want to register in the system

Scenario: 	Test registration into the Podorozhniki system for non register user .(positive test)
Given main podorozhniki page
And registration form
When the user has entered registration information
And rules are accepted
And confirm button are pressed
Then the user should see logout button
And the user name should be displayed above the photo field

Scenario: 	Test registration into the Podorozhniki system for register user . (negative test)
Given main podorozhniki page
And registration form
When the user has entered registration information
And rules are accepted
And confirm button are pressed
Then the user should see error message under field login