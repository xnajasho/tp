package seedu.friendbook.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.friendbook.commons.core.Messages;
import seedu.friendbook.model.Model;
import seedu.friendbook.model.person.TagContainsKeywordsPredicate;

/**
 * Finds and lists all persons in friend book whose tag contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindTagCommand extends Command {

    public static final String COMMAND_WORD = "findtag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all friends whose tags contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "\nParameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "\nExample: " + COMMAND_WORD + " favorite work school";

    private final TagContainsKeywordsPredicate predicate;

    public FindTagCommand(TagContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTagCommand // instanceof handles nulls
                && predicate.equals(((FindTagCommand) other).predicate)); // state check
    }
}
