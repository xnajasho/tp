package seedu.friendbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.friendbook.logic.commands.exceptions.CommandException;
import seedu.friendbook.model.Model;
import seedu.friendbook.model.person.Name;


/**
 * Sets the app to display the username of the user
 */
public class ProfileCommand extends Command {

    public static final String COMMAND_WORD = "profile";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets the username for the current user.\n"
            + "\nParameters: "
            + PREFIX_NAME + "NAME \n"
            + "\nExample: " + COMMAND_WORD + " n/john_doe";
    public static final String MESSAGE_SUCCESS = "Username set: %1$s";
    public static final String MESSAGE_DUPLICATE_USERNAME = "This username is the same as the current one";

    private final Name username;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public ProfileCommand(Name name) {
        requireNonNull(name);
        this.username = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.getUsername().equals(this.username)) {
            throw new CommandException(MESSAGE_DUPLICATE_USERNAME);
        }

        model.setUsername(this.username);
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.username));
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProfileCommand // instanceof handles nulls
                && username.equals(((ProfileCommand) other).username));
    }
}
