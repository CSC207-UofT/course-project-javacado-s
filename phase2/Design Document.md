# Javacado's Design Document

---

## Specification
Create a program that manages catering requests for events from users. A **User** can log in to their account with their
unique username and password, and be able to view and modify all events they have requested in the past, as well as
request new events. Users are also able to change their password.

Each **Event** has a name, date, location, number of attendees, and type of **Meal** needed. Depending on the type of
meal and number of attendees, the event will need a certain number of **Employees** assigned.

The **Catering System** employs a set number of different types of **Employees** (Chef, Cleaner, Server, Supervisor)
who can be assigned to events. Each employee can only work at one event per day. If there are not enough available
employees on an event date, the system will not allow the user to request catering. If a request is successfully
submitted, the user will receive a confirmation message with event details, as well as the total price of the catering.
This price will depend on the meal type requested as well as the number of event attendees.

It should be possible for the system to get a list of all events for a user for a particular date, and the number of
employees available to work for a particular date.

---

## Major Design Decisions
Below are some major design decisions our group has made for our project:

### Our Login System with Serialization/Data Persistence
For Phase 1, we wanted to incorporate a user login system to expand the scope of our project. This new functionality
would allow users to log in with their unique username and password combination, and _only_ access events that _they_
have requested. They would also be able to modify events (i.e. change number of attendees, meal type) that have not 
happened yet, and request new events. For this to function as an actual account system, our program needed a way to
save all the events of a user (with any modifications from the user), store it somewhere, and load it once a user logs
into their account again.

In order to adhere to clean architecture and the SOLID design principles, we made 2 major design decisions: (1) we 
chose to serialize the Event objects corresponding to a User and store that serialized information in the User objects 
themselves, and (2) we chose to let EventManager _only_ access the events of the User currently logged in, rather than
act as a centralized manager for all events in the Catering System. (Essentially, a new EventManager is constructed 
with each user login.)

Another option we had was to have users store a list of unique event IDs corresponding to their own events, and having
a centralized EventManager who has access to events from all users but only allows a user to access their own (based
on the list of event IDs stored in the User object). We also considered having Event objects store the unique username
they were associated with, instead of the other way around. Both these options, however, seemed inefficient and 
required us to modify our program a lot rather than just extend it. For example, in both these options the EventManager 
would either have to loop through a long master list of events, or we would need to modify EventManager to store both a
master list of events along with a list of events of the user currently logged in. Also, users never need to access 
events of another user, and the scheduling process of our system depends on employee availability rather than the 
timing of other events, so there is not much of a need to always store a list of _all_ events in our system.

With our current design decision we have that once a user logs in, their serialized event information is passed to a 
newly constructed the EventManager who will deserialize the events and store the actual Event objects. After all 
information is "loaded", the functionality of our program is very similar to that in Phase 0 but specific to a user 
(and with more user commands).

