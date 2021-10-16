## Progress Report

###Specification Model 

Our specification model states that our program should be able to receive catering requests for events, 
with each event having a name, date, location, number of attendees, and type of Meal needed. 
Each event has a set number of Employees assigned, and employees are assigned to the Event by the Catering 
System. If a request is successfully submitted (enough employees available on that particular date), 
the user will receive a confirmation message with event details and total price. 


###CRC model 

Our CRC model consists of three entity classes: Employee(store name, ID, store availability, etc.), 
Event (store name, date, num attendees, location, etc.), and Meal(store meal type, number employees needed, etc). 
The two use cases are EventManager, and EmployeeManager. The controller is CateringSystem, and finally the command 
line interface is represented by the class Main. 

###Scenario Walk-through 

1. Using our program, a user makes a catering request for an event called "Halloween Party" at the CN
Tower on October 31, 2021, with an expected 200 attendees. 

- Currently, there is only one meal option, which is $20 per attendee, and requiring 1 employee per 20 attendees. 

3. Upon receiving the user's request, Catering System will check that there are enough employees available to work at 
the event on October 31st, 2021. 

4. Once the program checks its internal list of employee availabilities, it will 
display one of two messages to the user: one stating a successful request, or one informing the user that 
their request could not be accepted for that date. 


###Skeleton Program 

Our skeleton program covers our three entity classes, two use cases, controller, 
and also provides a simple command line interface. Our skeleton program 
also includes an txt file, stored_employees.txt, which is supposed to store employees by their 
ID and name. 

###Other Notes

Overall, what worked so far with our design was that we made sure that our code abided by the dependency rule, 
which made editing/modifying classes an easier process; for instance, we made sure that our use cases
(i.e. EmployeeManager, EventManager) did not directly interact with entities (i.e. Employee, Event).
This also allowed for different team members to work on different classes at the same time. 

Some open questions that our group has is that we are not sure how to implement 'time' into the Employee and Event 
classes, as we want to have Employees be able to set different availabilities, and also have the Event class store 
times as well. 

Lastly, we have created a plan for what each team member is/will be working on in the coming weeks:

- Rose: incorporate time in Employee and Event classes 
- Lucas: implement User class, AccountManager, AccountSystem
- Faith: extend Employee types, different wages for each
- Zhengdong: extend different Meal types
- Karen: extend CateringSystem functionality by recommending another available times for the event 
- Maggie: implement rating system
- Zi Xuan: implement payment system 