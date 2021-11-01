package seedu.friendbook.model.person.exceptions;

/**
 * Signals that the operation is invalid due to having invalid Birthday fields
 */
public class BirthdayHasNotOccurredException extends Exception {
    public BirthdayHasNotOccurredException() {
        super("Invalid Birthday Field: Birthday has yet to occur");
    }
}
