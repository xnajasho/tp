package seedu.friendbook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.friendbook.commons.exceptions.DataConversionException;
import seedu.friendbook.model.ReadOnlyFriendBook;
import seedu.friendbook.model.ReadOnlyUserPrefs;
import seedu.friendbook.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends FriendBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getFriendBookFilePath();

    @Override
    Optional<ReadOnlyFriendBook> readFriendBook() throws DataConversionException, IOException;

    @Override
    void saveFriendBook(ReadOnlyFriendBook friendBook) throws IOException;

}
