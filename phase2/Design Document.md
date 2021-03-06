# Javacado's Design Document

---

## Specification
Create a program for a catering company that manages catering requests for events from users. A **User** can create an
account with a unique username and password, and log in with that information. Once logged in, they can request
catering for an **event**, including information on its name, date, location, number of attendees, and type of **Meal**
needed (breakfast, lunch, or dinner). Users should be able to view a list of all events they have requested in the past,
and view specific event information like the menu and total price of catering (which depends on the meal requested and
number of attendees) and the status (if the request is in progress or completed). Users should also be able to modify 
the name, location, number of attendees, and catered meal for their for event. They should also be able to cancel a 
request.

The program should save a user's events so that if they log out and log back in, they can view changes they have made
in the past. A user should only have access to their event information.

On the administrative side (hidden from the user), the **Catering System** employs a predetermined number of 
**employees** who can be assigned to events. Employees have names and dates in which they are unavailable to work. Each
employee can only work at one event per day, and each event will need a certain number of employees to work depending 
on the number of event attendees and meal type requested. If there are not enough available employees on an event date, 
the system should not allow the user to request catering for that date.

**Catering Company Information:**
* Breakfast: $7 per attendee; 1 of each type of employee needed per 5 attendees
* Lunch: $14 per attendee; 2 of each type of employee needed per 5 attendees
* Dinner: $22 per attendee; 3 of each type of employee needed per 5 attendees
* See employees.txt to see the list of employees for the company. All employees currently have January 1st, 2020 off.

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

<br/>

### Using Unique IDs for Entity Classes
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

<br/>

### Creating Gateway Classes to Read and Update Serialized and Text Files
Many of the methods in our manager classes involved reading, parsing, and writing to various files. For example, 
EventManager needed to deserialize an ArrayList of Events from a .ser file, and must be able to serialize the event list
of a user when they log out. EmployeeManager needed to read in and create employees from a .txt file, and must update 
that file given changes in their availabilities. UserManager must read from a .txt file to determine a user's password
and let them log in, and must also update their serialized events with information from EventManager. To ensure we
adhere to clean architecture, we decided to create gateway classes to perform these tasks that deal with external
information. 

Since each manager class has their own implementation of reading and updating files, we decided to create a gateway 
class for each manager class. In order to abide by the dependency inversion principle, we created a common interface
IReadWriter that the gateway classes would implement and the manager classes would depend on.

---
## Clean Architecture
Throughout our entire working process, we have made sure that our project adheres to Clean Architecture. When we first
designed our CRC model, and when we extended our program to include a login system, we determined which
classes to create according to the different layers of clean architecture. We began with determining our **entity 
classes**, such as User, Meal, Event, and Employee (along with their respective subclasses). We ensured that these
classes only store information about the objects, and created **use case classes** to manipulate them: UserManager
manipulates User, EventManager manipulates Event, and EmployeeManager manipulates Employee. Since all these Manager
classes need to read information from files and write to them, we have **gateway classes** EventManagerReadWriter
(for EventManager), EmployeeManagerReadWriter (for EmployeeManager), and UserManagerReadWriter (for UserManager)
to do these tasks. We then have a **controller class**, CateringSystem, to execute commands from the user and 
communicate between the different Manager classes. This helped us adhere to the **Dependency Rule** and only allow for
dependence on an adjacent layer, from outer to inner. We also created an interface for our gateway classes that our
use case classes could depend on to adhere to the dependency rule, as gateways are on an outer layer compared to
use cases. We then have our **user interface class** to actually interact with the user and handle both the input 
and output of information, without affecting any of our classes in the inner layers.

###Scenario-Walkthrough
Suppose we create a user with username Rose and password 1234. Logging in, we select option "1 Create new event"
and request an event with name Birthday on 1/19 at the CN Tower for 15 attendees, and request that dinner be catered.
We will get the confirmation message:

"Thank you for choosing Javacado's! Your catering request was accepted.\
Event details (ID: 0): Birthday on 01/19/2021 at CN Tower for 15 attendees.\
Menu of dinner:\
Grilled Steak\
Grilled Salmon\
Large Salad\
Shrimp And Corn Chowder Soup\
Apple Juice\
Price of catering: $330.0\
The current event status is: Created"

Throughout this entire process, the user is only interacting with the UI of our program and is presented with a
confirmation message with information generated by internal layers of our program such as our controller, use case,
and entity classes. Our EventManager created an event, where the Event entity class determined what the total price of
catering was, EmployeeManager checked that enough employees were available on January 19, 2021 to cater dinner for 15
people, etc. We could change the way all this information was presented to the user, and only need to change our Main
(UI) class and none of the classes actually involved in processing this information. Alternatively, we could change the
dinner menu, or price per attendee for dinner, and would not need to change our UI and only our Dinner class. This is
all possible because our program adheres to the dependency rule of clean architecture.

