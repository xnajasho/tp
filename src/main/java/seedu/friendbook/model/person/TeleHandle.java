package seedu.friendbook.model.person;


import static java.util.Objects.requireNonNull;
import static seedu.friendbook.commons.util.AppUtil.checkArgument;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

/**
 * Represents a Person's telegram handle in the friend book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTeleHandle(String)}
 */

public class TeleHandle {

    public static final String MESSAGE_CONSTRAINTS = "TeleHandle can use a-z and A-Z, 0-9 and underscores, and it "
            + "should have at least 5 characters";

    /*
     * At least 5 characters long; and has only alphabets, digits and underscores.
     */
    public static final String VALIDATION_REGEX = "^[(a-zA-Z0-9_]{5,}$";

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
     * Returns true if a given string is a valid TeleHandle, or is empty
     */
    public static boolean isValidTeleHandle(String test) {
        boolean isValid = test.matches(VALIDATION_REGEX) || test.trim().equals("");
        return isValid;
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
     * Returns true if the tele handle is empty.
     */
    public boolean isEmpty() {
        return value.isEmpty();
    }

    /**
     * Converts telegram handle to a t.me/ link
     */
    public Optional<URL> getTeleHandleUrl() {
        try {
            URL teleUrl = new URL("https://t.me/" + this.value);
            return Optional.of(teleUrl);
        } catch (MalformedURLException e) {
            return Optional.empty();
        }
    }

}
