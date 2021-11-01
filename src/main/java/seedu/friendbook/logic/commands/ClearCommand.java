package seedu.friendbook.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.friendbook.model.FriendBook;
import seedu.friendbook.model.Model;

/**
 * Clears the friend book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears friend and birthday list entirely. \n"
            + "\nExample: " + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "Friendbook has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFriendBook(new FriendBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
