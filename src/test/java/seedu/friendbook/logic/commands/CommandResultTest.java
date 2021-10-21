package seedu.friendbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.friendbook.testutil.TypicalPersons.ALICE;
import static seedu.friendbook.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

public class CommandResultTest {

    private static final CommandResult COMMAND_RESULT = new CommandResult("feedback");

    private static final CommandResult HELP_COMMAND_RESULT =
            new CommandResult("show help please", true, false);

    private static final CommandResult VIEW_ALICE_RESULT =
            new CommandResult("Viewing Alice", ALICE, false, false);

    private static final CommandResult VIEW_BENSON_RESULT =
            new CommandResult("Viewing Benson", BENSON, false, false);

    @Test
    public void getPersonToView() {

        // Any commands other than view command returns -> null
        assertNull(HELP_COMMAND_RESULT.getPersonToView());
        assertNull(COMMAND_RESULT.getPersonToView());

        // View person commands -> return respective person
        assertTrue(VIEW_ALICE_RESULT.getPersonToView().equals(ALICE));
        assertTrue(VIEW_BENSON_RESULT.getPersonToView().equals(BENSON));

        // Command results will never return any other person's info
        assertFalse(VIEW_ALICE_RESULT.getPersonToView().equals(BENSON));
        assertFalse(VIEW_BENSON_RESULT.getPersonToView().equals(ALICE));


    }

    @Test
    public void isShowHelp() {

        // command to view person or give feedback -> return false
        assertFalse(COMMAND_RESULT.isShowHelp());
        assertFalse(VIEW_ALICE_RESULT.isShowHelp());
        assertFalse(VIEW_BENSON_RESULT.isShowHelp());

        // help command requested -> return true
        assertTrue(HELP_COMMAND_RESULT.isShowHelp());
    }

    @Test
    public void isViewPerson() {

        // Any commands results other than view command -> false
        assertFalse(COMMAND_RESULT.isViewPerson());
        assertFalse(HELP_COMMAND_RESULT.isViewPerson());

        // View person command result -> true
        assertTrue(VIEW_ALICE_RESULT.isViewPerson());
        assertTrue(VIEW_BENSON_RESULT.isViewPerson());
    }

    @Test
    public void equals() {

        // same values -> returns true
        assertTrue(COMMAND_RESULT.equals(new CommandResult("feedback")));
        assertTrue(COMMAND_RESULT.equals(new CommandResult("feedback", false, false)));
        assertTrue(VIEW_ALICE_RESULT.equals(
                new CommandResult("Viewing Alice", ALICE, false, false)));
        assertTrue(VIEW_BENSON_RESULT.equals(
                new CommandResult("Viewing Benson", BENSON, false, false)));

        // same object -> returns true
        assertTrue(COMMAND_RESULT.equals(COMMAND_RESULT));
        assertTrue(VIEW_ALICE_RESULT.equals(VIEW_ALICE_RESULT));
        assertTrue(VIEW_BENSON_RESULT.equals(VIEW_BENSON_RESULT));

        // null -> returns false
        assertFalse(COMMAND_RESULT.equals(null));
        assertFalse(VIEW_ALICE_RESULT.equals(null));
        assertFalse(VIEW_BENSON_RESULT.equals(null));

        // different types -> returns false
        assertFalse(COMMAND_RESULT.equals(0.5f));
        assertFalse(VIEW_ALICE_RESULT.equals(0.5f));
        assertFalse(VIEW_BENSON_RESULT.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(COMMAND_RESULT.equals(new CommandResult("different")));
        assertFalse(VIEW_ALICE_RESULT.equals(
                new CommandResult("Different feedback", ALICE, false, false)));
        assertFalse(VIEW_BENSON_RESULT.equals(
                new CommandResult("Different feedback", BENSON, false, false)));

        // different person to view -> return false
        assertFalse(VIEW_ALICE_RESULT.equals(VIEW_BENSON_RESULT));

        // comparing view command and feedback -> false
        assertFalse(VIEW_ALICE_RESULT.equals(HELP_COMMAND_RESULT));

        // different showHelp value -> returns false
        assertFalse(COMMAND_RESULT.equals(new CommandResult("feedback", true, false)));
        assertFalse(VIEW_ALICE_RESULT.equals(
                new CommandResult("Different feedback", ALICE, true, false)));
        assertFalse(VIEW_BENSON_RESULT.equals(
                new CommandResult("Different feedback", BENSON, true, false)));

        // different exit value -> returns false
        assertFalse(COMMAND_RESULT.equals(new CommandResult("feedback", false, true)));
        assertFalse(VIEW_ALICE_RESULT.equals(
                new CommandResult("Different feedback", ALICE, false, true)));
        assertFalse(VIEW_BENSON_RESULT.equals(
                new CommandResult("Different feedback", BENSON, false, true)));
    }

    @Test
    public void hashcode() {

        // same values -> returns same hashcode
        assertEquals(COMMAND_RESULT.hashCode(), new CommandResult("feedback").hashCode());
        assertEquals(VIEW_ALICE_RESULT.hashCode(),
                new CommandResult("Viewing Alice", ALICE, false, false).hashCode());
        assertEquals(VIEW_BENSON_RESULT.hashCode(),
                new CommandResult("Viewing Benson", BENSON, false, false).hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(COMMAND_RESULT.hashCode(), new CommandResult("different").hashCode());
        assertNotEquals(VIEW_ALICE_RESULT.hashCode(),
                new CommandResult("Alice", ALICE, false, false).hashCode());
        assertNotEquals(VIEW_BENSON_RESULT.hashCode(),
                new CommandResult("Benson", BENSON, false, false).hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(COMMAND_RESULT.hashCode(),
                new CommandResult("feedback", true, false).hashCode());
        assertNotEquals(VIEW_ALICE_RESULT.hashCode(),
                new CommandResult("Viewing Alice", ALICE, true, false).hashCode());
        assertNotEquals(VIEW_BENSON_RESULT.hashCode(),
                new CommandResult("Viewing Benson", BENSON, true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(COMMAND_RESULT.hashCode(),
                new CommandResult("feedback", false, true).hashCode());
        assertNotEquals(VIEW_ALICE_RESULT.hashCode(),
                new CommandResult("Viewing Alice", ALICE, false, true).hashCode());
        assertNotEquals(VIEW_BENSON_RESULT.hashCode(),
                new CommandResult("Viewing Benson", BENSON, false, true).hashCode());
    }
}
