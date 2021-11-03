package seedu.friendbook.storage;

import static seedu.friendbook.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.friendbook.commons.exceptions.IllegalValueException;
import seedu.friendbook.commons.util.JsonUtil;

public class JsonSerializableFriendBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsFriendBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonFriendBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonFriendBook.json");
    private static final Path DUPLICATE_PHONE_FILE = TEST_DATA_FOLDER.resolve("duplicatePhone.json");
    private static final Path DUPLICATE_EMAIL_FILE = TEST_DATA_FOLDER.resolve("duplicateEmail.json");

    /*@Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableFriendBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableFriendBook.class).get();
        FriendBook friendBookFromFile = dataFromFile.toModelType();
        FriendBook typicalPersonsFriendBook = TypicalPersons.getTypicalFriendBook();
        assertEquals(friendBookFromFile, typicalPersonsFriendBook);
    }*/

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableFriendBook dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableFriendBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableFriendBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableFriendBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableFriendBook.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePhone_throwsIllegalValueException() throws Exception {
        JsonSerializableFriendBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PHONE_FILE,
                JsonSerializableFriendBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableFriendBook.MESSAGE_PHONE_NUMBER_EXISTS,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateEmail_throwsIllegalValueException() throws Exception {
        JsonSerializableFriendBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_EMAIL_FILE,
                JsonSerializableFriendBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableFriendBook.MESSAGE_EMAIL_EXISTS,
                dataFromFile::toModelType);
    }

}
