package seedu.friendbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.friendbook.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import seedu.friendbook.commons.core.GuiSettings;
import seedu.friendbook.logic.commands.exceptions.CommandException;
import seedu.friendbook.model.FriendBook;
import seedu.friendbook.model.Model;
import seedu.friendbook.model.ReadOnlyFriendBook;
import seedu.friendbook.model.ReadOnlyUserPrefs;
import seedu.friendbook.model.person.Email;
import seedu.friendbook.model.person.Name;
import seedu.friendbook.model.person.Person;
import seedu.friendbook.model.person.Phone;
import seedu.friendbook.testutil.PersonBuilder;
import seedu.friendbook.testutil.TypicalPersons;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_personSameNameBirthdayAddress_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();

        Person validPerson = new PersonBuilder(TypicalPersons.BENSON).build();
        Person validPerson2SameNameBirthdayAddress = new PersonBuilder(TypicalPersons.BENSON)
                .withPhone(TypicalPersons.ALICE.getPhone().value)
                .withEmail(TypicalPersons.ALICE.getEmail().value)
                .build();

        CommandResult commandResult1 = new AddCommand(validPerson).execute(modelStub);
        CommandResult commandResult2 = new AddCommand(validPerson2SameNameBirthdayAddress).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult1.getFeedbackToUser());
        assertEquals(String.format(
                AddCommand.MESSAGE_SUCCESS, validPerson2SameNameBirthdayAddress), commandResult2.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson, validPerson2SameNameBirthdayAddress), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_personWithSamePhone_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        Person validPerson2WithSameNumber = new PersonBuilder(TypicalPersons.ALICE)
                .withPhone(validPerson.getPhone().value).build();
        AddCommand addCommand = new AddCommand(validPerson2WithSameNumber);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_PHONE_NUMBER_EXISTS, () ->
                addCommand.execute(modelStub));
    }

    @Test
    public void execute_personWithSameEmail_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        Person validPerson2WithSameEmail = new PersonBuilder(TypicalPersons.BENSON)
                .withEmail(validPerson.getEmail().value).build();
        AddCommand addCommand = new AddCommand(validPerson2WithSameEmail);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_EMAIL_EXISTS, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
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
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }

        @Override
        public boolean hasPhone(Phone phone) {
            requireNonNull(phone);
            return this.person.getPhone().equals(phone);
        }

        @Override
        public boolean hasEmail(Email email) {
            requireNonNull(email);
            return this.person.getEmail().equals(email);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public boolean hasPhone(Phone phone) {
            requireNonNull(phone);
            return personsAdded.stream().anyMatch(personOther -> personOther.getPhone().equals(phone));
        }

        @Override
        public boolean hasEmail(Email email) {
            requireNonNull(email);
            return personsAdded.stream().anyMatch(personOther -> personOther.getEmail().equals(email));
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyFriendBook getFriendBook() {
            return new FriendBook();
        }
    }

}
