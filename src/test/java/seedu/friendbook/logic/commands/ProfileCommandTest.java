package seedu.friendbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.friendbook.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import seedu.friendbook.commons.core.GuiSettings;
import seedu.friendbook.logic.commands.exceptions.CommandException;
import seedu.friendbook.model.Model;
import seedu.friendbook.model.ReadOnlyFriendBook;
import seedu.friendbook.model.ReadOnlyUserPrefs;
import seedu.friendbook.model.person.Email;
import seedu.friendbook.model.person.Name;
import seedu.friendbook.model.person.Person;
import seedu.friendbook.model.person.Phone;

public class ProfileCommandTest {

    @Test
    public void constructor_nullName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ProfileCommand(null));
    }

    @Test
    public void execute_usernameReset_usernameResetSuccessful() throws Exception {
        ModelStubAcceptsNewUsername modelStub = new ModelStubAcceptsNewUsername(new Name("old"));
        Name validName = new Name("new");

        CommandResult commandResult = new ProfileCommand(validName).execute(modelStub);

        assertEquals(String.format(ProfileCommand.MESSAGE_SUCCESS, validName), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_duplicateUsername_throwsCommandException() {
        ModelStubAcceptsNewUsername modelStub = new ModelStubAcceptsNewUsername(new Name("old"));
        Name validName = new Name("old");
        ProfileCommand profileCommand = new ProfileCommand(validName);

        assertThrows(CommandException.class, ProfileCommand.MESSAGE_DUPLICATE_USERNAME, (
                ) -> profileCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        ProfileCommand profileCommand1 = new ProfileCommand(new Name("alice"));
        ProfileCommand profileCommand2 = new ProfileCommand(new Name("Bob"));

        // same object -> returns true
        assertTrue(profileCommand1.equals(profileCommand1));

        // same values -> returns true
        ProfileCommand profileCommand1Copy = new ProfileCommand(new Name("alice"));
        assertTrue(profileCommand1.equals(profileCommand1Copy));

        // different types -> returns false
        assertFalse(profileCommand1.equals(1));

        // null -> returns false
        assertFalse(profileCommand1.equals(null));

        // different person -> returns false
        assertFalse(profileCommand1.equals(profileCommand2));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Name getUsername() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public StringProperty getUsernameProperty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUsername(Name username) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getFriendBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFriendBookFilePath(Path friendBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFriendBook(ReadOnlyFriendBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyFriendBook getFriendBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPhone(Phone phone) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEmail(Email email) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonListSortedByBirthday() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }
    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubAcceptsNewUsername extends ModelStub {
        private Name name;

        ModelStubAcceptsNewUsername(Name name) {
            requireNonNull(name);
            this.name = name;
        }

        @Override
        public Name getUsername() {
            return this.name;
        }

        @Override
        public void setUsername(Name name) {
            this.name = name;
        }

    }


}
