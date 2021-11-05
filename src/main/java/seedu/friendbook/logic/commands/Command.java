package seedu.friendbook.logic.commands;

import seedu.friendbook.logic.commands.exceptions.CommandException;
import seedu.friendbook.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {
    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;


    /**
     * Represents enum of all Commands FriendBook presents
     */
    public enum CommandList {
        ADD, EDIT, CLEAR, DELETE, EXIT, FIND, FINDTAG, HELP, LIST, PROFILE, VIEW;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }

        public String getCommandMessageUsage() {
            String usage;
            switch (this) {
            case ADD:
                usage = AddCommand.MESSAGE_USAGE;
                break;
            case EDIT:
                usage = EditCommand.MESSAGE_USAGE;
                break;
            case CLEAR:
                usage = ClearCommand.MESSAGE_USAGE;
                break;
            case DELETE:
                usage = DeleteCommand.MESSAGE_USAGE;
                break;
            case EXIT:
                usage = ExitCommand.MESSAGE_USAGE;
                break;
            case FIND:
                usage = FindCommand.MESSAGE_USAGE;
                break;
            case FINDTAG:
                usage = FindTagCommand.MESSAGE_USAGE;
                break;
            case HELP:
                usage = HelpCommand.MESSAGE_USAGE;
                break;
            case LIST:
                usage = ListCommand.MESSAGE_USAGE;
                break;
            case PROFILE:
                usage = ProfileCommand.MESSAGE_USAGE;
                break;
            case VIEW:
                usage = ViewCommand.MESSAGE_USAGE;
                break;
            default:
                usage = null;
            }
            return usage;
        }
    }

}
