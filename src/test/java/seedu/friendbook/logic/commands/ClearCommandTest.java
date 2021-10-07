package seedu.friendbook.logic.commands;

import static seedu.friendbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.friendbook.testutil.TypicalPersons.getTypicalFriendBook;

import org.junit.jupiter.api.Test;

import seedu.friendbook.model.FriendBook;
import seedu.friendbook.model.Model;
import seedu.friendbook.model.ModelManager;
import seedu.friendbook.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyFriendBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyFriendBook_success() {
        Model model = new ModelManager(getTypicalFriendBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalFriendBook(), new UserPrefs());
        expectedModel.setFriendBook(new FriendBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
