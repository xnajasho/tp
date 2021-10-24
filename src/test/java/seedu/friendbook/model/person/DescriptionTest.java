package seedu.friendbook.model.person;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.friendbook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidDescription_throwsNullPointerException() {
        String invalidDescription = null;
        assertThrows(NullPointerException.class, () -> new Description(invalidDescription));
    }

    @Test
    public void isValidDescription() {
        // null description
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // valid description
        assertTrue(Description.isValidDescription("owes a lottta money"));
        assertTrue(Description.isValidDescription("good friend"));
    }

}
