---
layout: page
title: Sean's Project Portfolio Page
---

### Project: AddressBook Level 3

AddressBook - Level 3 is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to this project.

* **New Feature**: Added the ability to track the birthdays for every contact added in FriendBook.
  * What it does: Provides the user the ability to countdown and track the remaining days to every contact's birthday in the form of a list for visual representation(closest birthday is shown first). This feature supports automatic update and sorting when a new contact is added.
  * Justification: As a contact management app, it is a useful feature to have for users who regularly keep in contact with their friends. It may be difficult to keep track of the different birthdays, especially for users who have large connections. This feature aims to alleviate this problem by pushing the responsibility to the app, providing users the ability to retrieve such information within a short period whenever necessary. As such, we decided to make the Birthday field compulsory as without it, it would just be a normal contact management app.

* **New Feature**: Viewing full details of the contact.
  * What it does: Provides the user the ability to view the full particulars of a contact with a single command
  * Justification: It is not practical to show the full details of the person in the app's home page as it takes up too much space, given the various different fields present per contact. Hence, the home page only displays a subset of important details of the contact. This feature thus allows to user to access all other details of the contact whenever needed.

* **Code contributed**: [Reposense Link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-09-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=xnajasho&tabRepo=AY2122S1-CS2103-F10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub
  * Assisted team lead with setting soft deadlines and idea generation
  * Provided the main feedback on pros/cons of implemented features and how to better implement/improve them
  * Fixed various Command related bugs

* **Enhancements to existing features**:
  * Fixed bugs which were present in original AB3 which allowed duplicate phone numbers and emails (Pull request [\#156](https://github.com/AY2122S1-CS2103-F10-3/tp/pull/156))

* **Documentation**:
  * User Guide:
    * Added documentation for the feature `view` (Pull request [\#85](https://github.com/AY2122S1-CS2103-F10-3/tp/pull/85/files))
    * Update command summary table to support newly implemented fields (Pull request [\#92](https://github.com/AY2122S1-CS2103-F10-3/tp/pull/92/files))
  * Developer Guide:
    * Added Use Case scenarios for various commands (UC4 - UC7)
    * Added sequence diagram for `view` command
    * Updated and maintained details relevant to the model

* **Community**:
  * Reported bugs and suggestions for other teams in the class ([PED Link](https://github.com/xnajasho/ped/issues))
