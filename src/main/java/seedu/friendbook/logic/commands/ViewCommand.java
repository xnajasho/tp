package seedu.friendbook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.friendbook.commons.core.Messages;
import seedu.friendbook.commons.core.index.Index;
import seedu.friendbook.logic.commands.exceptions.CommandException;
import seedu.friendbook.model.Model;
import seedu.friendbook.model.person.Person;

/**
 * View the details of the identified person using it's displayed index from the friend book.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": View the details of the person identified by the index number used in the displayed person list.\n"
            + "\nParameters: INDEX (must be a positive integer)\n"
            + "\nExample: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_PERSON_SUCCESS = "View Person: %1$s";

    private final Index targetIndex;

    public ViewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToView = lastShownList.get(targetIndex.getZeroBased());
        return new CommandResult(
                String.format(MESSAGE_VIEW_PERSON_SUCCESS, personToView), personToView, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && targetIndex.equals(((ViewCommand) other).targetIndex)); // state check
    }
}
