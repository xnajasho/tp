package seedu.friendbook.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
    public static final String MESSAGE_NO = "Friendbook remains unchanged!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Alert clearConfirmationPrompt = new Alert(Alert.AlertType.CONFIRMATION,
                "are sure you want to lose your friends?", ButtonType.NO, ButtonType.YES);
        clearConfirmationPrompt.setTitle("confirm clearing Friendbook?");
        clearConfirmationPrompt.showAndWait();
        if (clearConfirmationPrompt.getResult() == ButtonType.YES) {
            model.setFriendBook(new FriendBook());
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_NO);
        }
    }
}
