package seedu.friendbook.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.friendbook.commons.util.AppUtil.checkArgument;

import javafx.scene.image.Image;
import seedu.friendbook.MainApp;

/**
 * Represents a Person's picture file name in the friend book.
 * Gurantees: immutable; is valid as declared in {@link #isValidPicture(String)}
 * */
public class Picture {

    public static final String MESSAGE_CONSTRAINTS =
            "Picture should only contain alphanumeric characters and underscores, but can be left blank";

    /*
     * Only alphanumeric and underscores allowed.
     */
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9_]*$";

    public final String value;


    /**
     * Constructs a {@code Picture}.
     *
     * @param picture A valid picture.
     */
    public Picture(String picture) {
        requireNonNull(picture);
        checkArgument(isValidPicture(picture), MESSAGE_CONSTRAINTS);
        this.value = picture;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidPicture(String test) {
        return test.matches(VALIDATION_REGEX) || test.trim().equals("");
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && value.equals(((Picture) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Returns true if picture is empty.
     */
    public boolean isEmpty() {
        return value.isEmpty();
    }

    public Image getImage() throws NullPointerException {
        if (!isValidPicture(value) || isEmpty()) {
            //default avatar pic
            return new Image(MainApp.class.getResourceAsStream("/images/avatar.png"));
        }
        return new Image(MainApp.class.getResourceAsStream("/images/" + this.value + ".png"));
    }
}
