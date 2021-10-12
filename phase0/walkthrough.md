##**SCENARIO WALKTHROUGH**

- Using command line interface, a user makes a catering request for an event with the title of the event, 
location, date, number of attendees, and type of meal (breakfast, lunch, or dinner)
- CateringSystem creates an EventManager and EmployeeManager
- Catering System sends event creation request to EventManager
- EventManager creates an Event with the event title, location, date, number of attendees, and type of meal
- Upon construction, the Event determines the price of catering and number of employees needed
- EventManager returns the above two numbers to the CateringSystem
- CateringSystem sends employee request for that date to EmployeeManager
- If there are enough employees available, EmployeeManager assigns the required number of employees to that event 
and returns True

  - If not: EmployeeManager returns False to CateringSystem and does not assign any employees to 
  the event
  - CateringSystem tells EventManager to cancel event
  - CateringSystem sends message to user that the request could not be accepted

- CateringSystem tells user the request was accepted and sends confirmation message with event details and price of 
catering
