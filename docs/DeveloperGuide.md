## Developer Guide
##### Acknowledgements
`This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).`
##### Setting Up - Coming soon

##### Design - Coming soon

##### Implementation - Coming soon

##### Documentation, logging, testing, configuration, dev-ops - Coming soon

##### Appendix: Requirements

###### Project Scope
Target user profile:
- has a significant number of friends to keep track of
- wishes to categorise friends accordingly
- needs to keep track of friends’ birthdays
- prefers desktop applications
- can type fast
- prefers typing to mouse interactions
- is reasonably comfortable using CLI apps

Value proposition: manage contacts faster than a typical mouse/GUI driven app

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`
#### User stories 
Priority | As a.. | I want to.. | So that I can..
----------|--------|-------------|-----------
**|User|View all my friends|Glance the friends I have
***|User|Add my friends|Know I stay in contact
**|User|Categorise my friends’ contacts|Easily find contact for groups I am interested in
|**|User|Add different platforms to contact friends|Know where and how to contact them
|**|User|Filter my friends by different categories|Find my teammates/module mates/workplace buddies/ etc
|***|User|Add descriptions about my friends|Personalise my friend contact book
|***|User|Update descriptions for my friends|Personalise my experience
|***|User|Retrieve descriptions for my friends|Personalise my experience
|**|User|Add friends to my favorite list|Have a quick view of my favorite friends
|**|User|Sort my friends by birthday|See which friends have their birthdays coming
|***|User|Retrieve my friends’ birthdays|Keep track of my friends’ birthdays
|***|User|Edit my friends’ birthdays|Keep track of my friends’ birthdays
|**|User|Get my friends’ age|Keep track of my friends’ ages
|***|User|Add my friends’ birthdays|Know when my friend’s birthday is and their age
|***|Forgetful User|Be reminded of my friends’ birthdays|Keep track of my friends’ birthdays

#### Use cases
(For all use cases below, the System is the FriendBook and the Actor is the user, unless specified otherwise)
##### Contacts Feature
###### Use case: Add a friend
MSS:
1. User requests to list friends
2. FriendBook shows the list of friends
3. User requests to add a friend with given information
4. FriendBook adds the friend and updates the list
Use case ends

Extensions
3a. The given information is invalid as it does not follow the correct syntax
- 3a1. Friendbook shows an error message
 Use case resumes at step 2

 3b. The user input is already in the list
-   3b1. FriendBook informs user that their input is already stored in the list
Use case resumes at step 2

###### Use case: Delete a friend
MSS
1. User requests to list friends
2. FriendBook shows the list of friends
3. User requests to delete a specific friend in the list
4. FriendBook deletes the friend and updates the list
Use case ends

Extensions
2a. The list is empty
- Use case ends
3a. The given index is invalid
- 3a1. FriendBook shows an error message
Use case resumes at step 2

###### Use case: Update a friend description
1. User requests to list friends
2. FriendBook shows the list of friends
3. User requests to update information of a specific friend in the list
4. FriendBook deletes the friend and updates the list

Extensions
2a. The list is empty
 Use case ends

3a. The given index is invalid
-  3a1. FriendBook shows an error message
 Use case resumes at step 2
 
 3b. The information to be updated is invalid
-  3b1. FriendBook shows an error message
Use case resumes at step 2

###### Use case: View all friends
###### Use case: Add different social media platforms to friend
###### Use case: Filter friends by tag
##### Birthday Feature
###### Use case: Sorting friends by birthday
1. User requests to sort friends by birthday
2. FriendBook shows the list of friends sorted by birthday

##### Tagging Feature
###### Use case: Tag friend

Non-Functional Requirements
1. Should work on any mainstream OS as long as it has Java 11 or above installed.
2. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. Should be able to work on its own without connection to the Internet

##### Glossary
· Mainstream OS: Windows, Linux, Unix, OS-X
· Private contact detail: A contact detail that is not meant to be shared with others
##### Appendix: Instruction for manual testing - Coming soon
 
 

