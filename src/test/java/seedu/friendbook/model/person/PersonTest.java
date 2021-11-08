package seedu.friendbook.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_AVATAR_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_BIRTHDAY_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_REMINDER_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_TELEHANDLE_BOB;
import static seedu.friendbook.testutil.Assert.assertThrows;
import static seedu.friendbook.testutil.TypicalPersons.ALICE;
import static seedu.friendbook.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.friendbook.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void checkAge() {
        assertEquals(26, ALICE.getAge());
        assertEquals(26, BOB.getAge());
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, phone, email, birthday, all other attributes different -> true
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND).withTeleHandle(VALID_TELEHANDLE_BOB)
                .withDescription(VALID_DESCRIPTION_BOB).withAvatar(VALID_AVATAR_BOB)
                .withReminder(VALID_REMINDER_BOB).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // same name, all other attributes different -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withBirthday(VALID_BIRTHDAY_BOB)
                .withTags(VALID_TAG_HUSBAND).withTeleHandle(VALID_TELEHANDLE_BOB)
                .withDescription(VALID_DESCRIPTION_BOB).withAvatar(VALID_AVATAR_BOB)
                .withReminder(VALID_REMINDER_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // same name, phone, all other attributes different -> false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withBirthday(VALID_BIRTHDAY_BOB)
                .withTags(VALID_TAG_HUSBAND).withTeleHandle(VALID_TELEHANDLE_BOB)
                .withDescription(VALID_DESCRIPTION_BOB).withAvatar(VALID_AVATAR_BOB)
                .withReminder(VALID_REMINDER_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // same name, phone, email, all other attributes different -> false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .withBirthday(VALID_BIRTHDAY_BOB)
                .withTags(VALID_TAG_HUSBAND).withTeleHandle(VALID_TELEHANDLE_BOB)
                .withDescription(VALID_DESCRIPTION_BOB).withAvatar(VALID_AVATAR_BOB)
                .withReminder(VALID_REMINDER_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // different name & phone, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // different name & phone & email, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // different name & phone & email & birthday, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withBirthday(VALID_BIRTHDAY_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // nam & email differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase())
                .withEmail(VALID_EMAIL_BOB.toUpperCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));

        // different birthday -> return false
        editedAlice = new PersonBuilder(ALICE).withBirthday(VALID_BIRTHDAY_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different avatar -> return false
        editedAlice = new PersonBuilder(ALICE).withAvatar("10").build();
        assertFalse(ALICE.equals(editedAlice));

        // different description -> return false
        editedAlice = new PersonBuilder(ALICE).withDescription("changed description").build();
        assertFalse(ALICE.equals(editedAlice));

        // different teleHandle -> return false
        editedAlice = new PersonBuilder(ALICE).withTeleHandle("aliceinwonderland").build();
        assertFalse(ALICE.equals(editedAlice));

        // different reminder -> return false
        editedAlice = new PersonBuilder(ALICE).withReminder("on").build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
