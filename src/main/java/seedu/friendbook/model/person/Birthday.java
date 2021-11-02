package seedu.friendbook.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.friendbook.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;

import seedu.friendbook.model.person.exceptions.BirthdayHasNotOccurredException;

public class Birthday {

    public static final String MESSAGE_CONSTRAINTS =
            "Birthdays should be in 'YYYY-MM-DD' format and should not be blank";

    public final String value;

    /**
     * Constructs a {@code Birthday}.
     *
     * @param birthday A valid birthday in 'YYYY-MM-DD' format.
     */
    public Birthday(String birthday) {
        requireNonNull(birthday);
        checkArgument(isValidFormat(birthday), MESSAGE_CONSTRAINTS);
        value = birthday;
    }

    /**
     * Checks if the specified birthday has passed
     * @param birthday A valid birthday in 'YYYY-MM-DD' format
     * @return false if birthday has yet to occur
     */
    public static boolean hasBirthdayPassed(LocalDate birthday) {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getYear() > birthday.getYear()
                || currentDate.getYear() == birthday.getYear()
                    && currentDate.getMonthValue() > birthday.getMonthValue()
                || currentDate.getYear() == birthday.getYear()
                    && currentDate.getMonthValue() == birthday.getMonthValue()
                    && currentDate.getDayOfMonth() >= birthday.getDayOfMonth();
    }

    /**
     * Returns true if the input birthday follows the 'yyyy-MM-dd' format
     */
    public static boolean isValidFormat(String test) {
        boolean isValid;

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd").withResolverStyle(ResolverStyle.LENIENT);

        try {
            LocalDate date = formatter.parse(test, LocalDate::from);
            isValid = true;
        } catch (DateTimeParseException e) {
            isValid = false;
        }
        return isValid;
    }

    /**
     * Checks if the birthday contains any invalid values i.e DayOfMonth > 31,leap month values etc
     * @throws DateTimeException first if values are invalid
     * @throws BirthdayHasNotOccurredException next if birthday has yet to occur
     */
    public static void invalidValuesCheck(String birthday) throws BirthdayHasNotOccurredException {
        boolean hasPassed;
        try {
            LocalDate date = LocalDate.parse(birthday);
            hasPassed = hasBirthdayPassed(date);
        } catch (DateTimeException e) {
            throw new DateTimeException(e.getMessage());
        }
        if (!hasPassed) {
            throw new BirthdayHasNotOccurredException();
        }
    }

    public String getActualDate() {
        LocalDate date = LocalDate.parse(value);
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Calculates the remaining days until specified birthday
     */
    public long calculateRemainingDaysToBirthday() {
        if (isTodayBirthday()) {
            return 0;
        } else {
            LocalDate today = LocalDate.now();
            LocalDate birthday = LocalDate.parse(value);
            LocalDate nextBday = birthday.withYear(today.getYear());

            //If your birthday has occurred this year already, add 1 to the year.
            if (nextBday.isBefore(today)) {
                nextBday = nextBday.plusYears(1);
            }

            return ChronoUnit.DAYS.between(today, nextBday);
        }
    }

    /**
     * Helper method to check if specified birthday is today
     */
    public boolean isTodayBirthday() {
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.parse(value);
        return birthday.getMonth().equals(today.getMonth())
                && birthday.getDayOfMonth() == today.getDayOfMonth();
    }

    /**
     * Calculates the age based on the birthday date
     */
    public int calculateAge() {
        LocalDate today = LocalDate.now();
        LocalDate bday = LocalDate.parse(value);
        return Period.between(bday, today).getYears();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Birthday
                && value.equals(((Birthday) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
