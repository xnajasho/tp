---
layout: page
title: User Guide
---
* Table of Contents
  {:toc}

### CS2103-F10-3 User Guide for v1.2
#### Friend Book
Product name: Friend Book

Targeted user profile: University students

Problem addressed: How might we provide university students to manage their friends’ contacts?

Value proposition: Friend Book is not just a contact management platform for university students to track their friends’ contact. It also helps to keep track of social interactions with their friends.
Main Dashboard

Command Pop up

#### Features 
- Tagging - group contacts together 
- Contacts 
- Birthdays 


#### Contacts
##### Adding a contact: add
--- Adds a person to the address book.
Format: `add [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG] [tele/TELE_USERNAME] [b/BIRTHDAY(dd/mm)]...`

 :bulb: Tip: A person can have any number of tags (including 0)
 :bulb: Tip: The telegram username, and the birthday fields are optional. The birthday field has to be specified in    dd/mm format.
- When adding contacts to the friend book, the user can specify any number of tags he/she chooses to be tagged to the individual (see examples 1 and 2 below)


##### Examples:
- `add n/James Lim e/jl123@gmail.com p/94617232 /a Bugis Street 1234` adds an individual with zero tags
- `add n/James Lim e/jl123@gmail.com p/94617232 /a Bugis Street 1234 t/friend t/NUS` adds an individual with a ‘Friend” and ‘NUS’ tag
- `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` 
- `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal tele/@CroweBetsss b/03/12` 

##### Listing all contacts : list
Shows a list of all contacts in the Friend book.
Format: list

##### Editing a contact : edit
Edits an existing contact in the address book.
Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG] [tele/TELE_USERNAME] [b/BIRTHDAY(dd/mm)]...`

- Edits the person at the specified INDEX. The index refers to the index number shown in the displayed person list. The index must be a positive integer 1, 2, 3, …​
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.
- When updating an existing contact’s tag, the user has to input all existing tags AND the new tag(s) to be added (see example 3 below)
- You can remove all the person’s tags by typing t/ without specifying any tags after it (see example 4)
- When editing the telegram username the old value is replaced by the new value, if one is provided. The telegram username for a contact can be removed by typing just tele/ without specifying any tele username. 

##### Examples:
1. `edit 1 p/91234567 e/johndoe@example.com` 
This edits the phone number and email address of the 1st person to be 91234567 and johndoe@example.com respectively.
2. `edit 2 n/Betsy Crower t/ tele/`
Edits the name of the 2nd person to be Betsy Crower, clears all existing tags, and removes the associated telegram username.
3. `edit 1 t/friend t/NUS t/basketball` 
adds a ‘basketball’ tag to an individual with an existing ‘friend’ and ‘NUS’ tag
4. `edit 1 t/` 
removes all tags associated with the individual at index ‘1’ 

##### Searching by keyword: find
Finds contacts whose **names, email, address, tags or telegram username** contain any of the given keywords.
Format: `find [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG] [tele/TELE_USERNAME] [b/BIRTHDAY(dd/mm)]...`
- At least one search field must be specified
- Every search field can have multiple keywords e.g. find n/Hans Bo
- The search is case-insensitive. e.g hans will match Hans
- The order of the keywords does not matter. e.g. Hans Bo will match Bo Hans
- Only full words will be matched e.g. @Crowe doesn’t match @CroweBetsss
- As long as a contact has even one keyword in a given field, the field is considered a match. e.g. Hans Bo matches Hans Gruber, Bo Yang; John matches both John and John Doe
- Only the contacts that match all the specified fields are returned
##### Examples: 
1. `find n/John tele/@JohnDoe`
Returns John Doe who has a telegram username of @JohnDoe. However a contact with the name Johnathan will not be returned since John does not match Johnathan; also a contact with the name John and telegram username of @Johnny will not be returned since @JohnDoe does not match @Johnny

##### Deleting a contact : delete
Deletes the specified contact from the friend book.
Format: `delete INDEX`
- Deletes the contact at the specified INDEX.
- The index refers to the index number shown in the displayed contact list.
- The index **must be a positive integer** 1, 2, 3, …​

##### Examples:
1. `delete 2` deletes the 2nd contact in the contact book.
2. `find Betsy` followed by `delete 1` deletes the 1st contact in the results of the `find` command.

##### Tagging
An accordian list of tags 


Allows the user to attach a tag(s) to an individual in the friend book.

- When adding contacts to the friend book, the user can specify any number of tags he/she chooses to be tagged to the individual (see examples 1 and 2 below)
- When updating an existing contact’s tag, the user has to input all existing tags AND the new tag(s) to be added (see example 3 below)
- To clear all tags attached to the individual, perform an update on the individual, without specifying any tags in the tag field (see example 4 below).

Examples:

1. `add n/James Lim e/jl123@gmail.com p/94617232 /a Bugis Street 1234` adds an individual with zero tags
2. `add n/James Lim e/jl123@gmail.com p/94617232 /a Bugis Street 1234 t/friend t/NUS` adds an individual with a ‘Friend” and ‘NUS’ tag
3. `edit 1 t/friend t/NUS t/basketball` adds a ‘basketball’ tag to an individual with an existing ‘friend’ and ‘NUS’ tag
4. `edit 1 t/` removes all tags associated with the individual at index ‘1’ 


#### Birthdays
Table view of all friend’s birthdays with a checkbox to set reminders 


Each contact registered can have a birthday date registered to them as well, and this birthday date can be used with different commands to receive an output

If the birthday is set to be reminded, a reminder will be sent when the application is opened; 

The following commands can be adapted to use with the birthday functionality:

- ##### List
    Returns a list of all the friends registered and sorted based on their birthday
    Format: `list [b/BIRTHDAY]`

- ##### Find
    Returns a list of friends who have a birthday on the specified month
    Format: `find [b/BIRTHDAY] KEYWORD` 
The `KEYWORD`  input must be a month of the year
The `KEYWORD`  is case-insensitive (e.g January will match january)
Examples:
`find January` returns the list of all contacts who have January as their birthday month

- ##### Edit 
    Edit a friend’s birthday edit `[n/NAME][b/BIRTHDAY] `
Format: edit `[n/NAME][b/BIRTHDAY]`

- ##### Remind 
    Returns a list of friends who have a birthday from the current date to up to seven days from the current date `remind [b/BIRTHDAY]`
Format: `remind [b/BIRTHDAY]`

	

