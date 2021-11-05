# Progress Report

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