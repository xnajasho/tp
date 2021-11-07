package seedu.friendbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_AVATAR;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_REMINDER;
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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the friend book.\n"
            + "\nParameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_BIRTHDAY + "BIRTHDAY "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_AVATAR + "AVATAR] "
            + "[" + PREFIX_TELEHANDLE + "TELE HANDLE] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_REMINDER + "REMINDER] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_BIRTHDAY + "1994-05-15 "
            + PREFIX_ADDRESS + "123, Clementi Rd, S1234665 "
            + PREFIX_AVATAR + "1 "
            + PREFIX_TELEHANDLE + "JohnDoe123 "
            + PREFIX_DESCRIPTION + "nice guy "
            + PREFIX_REMINDER + "on "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_PLACEHOLDER = COMMAND_WORD + " "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_BIRTHDAY + "BIRTHDAY "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_AVATAR + "AVATAR] "
            + "[" + PREFIX_TELEHANDLE + "TELEHANDLE] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_REMINDER + "REMINDER] "
            + "[" + PREFIX_TAG + "TAG]...\n";
    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the friend book";
    public static final String MESSAGE_PHONE_NUMBER_EXISTS = "Phone number is already used by someone else";
    public static final String MESSAGE_EMAIL_EXISTS = "Email is already used by someone else";

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
        if (model.hasPhone(toAdd.getPhone())) {
            throw new CommandException(MESSAGE_PHONE_NUMBER_EXISTS);
        }
        if (model.hasEmail(toAdd.getEmail())) {
            throw new CommandException(MESSAGE_EMAIL_EXISTS);
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