###Violations of Clean Architecture
[Karen/Zi-Xuan please fill this out.]
While our program is mostly compliant with clean architecture, we had one significant violation of clean architecture. 

- Currently, our Main class, a controller, has a dependency on User, an entity, a clear violation of clean architecture. 
The problem is due to our implementation of UserManager and the login procedure. Our getUser method in UserManager 
requires both a username and password, and returns a User, but it should require only a username to return a User, and 
either UserManager or User should have a separate method to check that the passwords match. Changes to these 
parts of our program would also invite changes in the login procedure. Instead of using a Tuple of a User and Boolean 
to check if we have a user logged in, we should add a field to UserManager that holds onto the current active User or
let the system hold on to the username of the current active User. These changes would eliminate the dependency between
our controller and entity.

---
## SOLID Design Principles
#### Single Responsibility Principle
We've ensured that each of our classes only have one responsibility. Our entity
classes only store, set, and get information for the object they represent. Our use case classes are only responsible
for manipulating _one_ entity class (and their respective subclasses). Each gateway class is responsible for acting
as a gateway for one manager class. Our controller class delegates tasks to our use case classes. And our user 
interface class only interacts with the user, inputting and outputting information, while it passes on the execution 
of user commands to the controller class. In other words, all our classes each only have one reason to change.
Further, by adding a gateway class in phase 2, we ensure that serialization and setup of managers are done by the
gateway class, so that managers only manipulate the entities and do not take care of serialization.


#### Open/Closed Principle
Our entity classes are open for extension but closed for modification. For example, in Phase
1 we wanted to add new features to our Meal and Employee classes for more complexity in our program. Instead of
directly modifying the classes, we created subclasses for Meal to allow for different prices and employee to attendee
ratios. We also created subclasses for Employee, so we have different types of employees required per event, as well as
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

Also, we initially had an issue where our manager classes depended on their respective gateway classes, an outer layer, 
but we made an interface IReadWriter. Then, the manager classes could depend on this interface, and the gateway classes
would implement that interface.

---
## Packaging Strategy
We ultimately decided to package our code by component, since we noticed that our classes naturally fall into certain
categories. We have three Manager classes that would be packaged into a "Manager" folder, several Commands that would
go in a "Commands" folder, etc. At this point we had also considered packaging by layer, but Meal and Employee each
have several subclasses (and associated interfaces), and we expect to possibly extend our other entity classes User
and Event to have more subclasses as well. In this case, packaging by layer would result in a lot of classes in our
Entity folder, so we felt that it would be more suitable to package subclasses together with their parent class. (i.e. 
Breakfast, Lunch, Dinner, and Meal would go in a "Meals" folder). This would improve readability since there's less to 
look at per package. In addition, packaging by component separates the different categories of entities and so higher 
level classes don't have to access entities they're not dependent on. For example, MealSetter only needs to import the 
"Meals" folder and this better illustrates the relationship between classes. We also have several data files, such as 
our list of employees and the saved User data, so those would be packaged into one folder as well.

---
## Design Patterns
### Command Design Pattern
We used the Command design pattern in our code to have our program more efficiently execute the various commands a
user can input into our command line. And, for convenience, we placed all the commands in the Command package. For
example, there is the CreateEventCommand which is used by the CateringSystem. The user interacts with the Main as they
want to create an event, inputting information about the new event (e.g. event name, date, etc.). Main then calls on 
CateringSystem's methods, passing along the information. CateringSystem eventually creates an CreateEventCommand object, 
so that it stores all the information and classes (e.g. EventManager and EmployeeManager) necessary to create an event. 
Finally, CateringSystem calls CreateEventCommand's execute() method, creating the new event. Within CreateEventCommand, 
it calls on methods from EventManager and EmployeeManager to create that event. Similar examples can be found, like the 
CancelEventCommand. By Main indirectly calling these commands through CateringSystem, the users can create a new event, 
or view, modify, or cancel an existing event. Most of these requests require several steps in our program, so using this 
design pattern, we can turn these requests into single objects that contain information about all those steps. In 
addition, it has the benefit of extracting the implementation of these commands into their own classes, so that Main and 
CateringSystem don't have to know about how these commands work. We also implemented the Command design pattern with 
a generic ICommand interface. The benefit of this is that each of the Command classes can implement the interface cast 
to a different type, allowing the return type of their execute() methods to be different.

