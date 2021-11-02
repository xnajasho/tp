package seedu.friendbook.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.friendbook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AvatarTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Avatar(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidAvatar = "25";
        assertThrows(IllegalArgumentException.class, () -> new Avatar(invalidAvatar));
    }

    @Test
    public void isValidAvatar() {
        // null name
        assertThrows(NullPointerException.class, () -> Avatar.isValidAvatar(null));

        // invalid name
        assertFalse(Avatar.isValidAvatar("01"));
        assertFalse(Avatar.isValidAvatar("24"));
        assertFalse(Avatar.isValidAvatar("-1"));
        assertFalse(Avatar.isValidAvatar("00"));

        // valid name
        assertTrue(Avatar.isValidAvatar(""));
        assertTrue(Avatar.isValidAvatar("1"));
        assertTrue(Avatar.isValidAvatar("12"));
        assertTrue(Avatar.isValidAvatar("20"));
        assertTrue(Avatar.isValidAvatar("0"));
        assertTrue(Avatar.isValidAvatar("4"));
    }
}
