package seedu.friendbook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.friendbook.commons.exceptions.DataConversionException;
import seedu.friendbook.model.ReadOnlyFriendBook;

/**
 * Represents a storage for {@link seedu.friendbook.model.FriendBook}.
 */
public interface FriendBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getFriendBookFilePath();

    /**
     * Returns FriendBook data as a {@link ReadOnlyFriendBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyFriendBook> readFriendBook() throws DataConversionException, IOException;

    /**
     * @see #getFriendBookFilePath()
     */
    Optional<ReadOnlyFriendBook> readFriendBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyFriendBook} to the storage.
     * @param friendBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFriendBook(ReadOnlyFriendBook friendBook) throws IOException;

    /**
     * @see #saveFriendBook(ReadOnlyFriendBook)
     */
    void saveFriendBook(ReadOnlyFriendBook friendBook, Path filePath) throws IOException;

}
