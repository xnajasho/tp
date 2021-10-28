package seedu.friendbook.logic.parser;

import static seedu.friendbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.friendbook.logic.commands.FindTagCommand;
import seedu.friendbook.logic.parser.exceptions.ParseException;
import seedu.friendbook.model.person.TagContainsKeywordsPredicate;

public class FindTagCommandParser implements Parser<FindTagCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindTagCommand
     * and returns a FindTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindTagCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTagCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindTagCommand(new TagContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}


