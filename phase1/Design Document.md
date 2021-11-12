# Javacado's Design Document

---
## Major Design Decisions
Below are some major design decisions our group has made for our project:

### 1. Our Login System with Serialization/Data Persistence
For Phase 1, we wanted to incorporate a user login system to expand the scope of our project. This new functionality
would allow users to log in with their unique username and password combination, and _only_ access events that _they_
have requested. They would also be able to modify events (i.e change number of attendees, meal type) that have not 
happened yet, and request new events. For this to function as an actual account system, our program needed a way to
save all the events of a user (with any modifications from the user), store it somewhere, and load it once a user logs
into their account again.

In order to adhere to clean architecture and the SOLID design principles, we decided to serialize the Event objects 
corresponding to a User and store that serialized information in the User objects themselves. Once a user logs in, this
serialized information is passed to a newly constructed EventManager who will deserialize the events and store the
actual Event objects. Essentially, a new EventManager is constructed with each new user login. After all information is 
"loaded", the functionality of our program is similar to that in Phase 0 but specific to a user (and with more user 
commands). 

Another option we had was to have users store a list of unique event IDs corresponding to their own events, and having
a centralized EventManager who has access to events from all users but only allows a user to access their own (based
on the list of event IDs stored in the User object). We also considered having Event objects store the unique username
they were associated with, instead of the other way around. Both these options, however, seemed inefficient and 
required us to modify our program a lot rather than just extend it. For example, in both these options the EventManager 
would either have to loop through a long master list of events, or we would need to modify EventManager to store both a
master list of events along with a list of events of the user currently logged in. Also, users never need to access 
events of another user, and the scheduling process of our system depends on employee availability rather than the 
timing of other events.


### 2. Using unique IDs for our entity classes
In classes Event, Employee, and User, we have unique IDs (in the case of User, it's a username) to identify different
instances of our entity classes. We decided to use these to minimize coupling within our program.
For example, when creating an event, EmployeeManager needs to determine if there are enough available employees. This
requires a lot of information from the event object itself, but both CateringSystem and EmployeeManager should not
directly interact with event objects, so instead they have access to the event ID and pass that to EventManager to get
the required information. Similarly, we wanted to be able to know which employees were assigned to which events, but
events themselves did not need to call any methods of the employees, so we stored employee IDs in Event instead.

### 3. Using the Command design pattern (?)

---
## Clean Architecture
Throughout our entire working process, we have made sure that our project adheres to Clean Architecture. When we first
designed our CRC model, and in this phase where we extended our program to include a login system, we determined which
classes to create according to the different layers of clean architecture. We began with determining our **entity 
classes**, such as User, Meal, Event, and Employee (along with their respective subclasses). We ensured that these
classes only store information about the objects, and created **use case classes** to manipulate them: UserManager,
EventManager, and EmployeeManager. We then have a **controller class**, CateringSystem, to execute commands from the
user and communicate between the different Manager classes. This helped us adhere to the **dependency rule** and only
allow for dependence on an adjacent layer, from outer to inner. We then have our **user interface class** to actually
interact with the user and handle both the input and output of information, without affecting any of our classes in 
the inner layers.

---
## SOLID Design Principles
#### Single Responsibility Principle
We've ensured that each of our classes only have one responsibility. Our entity
classes only store, set, and get information for the object they represent. Our use case classes are only responsible
for manipulating _one_ entity class (and their respective subclasses). Our controller class delegates tasks to our use
case classes. And our user interface class only interacts with the user, inputting and outputting information, while
it passes on the execution of user commands to the controller class. In other words, all our classes each only have one
reason to change.


#### Open/Closed Principle
Our entity classes are open for extension but closed for modification. For example, in Phase
1 we wanted to add new features to our Meal and Employee classes for more complexity in our program. Instead of
directly modifying the classes, we created subclasses for Meal to allow for different prices and employee to attendee
ratios. We also created subclasses for Employee so we have different types of employees required per event, as well as
different employee wages.


#### Liskov Substitution Principle
Our entity subclasses can be substituted by their parent class without altering any
of the desired properties of our program. For example, our Employee class has no more behaviours than its subclass
Chef so anywhere in our program where we use the entity Chef, we could replace it with Employee.


#### Interface Segregation Principle
No classes implement irrelevant methods of an interface. For example, in our
ICommand interface we had an issue where classes implementing the 'execute' method required different return types.
Instead of declaring multiple 'execute' methods with different return types and forcing all Command classes to
implement all the different execute methods, we used generics instead so classes only implemented one execute method.


#### Dependency Inversion Principle
Currently, any of our Manager (use case) classes will work with any class that is a
subclass of the entity class that that Manager manipulates. Thus, our Manager classes do not depend on specific
behaviour of an entity class and if we wanted to introduce a new subclass of Event, for example, we'd be able to include
it without rewriting code in our EventManager class.

---
## Packaging Strategy
We ultimately decided to package our code by component, since we noticed that our classes naturally fall into certain
categories. We have three Manager classes that would be packaged into a "Manager" folder, several Commands that would
go in a "Commands" folder, etc. At this point we had also considered packaging by layer, but Meal and Employee each
have several subclasses (and associated interfaces), and we expect to possibly extend our other entity classes User
and Event to have more subclasses as well. In this case, packaging by layer would result in too many classes in our
Entity folder, so we felt that it would be more suitable to package subclasses together with their parent class. (i.e. 
Breakfast, Lunch, Dinner, and Meal would go in a "Meals" folder). We also have several data files, such as our list of
employees and the saved User data, so those would be packaged into one folder as well.

---
## Design Patterns
- Command design pattern
- Factory design pattern (For Meal?)
- Anything else we plan to implement in Phase 2?


## Class Diagram (Optional)?