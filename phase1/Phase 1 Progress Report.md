# Progress Report

Overall, what worked so far with our design was that we made sure that our code abide by the dependency rule, 
which made editing/modifying classes an easier process; for instance, we made sure that our use cases
(i.e. EmployeeManager, EventManager) did not directly interact with entities (i.e. Employee, Event). This also allowed 
for different team members to work on different classes at the same time. Further, our design abide the open/closed 
principle, since the attributes in the classes are private and can only be accessed from getter 
methods and modified when setter methods are available. This ensures that code is not modified by accident. However, our
design is open for extension, in that we can implement subclass (i.e. for employee types) easily without deleting a lot
of code. 


Some open questions that our group has is that we are not sure how to implement 'time' into the Employee and Event 
classes, as we want to have Employees be able to set different availabilities, and also have the Event class store 
times as well. 

Currently, we have implemented the skeleton program from our CRC model and specification developed as a team, with
each group member coding one class:
- Rose and Maggie: implemented Event class
- Lucas: implemented EventManager class 
- Faith: implemented Employee class 
- Zhengdong: implemented Meal class
- Karen: implemented CateringSystem class
- Zi Xuan: implemented EmployeeManager class
- all: Main class 


Lastly, we have created a plan for what each team member is/will be working on in the coming weeks:

- Rose: incorporate time in Employee and Event classes 
- Lucas: implement User class, AccountManager, AccountSystem
- Faith: extend Employee types, different wages for each
- Zhengdong: extend different Meal types
- Karen: extend CateringSystem functionality by recommending another available times for the event 
- Maggie: implement rating system
- Zi Xuan: implement payment system 