### Factory Design pattern
We also used a simple factory design pattern. The MealSetter in the meals package is used for other classes to create
meals, while at the same time, those classes do not have to have strong dependency on meal class (for example, the
EventManager).
This allows us to create meal types according to the user's input. Event manager is able to create the requested meal
type without need to know which meal types (subclasses of Meal) it has, it will simply call the MealSetter,
and it will take the role to create the meal which the user desired, and if the requested meal type not exist,
it will throw the exceptions.
This helps us in the future program expansion, if we want to add more meal types, we do not need to modify the
EventManager class each time, just to modify the MealSetter to include more options.

### Composite Design Pattern
We decide to implement Composite design pattern for our Meal class, because we found it could be represented as a Tree
Structure, doing so could help us manage each classes independently while also maintain the overall hierarchy.
Thus, in our Meal class, we have different subclasses of meal types (so far Breakfast, Lunch, Dinner),
each contains different menus (of dishes),
its own price, and its own number of employees required and other possible information in the future expansions.
in additional each dish is made with different ingredients, where we consider as the 'lowest common denominator'
as it contains all the methods where all classes in meals package shares.
In the future expansions we hope to determine meal prices based on ingredients, so implementing this tree structure
using this design pattern seems suitable. Additionally, it helps manipulate our Meal classes easier.

---
## Accessibility Report

### Principles of Universal Design
#### Principle 1: Equitable Use
Currently, our program does not have many features that adhere to this principle as we have a very simple, text-based
user interface. Features we could implement in the future include (1) text-to-speech and speech-to-text services, and
(2) translation services. The first category of features would make our program adhere to equitable use as it allows
those with visual impairments to use our program. Translation services would allow those who do not speak English, or
are not fluent in English, to use our program with more ease.

#### Principle 2: Flexibility in Use
Our program adheres to this principle because it takes in user input through the keyboard, so that it accommodates both 
left- and right-handed users. In the future, we can implement a feature that allows users to change the size of text. 
This way the user interface can adapt to each users??? specific needs. We can also implement a text-to-speech feature, so 
that users have the option to read prompts and other textual information displayed on the screen, or to listen to them. 
All of these features make the use of our program more flexible, accommodating a wider range of user preferences and 
abilities.


#### Principle 3: Simple and Intuitive Use
Our program provides simple prompts for actions from the user. We provide them with a list of actions, and to perform
an action, the user enters the number associated with the action. To avoid confusion, we prompt the user for each piece 
of information needed to perform an action. For instance, when creating an event, we prompt for each detail separately 
(e.g name, date, number of attendees, etc.). When an error occurs we tell our user which input is invalid. This adheres 
to the principle of simple and intuitive design. A young child would be able to use our program without any 
issues.
#### Principle 4: Perceptible Information
Currently, the fourth principle, perceptible information, can not be satisfied by our program. The only way that user can
interact with the program is through a keyboard and reading prompts from the commandline. There are no visual or audio
options. One feature we could implement in the future is to include audio, and visual information in our program by
implementing a GUI and presenter in our program to allow users to choose how the information is presented to them.

#### Principle 5: Tolerance for Error
Since our user interface is fairly simple, there are not many accidental actions or commands that a user can do, 
therefore our program adheres to this principle of tolerance for error. One potential feature for a GUI for this program 
is to have the exit button isolated from the main page, for example at the bottom of the screen or having it only accessible 
via a menu button. This would prevent accidental commands and actions that may lead to the user unintentionally exiting 
the program.

#### Principle 6: Low Physical Effort
Our current design only involves text-base UI, which may require some effort when typing commands into the program, 
however, our program do not need much of that effort; only a little would be enough to do the job. We might consider
adding a GUI to the program, so that the user do not have to input command by typing, but instead only need to press 
the buttons.

#### Principle 7: Size and Space for Approach and Use
Unfortunately, most part of this principle cannot be adhered by our program, since most of this principle applied more 
to hardware than software side, and since our program so far only provide a simple UI form (text-UI); 
However, In the future program expansions, it is possible to provide some forms of the features, such as integrated 
together with the Principle 2, like to create High contrast mode so that our text-UI is easier to see when viewing the 
screen from different positions, and also adjust the level of detailed information on screen so that it does not 
require the User to have to look closer to the screen.

## Target Market

Our target market would be English-speaking adults who regularly host large events. For example, we would target HR/
administrative representatives of businesses and schools, and event planners of community organizations - anyone in
charge of booking/organizing catering for their events. Since the purpose of our program is to allow a user to request 
catering for events and manage several requests at once, we want to consider those who would regularly host events 
rather than it just being a one-time thing. Additionally, since we are a catering company, we want to prioritize
marketing to those who would host large events as it would be more profitable for us. (Profits of our catering system
are not reflected in our code at this stage, but this is the general case with catering companies.)

