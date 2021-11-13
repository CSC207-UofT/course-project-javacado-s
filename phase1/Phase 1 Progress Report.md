# Progress Report

1. Open questions your group is struggling with
- Would it be advantageous to serialize Employees rather than storing them into a txt file?
- Do we want to work with a database?
- A question would be how to deal with the getEventByID; many methods in other classes would have to throw the
EventNotFound exception if we have getEventByID to throw it, so could we use a design pattern to solve this?
- Should we expand our scope even more?
- Should we add more abstract classes, ie for managers?


2. What has worked well so far with your design

So far, what has worked well with our design is our decision to use unique IDs for different instances
of our entity classes (Event, Employee, and User). This design decreased coupling in our program. 
Moreover, we were able to successfully integrate a user/login system in our program, while abiding to clean 
architecture principles, such as the dependency rule, as well as maintaining a clear hierarchy in our classes. 
This hierarchy was maintained as we followed the single responsibility principle (each class only has 
one responsibility), the dependency rule, and the open/closed principle. Furthermore, we were able to use packaging 
strategies in order to organize our code. We were also able to add complexity to use cases such as EventManager, as 
well we were able to extend entities such as Employee class and Meal class, which helped extend the functionality and scope of the 
program. Another design that worked well as our decision to serialize Event objects, as this allowed for our program 
to have an account system for its users.


We have provided a summary of what each group member worked on: 

- Rose: Implemented User class, command classes, design document + specification  
- Lucas: Modified EventManager, event tests, exceptions package 
- Faith: Implemented Employee subclasses, employee file, progress report 
- Zhengdong: Implemented MealType subclasses, modified Meal Classes 
- Karen: Implemented command classes, Command interface, packaging, style warnings
- Maggie: Implemented Progress status in Event, event override equals method, testing coverage 
- Zi Xuan: Implemented UserManager, fixing EmployeeManager, EventManager + tests
- All: testing, progress report

Here is a summary of what each group member is planning on working on next:

- Rose: Implement a rating system 
- Lucas: work on serializing Employee
- Faith: integrate Employee subclasses in code 
- Zhengdong: Fixing Meal subclasses
- Karen: incorporating date/time in the program 
- Maggie: incorporating date/time in the program
- Zi Xuan: incorporating date/time in the program 