### Using unique IDs for entity classes
In classes Event, Employee, and User, we have unique IDs (in the case of User, it's a username) to identify different
instances of our entity classes. We decided to use these to minimize coupling within our program.

For example, when creating an event, EmployeeManager needs to determine if there are enough available employees. This
requires a lot of information from the event object itself, but both CateringSystem and EmployeeManager should not
directly interact with event objects. Thus, we decided that they would have access to an event ID (integer) and pass 
that to EventManager to get any required information of an event.

Another example is that we wanted to be able to know which employees were assigned to which events, but
events themselves did not need to call any methods of the employees. To minimize coupling here, we also used the fact
that employees have unique IDs and decided to store a list of employee IDs in an Event. This way, if there is ever a
need to access information of an employee for a certain event, the employee ID can be passed to the EmployeeManager to
get the needed information, rather than directly calling methods from an Employee object (which would not be consistent
with clean architecture).

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
We used the Command design pattern in our code to have our program more efficiently execute the various commands a
user can input into our command line. And, for conveniency, we placed all the commands in the Command package. For
example, there is the CreateEventCommand which is used by the CateringSystem. The user interacts with the Main as they
want to create an event, the Main then calls on CateringSystem which eventually calls on CreateEventCommand, so that it
creates an event. Similar examples can be found, like the CancelEventCommand. By indirectly calling these commands, the
users can create a new event, or view, modify, or cancel an existing event. They are also able to log in and log out of
our program. (We also anticipate adding more commands in Phase2.) Most of these requests require several steps in our
program, so using this design pattern, we can turn these requests into single
objects that contain information about all those steps.

We also used a simple factory design pattern. The MealSetter in the meals package is used for other classes to create
meals, while at the same time, those classes do not have to have strong dependency on meal class (for example, the
EventManager).

We are also exploring using the Composite design pattern for our Meal class. In our Meal class, we have different 
subclasses of meal types, each with a different menu of dishes, where each dish is made with different ingredients. 
In the future we hope to determine meal prices based on ingredients, so implementing this tree structure using this
design pattern seems suitable. Additionally, it would make extending our Meal classes easier.

---
##Accessibility Report
**1. For each Principle of Universal Design, write 2-5 sentences or point form notes explaining which features your 
program adhere to that principle. If you do not have any such features you can either: (a) Describe features that you 
could implement in the future that would adhere to principle or (b) Explain why the principle does not apply to a 
program like yours.**

#### Principle 1: Equitable Use

#### Principle 2: Flexibility in Use

#### Principle 3: Simple and Intuitive Use

#### Principle 4: Perceptible Information
Currently, the fourth principle, perceptible information, can not be satisfied by our program. The only way that user can
interact with the program is through a keyboard and reading prompts from the commandline. There are no visual or audio
options. One feature we could implement in the future is to include audio, and visual information in our program by
implementing a GUI and presenter in our program to allow users to choose how the information is presented to them.

#### Principle 5: Tolerance for Error
To satisfy the fifth principle, tolerance for error, we have designed the “exit” pathway from the program in a way
that minimizes accidental actions. In order to exit the program, users need to manually type out the word “exit” to
exit the program. By requiring this step, we manage to minimize the chance that the user accidentally exits the program
(i.e. by pressing the enter button). One potential feature for a GUI for this program is to have the exit button
isolated from the main page, for example at the bottom of the screen or having it only accessible via a menu button.
Once again, this would prevent accidental commands and actions that may lead to the user unintentionally exiting
the program.


#### Principle 6: Low Physical Effort
Our current design only involves text-base UI, which may require some effort when typing commands into the program, 
however, our program do not need much of that effort; only a little would be enough to do the job. We might consider
adding a GUI to the program, so that the user do not have to input command by typing, but instead only need to press 
the buttons.

#### Principle 7: Size and Space for Approach and Use


**2. Write a paragraph about who you would market your program towards, if you were to sell or license your program to 
customers. This could be a specific category such as "students" or more vague, such as "people who like games". Try to
give a bit more detail along with the category.**

The program would be marketed towards catering companies, as it would allow those companies to easily
keep track of their orders, customers, and employees. This program could be used by different types of
catering services, including wedding catering, corporate catering, social event catering. Moreover,
this program could be easily modified for restaurants or restaurant chains to use to take
orders or reservations from customers.



**3. Write a paragraph about whether or not your program is less likely to be used by certain demographics. For 
example, a program that converts txt files to files that can be printed by a braille printer are less likely to be used 
by people who do not read braille.**
This program is less likely to be used by individuals or small companies, as they would have less need for
a program that keeps track of many employees.


---
## Progress Report

**We have provided a summary of what each group member worked on, as well as one (or two) significant pull request(s)
that they made throughout the term:**

**Rose:**

**Karen:**

**Zx:**

**Maggie:**
Event status update, converting deprecated Date to Gregorian Calendar, and update test files.
Update status feature so that event status are automatically updated each time the program is run: 
https://github.com/CSC207-UofT/course-project-javacado-s/commit/7e05225028cfdc1decf7b3bdd4d29024f7a2ae48
one testing file: 
https://github.com/CSC207-UofT/course-project-javacado-s/commit/4dab0290152aa16c1386c58cf95607f058939271

**Faith:**

**Yifang:**
Worked on the design document a bit, improved the MealSetter.

**Zhengdong:**
