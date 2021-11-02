package seedu.friendbook.logic.parser;

import static seedu.friendbook.commons.core.Messages.MESSAGE_COMMAND_CONTAINS_UPPERCASE;
import static seedu.friendbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.friendbook.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.friendbook.logic.commands.AddCommand;
import seedu.friendbook.logic.commands.ClearCommand;
import seedu.friendbook.logic.commands.Command;
import seedu.friendbook.logic.commands.DeleteCommand;
import seedu.friendbook.logic.commands.EditCommand;
import seedu.friendbook.logic.commands.ExitCommand;
import seedu.friendbook.logic.commands.FindCommand;
import seedu.friendbook.logic.commands.FindTagCommand;
import seedu.friendbook.logic.commands.HelpCommand;
import seedu.friendbook.logic.commands.ListCommand;
import seedu.friendbook.logic.commands.ProfileCommand;
import seedu.friendbook.logic.commands.ViewCommand;
import seedu.friendbook.logic.parser.exceptions.ParseException;


/**
 * Parses user input.
 */
public class FriendBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Used to check if command contains any upper case characters
     */
    private static final String UPPER_CASE_REGEX = ".*[A-Z].*";

    /**
     * Parses user input(case-sensitive) into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format, or contains uppercase
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case FindTagCommand.COMMAND_WORD:
            return new FindTagCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);
        case ProfileCommand.COMMAND_WORD:
            return new ProfileCommandParser().parse(arguments);

        default:
            if (hasUpperCase(commandWord)) {
                throw new ParseException(String.format(MESSAGE_COMMAND_CONTAINS_UPPERCASE, commandWord.toLowerCase()));
            } else {
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }
        }
    }

    /**
     * Checks if the given command message contains uppercase characters.
     * @return true if it is a valid command and contains uppercase characters.
     */
    public static boolean hasUpperCase(String message) {
        return isAValidCommand(message) && message.matches(UPPER_CASE_REGEX);
    }

    /**
     * Helper method to check if the command is valid.
     * A command is valid if it is a case-insensitive version of the actual command word.
     */
    public static boolean isAValidCommand(String message) {
        String lowerCaseCommand = message.toLowerCase();
        return lowerCaseCommand.equals(AddCommand.COMMAND_WORD)
                || lowerCaseCommand.equals(EditCommand.COMMAND_WORD)
                || lowerCaseCommand.equals(DeleteCommand.COMMAND_WORD)
                || lowerCaseCommand.equals(ClearCommand.COMMAND_WORD)
                || lowerCaseCommand.equals(FindCommand.COMMAND_WORD)
                || lowerCaseCommand.equals(FindTagCommand.COMMAND_WORD)
                || lowerCaseCommand.equals(ListCommand.COMMAND_WORD)
                || lowerCaseCommand.equals(ExitCommand.COMMAND_WORD)
                || lowerCaseCommand.equals(HelpCommand.COMMAND_WORD)
                || lowerCaseCommand.equals(ViewCommand.COMMAND_WORD)
                || lowerCaseCommand.equals(ProfileCommand.COMMAND_WORD);
    }
}
