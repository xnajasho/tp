package seedu.friendbook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.friendbook.commons.core.LogsCenter;
import seedu.friendbook.commons.exceptions.DataConversionException;
import seedu.friendbook.model.ReadOnlyFriendBook;
import seedu.friendbook.model.ReadOnlyUserPrefs;
import seedu.friendbook.model.UserPrefs;

/**
 * Manages storage of FriendBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private FriendBookStorage friendBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code FriendBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(FriendBookStorage friendBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.friendBookStorage = friendBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ FriendBook methods ==============================

    @Override
    public Path getFriendBookFilePath() {
        return friendBookStorage.getFriendBookFilePath();
    }

    @Override
    public Optional<ReadOnlyFriendBook> readFriendBook() throws DataConversionException, IOException {
        return readFriendBook(friendBookStorage.getFriendBookFilePath());
    }

    @Override
    public Optional<ReadOnlyFriendBook> readFriendBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return friendBookStorage.readFriendBook(filePath);
    }

    @Override
    public void saveFriendBook(ReadOnlyFriendBook friendBook) throws IOException {
        saveFriendBook(friendBook, friendBookStorage.getFriendBookFilePath());
    }

    @Override
    public void saveFriendBook(ReadOnlyFriendBook friendBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        friendBookStorage.saveFriendBook(friendBook, filePath);
    }

}
