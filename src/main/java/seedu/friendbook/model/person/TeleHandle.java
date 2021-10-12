package seedu.friendbook.model.person;


import static java.util.Objects.requireNonNull;
import static seedu.friendbook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's telegram handle in the friend book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTeleHandle(String)} #TODO add method
 */
//TODO write tests for this class
public class TeleHandle {
    //TODO add actual tele username constraints
    public static final String MESSAGE_CONSTRAINTS = "TeleHandle can take any value, and it should not be blank";

    /*
     * The first character of the TeleHandle must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a {@code TeleHandle}.
     *
     * @param teleHandle A valid telegram handle.
     */
    public TeleHandle(String teleHandle) {
        requireNonNull(teleHandle);
        checkArgument(isValidTeleHandle(teleHandle), MESSAGE_CONSTRAINTS);
        value = teleHandle;
    }

    /**
     * Returns true if a given string is a valid TeleHandle.
     */
    public static boolean isValidTeleHandle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TeleHandle // instanceof handles nulls
                && value.equals(((TeleHandle) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Returns true if the user has provided a tele handle, false if there's a default value
     */
    public boolean isSet() {
        return !(value == "#DEFAULT#");
    }

}
