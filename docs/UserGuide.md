# User Guide

FriendBook is a **desktop app for managing your friends and is optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, FriendBook can get your friend management tasks done faster then traditional GUI apps.

Targeted user profile: University students

Problem addressed: How might we provide university students to manage their friends’ contacts?

Value proposition: FriendBook is not just a contact management platform for university students to track their friends’ contact. It also helps to keep track of social interactions with their friends.

Table of Contents |
------------------|
Quick Start |
Features |
FAQ |
Command Summary|


--------------------------------------------------------------------------------------------------------------------

##Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

  * **`list`** : Lists all contacts.

  * **`TO UPDATE: add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 b/1994-03-03` : Adds a contact named `John Doe` to the Address Book.

  * **`delete`**`3` : Deletes the 3rd friend shown in the friend list.

  * **`clear`** : Deletes all contacts.

  * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining the basics of each command, and a link to the full user guide.

![help message](images/newHelpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the friend book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS b/BIRTHDAY [avatar/AVATAR] [tele/TELE HANDLE] [desc/DESCRIPTION] [r/REMINDER] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: *Tip:*
A person can have any number of tags (including 0)
</div>
:bulb: *Tip:* The avatar value can be from 1 to 20
<div markdown="span" class="alert alert-primary">:bulb: *Tip:*
Valid reminder inputs are 'on' and 'off'

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 b/1985-05-13`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 b/1999-10-22 t/criminal`
* `add n/James Low p/91593836 e/jameslow@example.com a/Hougang Avnue 5 b/1980-04-01 avatar/5 tele/jameslow222 desc/doctor r/on t/friends`

### Listing all persons : `list`

Shows a list of all persons in the friend book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the friend book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [b/BIRTHDAY] [avatar/AVATAR] [tele/TELE HANDLE] [desc/DESCRIPTION] [r/REMINDER] [t/TAG]…​[t/TAG]…`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without specifying any tags after it.
* Leaving any fields blank e.g `tele/` clears that field

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.
*  `edit 2 avatar/6 tele/ desc/` Edits the avatar of the 2nd person to be the 6th default avatar, and clears their telegram handle and description

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `John Doe`
* `find tim john` returns `Tim`, `John Doe`<br>
  ![findTag FriendBook example](images/findTagFriendBookExample.png)

### Locating persons by tag: `findTag`

Finds persons whose tags contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `owesmoney` will match `owesMoney`
* The order of the keywords does not matter. e.g. `friends owesMoney` will match `owesMoney friends`
* Only the tags are searched.
* Only full words will be matched e.g. `friend` will not match `friends`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `friends owesMoney` will return all friends with `friends` and `owesMoney` in their tags

Examples:
* `findTag friends` returns `John Doe` and `Lucas`
* `findTag owesMoney` returns `Tim` and `Lucas`<br>
![findTag FriendBook example](images/findTagFriendBookExample.png)

### Viewing a person : `view`

Views the profile of a specified person in the friend book.

Format: `view INDEX`

* Views the profile of the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​
* The profile contains all the fields of the person

![Example Profile](images/ExampleProfile.png)

### Deleting a person : `delete`

Deletes the specified person from the friend book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the friend book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

FriendBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/friendbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, FriendBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous FriendBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**FindTag** | `findTag KEYWORD [MORE_KEYWORDS]` <br> e.g., `findTag friends owesMoney`
**List** | `list`
**View** | `view INDEX` <br> e.g., `view 2`
**Help** | `help`

