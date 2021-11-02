package seedu.friendbook.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.friendbook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.friendbook.model.reminder.Reminder;

public class ReminderTest {

    private static final String VALID_ON_REMINDER_LOWER_CASE = "on";
    private static final String VALID_OFF_REMINDER_LOWER_CASE = "off";
    private static final String VALID_ON_REMINDER_UPPER_CASE = "oN";
    private static final String VALID_OFF_REMINDER_UPPER_CASE = "OFF";

    private static final String INVALID_REMINDER_LOWER_CASE = "onoff";
    private static final String INVALID_REMINDER_UPPER_CASE = "OnOff";


    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Reminder(null));
    }

    @Test
    public void isValidReminder() {
        assertTrue(Reminder.isValidReminder(VALID_ON_REMINDER_LOWER_CASE));
        assertTrue(Reminder.isValidReminder(VALID_OFF_REMINDER_LOWER_CASE));
        assertTrue(Reminder.isValidReminder(VALID_ON_REMINDER_UPPER_CASE));
        assertTrue(Reminder.isValidReminder(VALID_OFF_REMINDER_UPPER_CASE));
        assertTrue(Reminder.isValidReminder(""));

        assertFalse(Reminder.isValidReminder(INVALID_REMINDER_LOWER_CASE));
        assertFalse(Reminder.isValidReminder(INVALID_REMINDER_UPPER_CASE));
    }
}
