## Progress Report

###Specification Model 

Our specification model states that our program should be able to receive catering requests for events from user, 
with each event having a name, unique ID, date, location, number of attendees, and type of meals.Meal needed. 
Each event has a set number of employees assigned, and employees are assigned to the events.Event by the Catering 
System. If a request is successfully submitted (enough employees available on that particular date), 
the user will receive a confirmation message with event details and total price. 


###CRC model 

Our CRC model consists of three entity classes: employees.Employee(store name, ID, store availability, etc.), 
events.Event (store name, ID, date, num attendees, location, etc.), and meals.Meal(store meal type, number employees needed, etc). 
The two use cases are managers.EventManager, and managers.EmployeeManager. The controller is front_end.CateringSystem, and finally the command 
line interface is represented by the class front_end.Main. 

###Scenario Walk-through 

1. Using our program, a user makes a catering request for an event called "Halloween Party" at the CN
Tower on October 31, 2021, with an expected 200 attendees by entering information to the command line. 

2. Currently, there is only one meal option, which is $20 per attendee, and requiring 1 employee per 20 attendees. 

3. Upon receiving the user's request, Catering System will check that there are enough employees available to work at 
the event on October 31st, 2021. Currently, employee can only work for one event on a date, and employees are assumed to
be available if they are not assigned on a date yet. 

4. Once the program checks its internal list of employee availabilities, it will 
display one of two messages to the user: one stating a successful request, or one informing the user that 
their request could not be accepted for that date. 


###Skeleton Program 

Our skeleton program covers our three entity classes(employees.Employee, events.Event, meals.Meal), two use cases 
(managers.EventManager, managers.EmployeeManager), controller (front_end.CateringSystem), and also provides a simple command line interface. 
Our skeleton program also includes a txt file, stored_employees.txt, which is supposed to store employees by their 
ID and name. 

###Other Notes

Overall, what worked so far with our design was that we made sure that our code abide by the dependency rule, 
which made editing/modifying classes an easier process; for instance, we made sure that our use cases
(i.e. managers.EmployeeManager, managers.EventManager) did not directly interact with entities (i.e. employees.Employee, events.Event). This also allowed 
for different team members to work on different classes at the same time. Further, our design abide the open/closed 
principle, since the attributes in the classes are private and can only be accessed from getter 
methods and modified when setter methods are available. This ensures that code is not modified by accident. However, our
design is open for extension, in that we can implement subclass (i.e. for employee types) easily without deleting a lot
of code. 


Some open questions that our group has is that we are not sure how to implement 'time' into the employees.Employee and events.Event 
classes, as we want to have employees be able to set different availabilities, and also have the events.Event class store 
times as well. 

Currently, we have implemented the skeleton program from our CRC model and specification developed as a team, with
each group member coding one class:
- Rose and Maggie: implemented events.Event class
- Lucas: implemented managers.EventManager class 
- Faith: implemented employees.Employee class 
- Zhengdong: implemented meals.Meal class
- Karen: implemented front_end.CateringSystem class
- Zi Xuan: implemented managers.EmployeeManager class
- all: front_end.Main class 


Lastly, we have created a plan for what each team member is/will be working on in the coming weeks:

- Rose: incorporate time in employees.Employee and events.Event classes 
- Lucas: implement users.User class, AccountManager, AccountSystem
- Faith: extend employees.Employee types, different wages for each
- Zhengdong: extend different meals.Meal types
- Karen: extend front_end.CateringSystem functionality by recommending another available times for the event 
- Maggie: implement rating system
- Zi Xuan: implement payment system 