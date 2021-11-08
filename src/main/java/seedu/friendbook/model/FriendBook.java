package seedu.friendbook.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.friendbook.model.person.Email;
import seedu.friendbook.model.person.Person;
import seedu.friendbook.model.person.Phone;
import seedu.friendbook.model.person.UniquePersonList;

/**
 * Wraps all data at the friend-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class FriendBook implements ReadOnlyFriendBook {

    private final UniquePersonList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
    }

    public FriendBook() {}

    /**
     * Creates a FriendBook using the Persons in the {@code toBeCopied}
     */
    public FriendBook(ReadOnlyFriendBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code FriendBook} with {@code newData}.
     */
    public void resetData(ReadOnlyFriendBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the friend book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if a person with the same Phone Number exists in the friend book.
     */
    public boolean hasPhone(Phone phone) {
        requireNonNull(phone);
        return persons.hasExistingPhoneNumber(phone);
    }

    /**
     * Returns true if a person with the same Email exists in the friend book.
     */
    public boolean hasEmail(Email email) {
        requireNonNull(email);
        return persons.hasExistingEmail(email);
    }

    /**
     * Adds a person to the friend book.
     * The person must not already exist in the friend book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the friend book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the friend book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code FriendBook}.
     * {@code key} must exist in the friend book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Person> getSortedPersonListByBirthday() {
        return persons.asUnmodifiableObservableListSortedByBirthday();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FriendBook // instanceof handles nulls
                && persons.equals(((FriendBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
