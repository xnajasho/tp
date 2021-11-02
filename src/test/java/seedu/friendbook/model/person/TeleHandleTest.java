package seedu.friendbook.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.friendbook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TeleHandleTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Birthday(null));
    }

    @Test
    public void constructor_invalidTelehandle_throwsIllegalArgumentException() {
        String invalidTeleHandle = "xyz";
        assertThrows(IllegalArgumentException.class, () -> new TeleHandle(invalidTeleHandle));
    }

    @Test
    public void isValidTeleHandle() {
        // null tele handle
        assertThrows(NullPointerException.class, () -> TeleHandle.isValidTeleHandle(null));

        // invalid tele handle
        assertFalse(TeleHandle.isValidTeleHandle("abc"));
        assertFalse(TeleHandle.isValidTeleHandle("abc@12345"));
        assertFalse(TeleHandle.isValidTeleHandle("@abc12#"));
        // valid tele handles
        assertTrue(TeleHandle.isValidTeleHandle(""));
        assertTrue(TeleHandle.isValidTeleHandle("mari12"));
        assertTrue(TeleHandle.isValidTeleHandle("baked_cookie_dough"));
    }

}
