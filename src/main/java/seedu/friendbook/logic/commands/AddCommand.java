package seedu.friendbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_AVATAR;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_TELEHANDLE;

import seedu.friendbook.logic.commands.exceptions.CommandException;
import seedu.friendbook.model.Model;
import seedu.friendbook.model.person.Person;


/**
 * Adds a person to the friend book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the friend book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_BIRTHDAY + "BIRTHDAY "
            + PREFIX_AVATAR + "<OPTIONAL> AVATAR"
            + PREFIX_TELEHANDLE + "<OPTIONAL> TELE HANDLE "
            + PREFIX_DESCRIPTION + "<OPTIONAL> DESCRIPTION"
            + "<OPTIONAL> [" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_BIRTHDAY + "1994-05-15 "
            + PREFIX_AVATAR + "1 "
            + PREFIX_TELEHANDLE + "JohnDoe123 "
            + PREFIX_DESCRIPTION + "nice guy "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_PLACEHOLDER = COMMAND_WORD + " "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_BIRTHDAY + "BIRTHDAY "
            + PREFIX_AVATAR + "AVATAR"
            + PREFIX_TELEHANDLE + "TELEHANDLE "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + "[" + PREFIX_TAG + "TAG]...\n";
    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the friend book";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
