package seedu.friendbook.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.friendbook.commons.util.AppUtil.checkArgument;

import javafx.scene.image.Image;
import seedu.friendbook.MainApp;

/**
 * Represents a Person's avatar file name in the friend book.
 * Gurantees: immutable; is valid as declared in {@link #isValidAvatar(String)}
 * */
public class Avatar {

    public static final String AVATAR_PATH = "/images/avatars/Avatar";

    /*
     * Only alphanumeric and underscores allowed.
     */
    public static final String VALIDATION_REGEX = "/^[0-9]$|^[0-9]$|^1[0-9]$|^20$|^0$/";

    public static final String DEFAULT_AVATAR = "0";

    public static final Image DEFAULT_AVATAR_IMAGE = new Image(
            MainApp.class.getResourceAsStream(AVATAR_PATH + "0.png"));

    public static final String MESSAGE_CONSTRAINTS =
            "Choose Avatar from 0-20 only";

    public final String value;

    /**
     * Constructs an {@code Avatar}.
     *
     * @param avatar A valid avatar number.
     */
    public Avatar(String avatar) {
        requireNonNull(avatar);
        checkArgument(isValidAvatar(avatar), MESSAGE_CONSTRAINTS);
        this.value = avatar;
    }

    /**
     * Returns true if a given string is a valid avatar.
     */
    public static boolean isValidAvatar(String test) {
        return test.matches(VALIDATION_REGEX) || test.trim().equals("");
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Avatar // instanceof handles nulls
                && value.equals(((Avatar) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Returns true if the avatar is empty.
     */
    public boolean isEmpty() {
        return value.isEmpty();
    }


    public Image getImage() throws NullPointerException {
        if (!isValidAvatar(value)) {
            return DEFAULT_AVATAR_IMAGE;
        }
        return new Image(
                MainApp.class.getResourceAsStream(AVATAR_PATH + this.value + ".png"));
    }
}
