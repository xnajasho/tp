package seedu.friendbook.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import seedu.friendbook.commons.core.GuiSettings;
import seedu.friendbook.model.person.Email;
import seedu.friendbook.model.person.Name;
import seedu.friendbook.model.person.Person;
import seedu.friendbook.model.person.Phone;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' friend book file path.
     */
    Path getFriendBookFilePath();

    /**
     * Sets the user prefs' friend book file path.
     */
    void setFriendBookFilePath(Path friendBookFilePath);

    /**
     * Returns the user pref's username
     */
    Name getUsername();

    /**
     * Returns the user pref's username property
     */
    StringProperty getUsernameProperty();

    /**
     * Sets the user prefs' username.
     */
    void setUsername(Name username);
    /**
     * Replaces friend book data with the data in {@code friendBook}.
     */
    void setFriendBook(ReadOnlyFriendBook friendBook);

    /** Returns the FriendBook */
    ReadOnlyFriendBook getFriendBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the friend book.
     */
    boolean hasPerson(Person person);

    /**
     * Returns true if the phone exists in the friend book.
     */
    boolean hasPhone(Phone phone);

    /**
     * Returns true if the email exists in the friend book.
     */
    boolean hasEmail(Email email);

    /**
     * Deletes the given person.
     * The person must exist in the friend book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the friend book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the friend book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the friend book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered person list which is sorted by days to birthday */
    ObservableList<Person> getFilteredPersonListSortedByBirthday();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * The given predicate is a person
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

}
