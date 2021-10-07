package seedu.friendbook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.friendbook.testutil.TypicalPersons.getTypicalFriendBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.friendbook.commons.core.GuiSettings;
import seedu.friendbook.model.FriendBook;
import seedu.friendbook.model.ReadOnlyFriendBook;
import seedu.friendbook.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonFriendBookStorage friendBookStorage = new JsonFriendBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(friendBookStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void friendBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonFriendBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonFriendBookStorageTest} class.
         */
        FriendBook original = getTypicalFriendBook();
        storageManager.saveFriendBook(original);
        ReadOnlyFriendBook retrieved = storageManager.readFriendBook().get();
        assertEquals(original, new FriendBook(retrieved));
    }

    @Test
    public void getFriendBookFilePath() {
        assertNotNull(storageManager.getFriendBookFilePath());
    }

}
