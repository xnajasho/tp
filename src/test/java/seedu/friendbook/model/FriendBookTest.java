package seedu.friendbook.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.friendbook.testutil.Assert.assertThrows;
import static seedu.friendbook.testutil.TypicalPersons.ALICE;
import static seedu.friendbook.testutil.TypicalPersons.getTypicalFriendBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.friendbook.model.person.Person;
import seedu.friendbook.model.person.exceptions.DuplicatePersonException;
import seedu.friendbook.testutil.PersonBuilder;

public class FriendBookTest {

    private final FriendBook friendBook = new FriendBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), friendBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> friendBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyFriendBook_replacesData() {
        FriendBook newData = getTypicalFriendBook();
        friendBook.resetData(newData);
        assertEquals(newData, friendBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        FriendBookStub newData = new FriendBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> friendBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> friendBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInFriendBook_returnsFalse() {
        assertFalse(friendBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInFriendBook_returnsTrue() {
        friendBook.addPerson(ALICE);
        assertTrue(friendBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInFriendBook_returnsTrue() {
        friendBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(friendBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> friendBook.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyFriendBook whose persons list can violate interface constraints.
     */
    private static class FriendBookStub implements ReadOnlyFriendBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Person> personsCopy = FXCollections.observableArrayList();
        private final Comparator<Person> comparator = Comparator.comparingInt(Person::getDaysToRemainingBirthday);

        FriendBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
            this.personsCopy.setAll(persons);
            this.personsCopy.sort(comparator);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Person> getSortedPersonListByBirthday() {
            return personsCopy;
        }
    }

}
