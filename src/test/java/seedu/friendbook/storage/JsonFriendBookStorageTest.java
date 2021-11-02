package seedu.friendbook.storage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.friendbook.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.friendbook.commons.exceptions.DataConversionException;
import seedu.friendbook.model.FriendBook;
import seedu.friendbook.model.ReadOnlyFriendBook;

public class JsonFriendBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonFriendBookStorageTest");

    //@TempDir
    //public Path testFolder;

    @Test
    public void readFriendBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readFriendBook(null));
    }

    private java.util.Optional<ReadOnlyFriendBook> readFriendBook(String filePath) throws Exception {
        return new JsonFriendBookStorage(Paths.get(filePath)).readFriendBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readFriendBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readFriendBook("notJsonFormatFriendBook.json"));
    }

    @Test
    public void readFriendBook_invalidPersonFriendBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFriendBook("invalidNameInPersonFriendBook.json"));
        assertThrows(DataConversionException.class, () -> readFriendBook("invalidAddressInPersonFriendBook.json"));
        assertThrows(DataConversionException.class, () -> readFriendBook("invalidAvatarInPersonFriendBook.json"));
        assertThrows(DataConversionException.class, () -> readFriendBook("invalidBirthdayInPersonFriendBook.json"));
        assertThrows(DataConversionException.class, () -> readFriendBook("invalidEmailInPersonFriendBook.json"));
        assertThrows(DataConversionException.class, () -> readFriendBook("invalidPhoneInPersonFriendBook.json"));
        assertThrows(DataConversionException.class, () -> readFriendBook("invalidTagInPersonFriendBook.json"));
        assertThrows(DataConversionException.class, () -> readFriendBook("invalidReminderInPersonFriendBook.json"));
        assertThrows(DataConversionException.class, () -> readFriendBook("invalidTeleHandleInPersonFriendBook.json"));
    }
    @Test
    public void readFriendBook_invalidAndValidPersonFriendBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFriendBook("invalidAndValidPersonFriendBook.json"));
    }

    /*@Test
    public void readAndSaveFriendBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempFriendBook.json");
        FriendBook original = getTypicalFriendBook();
        JsonFriendBookStorage jsonFriendBookStorage = new JsonFriendBookStorage(filePath);

        // Save in new file and read back
        jsonFriendBookStorage.saveFriendBook(original, filePath);
        ReadOnlyFriendBook readBack = jsonFriendBookStorage.readFriendBook(filePath).get();
        assertEquals(original, new FriendBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonFriendBookStorage.saveFriendBook(original, filePath);
        readBack = jsonFriendBookStorage.readFriendBook(filePath).get();
        assertEquals(original, new FriendBook(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonFriendBookStorage.saveFriendBook(original); // file path not specified
        readBack = jsonFriendBookStorage.readFriendBook().get(); // file path not specified
        assertEquals(original, new FriendBook(readBack));

    }*/

    @Test
    public void saveFriendBook_nullFriendBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFriendBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code friendBook} at the specified {@code filePath}.
     */
    private void saveFriendBook(ReadOnlyFriendBook friendBook, String filePath) {
        try {
            new JsonFriendBookStorage(Paths.get(filePath))
                    .saveFriendBook(friendBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveFriendBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFriendBook(new FriendBook(), null));
    }
}
