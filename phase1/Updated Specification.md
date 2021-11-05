# SPECIFICATION

Create a program that manages catering requests for events from users. A **users.User** can log in to their account with their
unique username and password, and be able to view and modify all events they have requested in the past, as well as
request new events. users are also able to change their password.

Each **events.Event** has a name, date, location, number of attendees, and type of **meals.Meal** needed. Depending on the type of 
meal and number of attendees, the event will need a certain number of **employees** assigned.

The **Catering System** employs a set number of different types of **employees** (employees.Chef, employees.Cleaner, employees.Server, employees.Supervisor)
who can be assigned to events. Each employee can only work at one event per day. If there are not enough available
employees on an event date, the system will not allow the user to request catering. If a request is successfully
submitted, the user will receive a confirmation message with event details, as well as the total price of the catering.
This price will depend on the meal type requested as well as the number of event attendees.

It should be possible for the system to get a list of all events for a user for a particular date, and the number of
employees available to work for a particular date.