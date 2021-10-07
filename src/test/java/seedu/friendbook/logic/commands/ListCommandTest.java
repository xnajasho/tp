package seedu.friendbook.logic.commands;

import static seedu.friendbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.friendbook.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.friendbook.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.friendbook.testutil.TypicalPersons.getTypicalFriendBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.friendbook.model.Model;
import seedu.friendbook.model.ModelManager;
import seedu.friendbook.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFriendBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getFriendBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
