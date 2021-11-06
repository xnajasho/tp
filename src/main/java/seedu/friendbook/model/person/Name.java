package seedu.friendbook.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.friendbook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the friend book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Names can only contain alphanumeric characters, spaces, hyphens or underscores,"
            + " subjected to the following constraints:\n"
            + "1. Names are not allowed to start with numbers\n"
            + "2. Names cannot consist solely of numbers\n"
            + "3. Hyphens or underscores MUST be preceded by a character\n"
            + "4. Consecutive hyphens or underscores are not allowed\n"
            + "5. Names are not allowed to begin with spaces";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^(?!.*--)(?!.*__)[a-zA-Z][a-zA-Z\\d\\s\\_\\-]*";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }
    /*
    This is to allow readabililty from JSON format
     */
    public Name() {
        this.fullName = "read-from-json";
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && fullName.equals(((Name) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
