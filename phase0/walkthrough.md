##**SCENARIO WALKTHROUGH**
Using our program, a user makes a catering request for an event called "Halloween Party" at the CN
Tower, which will occur on October 31, 2021. They input that they expect 200 attendees and request dinner to be 
catered. meals.Dinner is $22 per attendee, and (hidden from user) requires 3 employees per 5 attendees.

The catering system employs a predetermined number of employees, and upon receiving the user's request, will check
that there are enough employees available (in this case 120) to work at the event on October 31st, 2021. We assume
that employees are available to work on any day, and can work at a maximum of one event per day. Once the program 
checks its internal list of employee availabilities, it will display one of two messages to the user:

1. If there are **not** enough employees available to work on that date: "Sorry, your catering request could not be 
accepted for this date. Please try requesting on a different date."
2. If there are enough employees available to work on that date: "Thank you for choosing Javacado's! Your catering
request was accepted. events.Event details: Halloween Party on 31/10/2021 at CN Tower for 200 attendees.
Menu of dinner: Grilled Steak, Grilled Salmon, Large Salad, Shrimp and Corn Chowder Soup, Apple Juice.
Price of catering: $4400.00."