Our program is not likely to be used by non-English speakers, as all our user prompts, input, and output are in 
English. As well, given that our target market consists of large event hosts, it is less likely that children and
teenagers would use our program. Finally, taking disabilities into account, are program is less likely to be used by
those with visual impairments. Our user prompts, input, and output are all text based, with no audio or colour cues.
See our discussion above about the principles of universal design for some features we could implement in the
future to take this into account.

---
## Testing and testing coverage 
We wrote test files for all of our entities, use case, and controller classes in addition to testing from the front_end 
by running main. Our testing coverage is quite high when looking at % of method coverage for most files (ex. for managers 
folder that contain all our use case classes we have 94% coverage). Testing our code from the backend and testing 
from the user's perspective by running **Main** class ensures correctness of our code.

---
## Progress Report

**We have provided a summary of what each group member worked on during Phase 2, as well as one (or two) significant 
pull request(s) that they made throughout the term:**

### Rose
In phase 2, I worked on keeping our Design Document up to date and making edits according to our feedback from Phase 1.
Additionally, I modified our ViewEventCommand to show a list of the user's events to make it more convenient to use our 
program. I also worked on extracting the file reading, parsing, and writing sections from our manager classes and 
creating gateway classes to perform these tasks.

**One significant pull request:** https://github.com/CSC207-UofT/course-project-javacado-s/pull/96 \
This pull request involved creating the gateway classes, which demonstrates a significant contribution as it helped our 
program adhere to clean architecture. Additionally, I created interfaces to ensure that these classes adhere to the 
dependency inversion and interface segregation SOLID principles.

### Karen
Worked on handling invalid inputs in Main (e.g. when creating/modifying an event).
https://github.com/CSC207-UofT/course-project-javacado-s/pull/34/files - In this pull request, I started implementing 
the Command design pattern by creating the ICommand interface and CreateEventCommand class. This demonstrates a 
significant contribution to our team because it laid the foundation for how we were to incorporate this design pattern 
into our program.

### Zi Xuan
Handled assigning and reassigning specific Employees to Events. Updated EmployeeManager to properly handle Employee 
unavailability. Created Exceptions to provide more specific try-catch scenarios. Slight refactoring. 

Significant pull requests:\
https://github.com/CSC207-UofT/course-project-javacado-s/pull/53 (together with 
https://github.com/CSC207-UofT/course-project-javacado-s/pull/64 split apart by accident)\
I designed and programmed the prototype implementation of the User branch of our system. Admittedly the system didn't 
work properly because we weren't very familiar with serialization or file operations at the time, but the base of the 
system was here.

### Maggie
Event status update, converting deprecated Date to Gregorian Calendar, and update test files.
Update status feature so that event status are automatically updated each time the program is run: \
https://github.com/CSC207-UofT/course-project-javacado-s/commit/7e05225028cfdc1decf7b3bdd4d29024f7a2ae48\
one testing file:
https://github.com/CSC207-UofT/course-project-javacado-s/commit/4dab0290152aa16c1386c58cf95607f058939271\
These demonstrate a significant contribution to the group because update event status feature allow user to know
event details better and incorporated dates into our program. Testing catches bugs of different files that are cruical
for program to run correctly.

### Faith
Integrated Employee subtypes in the code -- modified meal class and its subclasses(Breakfast, Lunch, Dinner), 
event class, and meal tests, to include different types of Employees (Chef, Supervisor, Server, Cleaner) 
(https://github.com/CSC207-UofT/course-project-javacado-s/pull/89). Worked on implementing employee subclasses in 
EmployeeManager for initialization. This demonstrates a significant contribution to the team as it added complexity to the design of our program, 
specifically on the employee side, by extending one of its core entity classes. Also worked on invalid inputs for password
and username input. 

### Yifang
Worked on the design document a bit, improved the MealSetter. Fixed the bugs related to EventManager, and problems related
to creating new users.

Significant pull requests: \
https://github.com/CSC207-UofT/course-project-javacado-s/pull/67/files \
https://github.com/CSC207-UofT/course-project-javacado-s/pull/86/files


### Zhengdong
During the phase 2: Remove (Delete) the older (outdated) codes remained in the Meal Class, modify the Mead Class 
so that it follows more on the Composite Design pattern, move some roles originally in the Meal class to its Subclasses.

one significant Pull Requests:
https://github.com/CSC207-UofT/course-project-javacado-s/pull/46/files
In this Pull request, I implement the basic framework of the Composite Design Pattern in my Meal Classes, 
by creating 3 direct subclasses (Breakfast, Lunch and Dinner), a "lowest common denominator" interface Ingredient,
A primitive Dish class.
Doing so we have successfully implement one of the design pattern (develop from 0 to 1) inside one of the key part of 
our catering system, since we need meals to be served in the Event and this design pattern help us consider 
individual Ingredients/MealTypes/Dishes and General Meal (Composition) uniformly.
