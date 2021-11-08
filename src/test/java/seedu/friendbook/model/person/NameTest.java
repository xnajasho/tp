package seedu.friendbook.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.friendbook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("peter*")); // contains non-alphanumeric characters
        assertFalse(Name.isValidName("               Jack")); // name preceded with spaces
        assertFalse(Name.isValidName("12345")); // numbers only
        assertFalse(Name.isValidName("123124 John Lim")); // names beginning with numbers
        assertFalse(Name.isValidName("123124John"));
        assertFalse(Name.isValidName("-")); // only hyphen
        assertFalse(Name.isValidName("_")); // only underscore
        assertFalse(Name.isValidName("james--")); // two consecutive hyphens
        assertFalse(Name.isValidName("james__")); // two consecutive underscores
        assertFalse(Name.isValidName("james-----------")); // many consecutive hyphens
        assertFalse(Name.isValidName("james___________")); // many consecutive underscores

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
        assertTrue(Name.isValidName("Peter X-VII")); // contains hyphen
        assertTrue(Name.isValidName("James X_VII")); // contains underscore
        assertTrue(Name.isValidName("a_")); // underscore preceded with a single character
        assertTrue(Name.isValidName("b-")); // hyphen preceded with a single character
        assertTrue(Name.isValidName("jamus-lim-chye-wee")); // multiple hyphens
        assertTrue(Name.isValidName("jamus_lim_chye_wee")); // multiple underscores
    }
}
