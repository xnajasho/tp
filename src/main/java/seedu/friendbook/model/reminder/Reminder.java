package seedu.friendbook.model.reminder;

import static java.util.Objects.requireNonNull;

public class Reminder {

    public static final String MESSAGE_CONSTRAINTS =
            "Reminder should only contain on or off as values ";

    private String stringValue;

    /**
     * Constructs a {@code Reminder}.
     *
     * @param stringValue A valid reminder value.
     */
    public Reminder(String stringValue) {
        requireNonNull(stringValue);
        this.stringValue = stringValue.toLowerCase();
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setReminder(Boolean booleanValue) {
        this.stringValue = booleanValue == true ? "on" : "off";
    }

    public Boolean getBooleanValue() {
        return stringValue.equals("on");
    }

    /**
     * Returns true if a given reminder is valid. Values are case-insensitive
     */
    public static boolean isValidReminder(String test) {
        return test.equalsIgnoreCase("on") || test.equalsIgnoreCase("off") || test.trim().equals("");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Reminder // instanceof handles nulls
                && stringValue.equals(((Reminder) other).stringValue)); // state check
    }

    @Override
    public int hashCode() {
        return stringValue.hashCode();
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
