package seedu.friendbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.friendbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.friendbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.friendbook.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.friendbook.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.friendbook.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.friendbook.testutil.TypicalPersons.getTypicalFriendBook;

import org.junit.jupiter.api.Test;

import seedu.friendbook.commons.core.Messages;
import seedu.friendbook.commons.core.index.Index;
import seedu.friendbook.model.Model;
import seedu.friendbook.model.ModelManager;
import seedu.friendbook.model.UserPrefs;
import seedu.friendbook.model.person.Person;

public class ViewCommandTest {

    private Model model = new ModelManager(getTypicalFriendBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToView = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_PERSON_SUCCESS, personToView);

        ModelManager expectedModel = new ModelManager(model.getFriendBook(), new UserPrefs());

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Person personToView = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        ViewCommand viewCommand = new ViewCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_PERSON_SUCCESS, personToView);

        Model expectedModel = new ModelManager(model.getFriendBook(), new UserPrefs());
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of friend book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFriendBook().getPersonList().size());

        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewCommand viewFirstPersonCommand = new ViewCommand(INDEX_FIRST_PERSON);
        ViewCommand viewSecondPersonCommand = new ViewCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(viewFirstPersonCommand.equals(viewFirstPersonCommand));

        // same values -> returns true
        ViewCommand viewFirstPersonCommandCopy = new ViewCommand(INDEX_FIRST_PERSON);
        assertTrue(viewFirstPersonCommand.equals(viewFirstPersonCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstPersonCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstPersonCommand.equals(null));

        // different person -> returns false
        assertFalse(viewFirstPersonCommand.equals(viewSecondPersonCommand));
    }
}
