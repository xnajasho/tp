package seedu.friendbook.model;

import static java.util.Objects.requireNonNull;
import static seedu.friendbook.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.friendbook.commons.core.GuiSettings;
import seedu.friendbook.commons.core.LogsCenter;
import seedu.friendbook.model.person.Email;
import seedu.friendbook.model.person.Name;
import seedu.friendbook.model.person.Person;
import seedu.friendbook.model.person.Phone;

/**
 * Represents the in-memory model of the friend book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final FriendBook friendBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Person> filteredPersonsCopy;
    private final StringProperty userNameProperty;

    /**
     * Initializes a ModelManager with the given friendBook and userPrefs.
     */
    public ModelManager(ReadOnlyFriendBook friendBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(friendBook, userPrefs);

        logger.fine("Initializing with friend book: " + friendBook + " and user prefs " + userPrefs);

        this.friendBook = new FriendBook(friendBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.friendBook.getPersonList());
        filteredPersonsCopy = new FilteredList<>(this.friendBook.getSortedPersonListByBirthday());
        userNameProperty = new SimpleStringProperty(userPrefs.getUsername().fullName);
    }

    public ModelManager() {
        this(new FriendBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getFriendBookFilePath() {
        return userPrefs.getFriendBookFilePath();
    }

    @Override
    public void setFriendBookFilePath(Path friendBookFilePath) {
        requireNonNull(friendBookFilePath);
        userPrefs.setFriendBookFilePath(friendBookFilePath);
    }

    @Override
    public Name getUsername() {
        return userPrefs.getUsername();
    }

    @Override
    public StringProperty getUsernameProperty() {
        return this.userNameProperty;
    }

    @Override
    public void setUsername(Name username) {
        requireNonNull(username);
        userPrefs.setUsername(username);

        userNameProperty.setValue(username.fullName);
    }

    //=========== FriendBook ================================================================================

    @Override
    public void setFriendBook(ReadOnlyFriendBook friendBook) {
        this.friendBook.resetData(friendBook);
    }

    @Override
    public ReadOnlyFriendBook getFriendBook() {
        return friendBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return friendBook.hasPerson(person);
    }

    @Override
    public boolean hasPhone(Phone phone) {
        requireNonNull(phone);
        return friendBook.hasPhone(phone);
    }

    @Override
    public boolean hasEmail(Email email) {
        requireNonNull(email);
        return friendBook.hasEmail(email);
    }

    @Override
    public void deletePerson(Person target) {
        friendBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        friendBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        friendBook.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedFriendBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public ObservableList<Person> getFilteredPersonListSortedByBirthday() {
        return filteredPersonsCopy;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return friendBook.equals(other.friendBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
