##**SCENARIO WALKTHROUGH**

Upon startup, the command line interface will create a CateringSystem, which will in turn create an EventManager and
EmployeeManager. A text file of employee information is passed through the command line interface to the
EmployeeManager. When EmployeeManager is created, it uses this text file to create Employees corresponding to the
information in the file.

Using the command line interface, a user makes a catering request for an event called "Halloween Party" at the CN
Tower, which will occur on October 31, 2021. They expect 200 attendees and request dinner to be catered.
CateringSystem will create a Dinner object, with a set price of $__ per attendee and requiring __ employees for every
____ attendees. It passes this object along with all other event information to EventManager.  EventManager then 
creates an Event with this information, and calculates that ____ employees are needed at this event and the total
price of catering is ____. CateringSystem sends EmployeeManager the number of employees needed for the event date, and 
EmployeeManager determines if there is enough availability. If there is not enough availability, CateringSystem will 
return to the command line interface the message "Sorry, your catering request could not be accepted for this date." 
If there is enough employee availability, the CateringSystem will return to the command line
interface the confirmation message: "Thank you for choosing Javacado's! Your catering request was accepted. Event
details: Halloween Party on October 31, 2021 at CN Tower for Dinner for 200 attendees. Price of catering: $_______."



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
