package seedu.friendbook.logic;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import seedu.friendbook.commons.core.GuiSettings;
import seedu.friendbook.commons.core.LogsCenter;
import seedu.friendbook.logic.commands.Command;
import seedu.friendbook.logic.commands.CommandResult;
import seedu.friendbook.logic.commands.exceptions.CommandException;
import seedu.friendbook.logic.parser.FriendBookParser;
import seedu.friendbook.logic.parser.exceptions.ParseException;
import seedu.friendbook.model.Model;
import seedu.friendbook.model.ReadOnlyFriendBook;
import seedu.friendbook.model.person.Name;
import seedu.friendbook.model.person.Person;
import seedu.friendbook.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final FriendBookParser friendBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        friendBookParser = new FriendBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = friendBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveFriendBook(model.getFriendBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public void executeUpdateReminder(Person person, Person newPerson) throws CommandException {
        logger.info("Updating Person reminder through checkbox");
        model.setPerson(person, newPerson);
        try {
            storage.saveFriendBook(model.getFriendBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }
    }

    @Override
    public ReadOnlyFriendBook getFriendBook() {
        return model.getFriendBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<Person> getFilteredPersonListSortedByBirthday() {
        return model.getFilteredPersonListSortedByBirthday();
    }

    @Override
    public Path getFriendBookFilePath() {
        return model.getFriendBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public void setUsername(Name username) {
        requireNonNull(username);
        model.setUsername(username);
    }
    @Override
    public Name getUsername() {
        return model.getUsername();
    }

    @Override
    public StringProperty getUsernameProperty() {
        return this.model.getUsernameProperty();
    }
}
