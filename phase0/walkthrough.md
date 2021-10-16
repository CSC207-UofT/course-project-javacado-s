##**SCENARIO WALKTHROUGH**
Using our program, a user makes a catering request for an event called "Halloween Party" at the CN
Tower, which will occur on October 31, 2021. They input that they expect 200 attendees and request a meal to be 
catered. Currently, there is only one meal option, which is $20 per attendee, and (hidden from user) requiring 1
employee per 20 attendees.

The catering system employs a predetermined number of employees, and upon receiving the user's request, will check
that there are enough employees available (in this case 10) to work at the event on October 31st, 2021. We assume that
employees are available to work on any day, and can work at a maximum of one event per day. Once the program checks its
internal list of employee availabilities, it will display one of two messages to the user:
1. "Sorry, your catering request could not be accepted for this date. Please try requesting on a different
date." - if there are **not** enough employees available to work on that date.
2. "Thank you for choosing Javacado's! Your catering request was accepted.
Event details: Halloween Party on October 31, 2021 at CN Tower for Dinner for 200 attendees. 
Price of catering: $4000.00." - if there are enough employees available to work on that date.