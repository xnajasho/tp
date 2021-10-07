package seedu.friendbook.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.friendbook.commons.core.LogsCenter;
import seedu.friendbook.commons.exceptions.DataConversionException;
import seedu.friendbook.commons.exceptions.IllegalValueException;
import seedu.friendbook.commons.util.FileUtil;
import seedu.friendbook.commons.util.JsonUtil;
import seedu.friendbook.model.ReadOnlyFriendBook;

/**
 * A class to access FriendBook data stored as a json file on the hard disk.
 */
public class JsonFriendBookStorage implements FriendBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonFriendBookStorage.class);

    private Path filePath;

    public JsonFriendBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFriendBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyFriendBook> readFriendBook() throws DataConversionException {
        return readFriendBook(filePath);
    }

    /**
     * Similar to {@link #readFriendBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyFriendBook> readFriendBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableFriendBook> jsonFriendBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableFriendBook.class);
        if (!jsonFriendBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonFriendBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveFriendBook(ReadOnlyFriendBook friendBook) throws IOException {
        saveFriendBook(friendBook, filePath);
    }

    /**
     * Similar to {@link #saveFriendBook(ReadOnlyFriendBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveFriendBook(ReadOnlyFriendBook friendBook, Path filePath) throws IOException {
        requireNonNull(friendBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableFriendBook(friendBook), filePath);
    }

}
