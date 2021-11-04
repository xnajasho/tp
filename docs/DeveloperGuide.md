---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative] (https://se-education.org).

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2122S1-CS2103-F10-3/tp/blob/master/src/main/java/seedu/friendbook/Main.java) and [`MainApp`](https://github.com/AY2122S1-CS2103-F10-3/tp/blob/master/src/main/java/seedu/friendbook/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2122S1-CS2103-F10-3/tp/blob/master/src/main/java/seedu/friendbook/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`BirthdayListPanel`, `FriendListPanel`, `ProfileDisplay`, `FriendWindow` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2122S1-CS2103-F10-3/tp/blob/master/src/main/java/seedu/friendbook/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2122S1-CS2103-F10-3/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands as well as listen to the `ReminderService`.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2122S1-CS2103-F10-3/tp/blob/master/src/main/java/seedu/friendbook/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/FriendBookLogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `FriendBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/FriendBookDeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/FriendBookParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `FriendBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `FriendBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2122S1-CS2103-F10-3/tp/blob/master/src/main/java/seedu/friendbook/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the friend book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `FriendBook`, which `Person` references. This allows `FriendBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/AY2122S1-CS2103-F10-3/tp/blob/master/src/main/java/seedu/friendbook/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `FriendBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.friendbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to manage a significant number of contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage contacts faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | new user                                   | see usage instructions         | refer to instructions when I forget how to use the App                 |
| `* * *`  | user                                       | add a new person               |                                                                        |
| `* * *`  | user                                       | delete a person                | remove entries that I no longer need                                   |
| `* * *`  | user                                       | find a person by name          | locate details of persons without having to go through the entire list |
| `* * *`  | user                                       | add my friends' birthdays      | know my friend's birthday and age                                      |
| `* * *`  | user                                       | edit my friends' details       | update my friends' details when they change their personal information |
| `* * *`  | forgetful user                             | be reminded of my friends' birthdays| keep track of my friends' birthdays                               |  
! `* *  `  | user                                       | create custom profiles for my friends containing their details| personalise my friend contact book      |
| `* *`    | user                                       | add descriptions about my friends| personalise my friend contact book                                   |
| `* *`    | user                                       | add tags to my friends         | personalise my friend contact book                                     |
| `* *`    | user                                       | add different platforms to contact friends| know where and how to contact them                          |
| `* *`    | user                                       | find persons by tags           | locate details of persons with specific tags only                      |
| `*`      | user                                       | personalise my application with my own name |                                                           |


### Use cases

(For all use cases below, the **System** is the `FriendBook` and the **Actor** is the `user`, unless specified otherwise)

#### Contacts Feature

**Use Case 1 (UC1): Add a friend**

**MSS**
1. User requests to list friends
2. FriendBook shows the list of friends
3. User requests to add a friend with given information
4. FriendBook adds the friend and updates the list

Use case ends

Extensions

* 3a. The given information is invalid as it does not follow the correct syntax

    * 3a1. Friendbook shows an error message

      Use case resumes at step 2

* 3b. The user input is already in the list

	* 3b1. FriendBook informs user that their input is already stored in the list

	  Use case resumes at step 2

**Use case 2 (UC2): Delete a person**

**MSS**

1.  User requests to list persons
2.  FriendBook shows a list of persons
3.  User requests to delete a specific person in the list
4.  FriendBook deletes the person

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

	* 3a1. FriendBook shows an error message.
 
	  Use case resumes at step 2.

**Use case 3 (UC3): Update a friend description**
1. User requests to list friends
2. FriendBook shows the list of friends
3. User requests to update information of a specific friend in the list
4. FriendBook deletes the friend and updates the list

**Extensions**

* 2a. The list is empty

  Use case ends

* 3a. The given index is invalid

	* 3a1. FriendBook shows an error message

	  Use case resumes at step 2

* 3b. The information to be updated is invalid

	* 3b1. FriendBook shows an error message

	  Use case resumes at step 2

**Use case 4 (UC4): View a friend**

**MSS**

1. User requests to list friends (UC5 or UC6 or UC7)
2. FriendBook shows the list of friends based on results of step 1
3. User requests to view the complete details of a specified friend in the list
4. Friendbook displays the full contact details of the specified person

   Use case ends.

**Extensions:**

* 2a: List is empty.

  Use case ends.

* 3a. The given index is invalid.

	* 3a1. Friendbook shows an error message
 
	  Use case resumes at step 2

**Use case 5 (UC5): View all friends**

**MSS:**

1. User requests to list all friends
2. Friendbook shows the list of friends stored within

   Use case ends.

**Extensions:**

* 2a: List is empty.

  Use case ends.

**Use case 6 (UC6): Find friends**

**MSS:**

1. User specifies a set of keywords
2. Friendbook displays a list of person(s) with names matching the keyword(s)
  Use case ends.

**Extensions:**

* 2a: List is empty.

  Use case ends.

**Use case 7 (UC7): Filter friends by tag**

**MSS:**

1. User specifies a set of keywords
2. Friendbook displays a list of person(s) with tags matching the keyword(s)

   Use case ends.

**Extensions:**

* 2a: List is empty

  Use case ends

**Use case 8 (UC8): Get friend’s age**

**MSS:**

1. User requests to view friend’s info (UC4)

	Use case ends.

**Extensions:**

* 1a. The given index is invalid.

	* 1a1. Friendbook shows an error message

	  Use case ends

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

 1. Download the jar file and copy into an empty folder

 1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

 1. Resize the window to an optimum size. Move the window to a different location. Close the window.

 1. Re-launch the app by double-clicking the jar file.<br>
    Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

 1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

 1. Test case: `delete 1`<br>
    Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

 1. Test case: `delete 0`<br>
    Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

 1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
    Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

 1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_


---
