package seedu.friendbook.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.friendbook.testutil.Assert.assertThrows;

import java.time.DateTimeException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.friendbook.model.person.exceptions.BirthdayHasNotOccurredException;

public class BirthdayTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Birthday(null));
    }

    @Test
    public void constructor_invalidBirthday_throwsIllegalArgumentException() {
        String invalidBirthday = "1993/04/20";
        assertThrows(IllegalArgumentException.class, () -> new Birthday(invalidBirthday));
    }

    @Test
    public void isValidBirthday() {
        // null birthday
        assertThrows(NullPointerException.class, () -> Birthday.isValidFormat(null));

        // invalid birthday formats
        assertFalse(Birthday.isValidFormat(""));
        assertFalse(Birthday.isValidFormat("1993/04/20"));
        assertFalse(Birthday.isValidFormat("1994/20/04"));
        assertFalse(Birthday.isValidFormat("20/04/98"));
        assertFalse(Birthday.isValidFormat("20/04/1998"));
        assertFalse(Birthday.isValidFormat("26 Oct 1998"));

        // valid birthdays formats
        assertTrue(Birthday.isValidFormat("1994-04-15"));
        assertTrue(Birthday.isValidFormat("2021-03-25"));
        assertTrue(Birthday.isValidFormat("2021-10-05"));
        assertTrue(Birthday.isValidFormat("2016-04-29")); // leap year
    }

    @Test
    public void invalidValuesCheck() {
        assertThrows(DateTimeException.class, () -> Birthday.invalidValuesCheck("2010-04-34"));
        assertThrows(DateTimeException.class, () -> Birthday.invalidValuesCheck("2015-02-29")); // not leap year

        // invalid DoM for September, only up to 30
        assertThrows(DateTimeException.class, () -> Birthday.invalidValuesCheck("2021-09-31"));

        // Invalid DoM field
        assertThrows(DateTimeException.class, () -> Birthday.invalidValuesCheck("2021-09-00"));

        // invalid Month fields
        assertThrows(DateTimeException.class, () -> Birthday.invalidValuesCheck("2021-13-12"));
        assertThrows(DateTimeException.class, () -> Birthday.invalidValuesCheck("2021-00-12"));

        // Birthday has yet to occur but Invalid values are thrown first
        assertThrows(DateTimeException.class, () -> Birthday.invalidValuesCheck("2022-00-12"));
        assertThrows(DateTimeException.class, () -> Birthday.invalidValuesCheck("2022-01-32"));

        // invalid Birthdays as they have yet to occur
        assertThrows(BirthdayHasNotOccurredException.class, () -> Birthday.invalidValuesCheck("2022-04-15"));
        assertThrows(BirthdayHasNotOccurredException.class, () -> Birthday.invalidValuesCheck("2022-02-12"));
        assertThrows(BirthdayHasNotOccurredException.class, () -> Birthday.invalidValuesCheck("2023-12-30"));

    }

    @Test
    public void hasBirthdayPassed() {

        assertTrue(Birthday.hasBirthdayPassed(LocalDate.of(2021, 9, 22)));
        assertTrue(Birthday.hasBirthdayPassed(LocalDate.of(2021, 10, 31)));

        assertFalse(Birthday.hasBirthdayPassed(LocalDate.of(2050, 12, 31)));
        assertFalse(Birthday.hasBirthdayPassed(LocalDate.of(2099, 2, 13)));
        assertFalse(Birthday.hasBirthdayPassed(LocalDate.of(2050, 10, 25)));
        assertFalse(Birthday.hasBirthdayPassed(LocalDate.of(2045, 5, 2)));
    }

    @Test
    public void getActualDate() {
        Birthday testBirthday = new Birthday("2021-04-15");
        assertEquals("Apr 15 2021", testBirthday.getActualDate());
    }

    @Test
    public void calculateAgeTest() {
        Birthday birthdayPassed = new Birthday("1994-05-20");
        Birthday birthdayUpcoming = new Birthday("1995-12-28");

        assertEquals(27, birthdayPassed.calculateAge());
        assertEquals(25, birthdayUpcoming.calculateAge());
    }
}
