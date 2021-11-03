package seedu.friendbook.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_CONTAINS_FIELD_IN_BRACKETS = "Unable to parse command as %s field contains"
            + " fields enclosed in square brackets, e.g [t/TAG]\n"
            + "Please remove all enclosing square brackets.";
    public static final String MESSAGE_COMMAND_CONTAINS_UPPERCASE = "%s command contains UPPERCASE characters."
            + " Please enter LOWERCASE characters only";
}
