package seedu.friendbook;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.friendbook.commons.core.Config;
import seedu.friendbook.commons.core.LogsCenter;
import seedu.friendbook.commons.core.Version;
import seedu.friendbook.commons.exceptions.DataConversionException;
import seedu.friendbook.commons.util.ConfigUtil;
import seedu.friendbook.commons.util.StringUtil;
import seedu.friendbook.logic.Logic;
import seedu.friendbook.logic.LogicManager;
import seedu.friendbook.model.FriendBook;
import seedu.friendbook.model.Model;
import seedu.friendbook.model.ModelManager;
import seedu.friendbook.model.ReadOnlyFriendBook;
import seedu.friendbook.model.ReadOnlyUserPrefs;
import seedu.friendbook.model.UserPrefs;
import seedu.friendbook.model.util.SampleDataUtil;
import seedu.friendbook.reminder.Reminder;
import seedu.friendbook.reminder.ReminderManager;
import seedu.friendbook.storage.FriendBookStorage;
import seedu.friendbook.storage.JsonFriendBookStorage;
import seedu.friendbook.storage.JsonUserPrefsStorage;
import seedu.friendbook.storage.Storage;
import seedu.friendbook.storage.StorageManager;
import seedu.friendbook.storage.UserPrefsStorage;
import seedu.friendbook.ui.Ui;
import seedu.friendbook.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 2, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;
    private Reminder birthdayReminder;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing FriendBook ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());
        assert config != null : "config should not be null";

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        FriendBookStorage friendBookStorage = new JsonFriendBookStorage(userPrefs.getFriendBookFilePath());
        storage = new StorageManager(friendBookStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);

        birthdayReminder = new ReminderManager(model.getFilteredPersonListSortedByBirthday());
        birthdayReminder.startBirthdayReminder();
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s friend book and {@code userPrefs}. <br>
     * The data from the sample friend book will be used instead if {@code storage}'s friend book is not found,
     * or an empty friend book will be used instead if errors occur when reading {@code storage}'s friend book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyFriendBook> friendBookOptional;
        ReadOnlyFriendBook initialData;
        try {
            friendBookOptional = storage.readFriendBook();
            if (!friendBookOptional.isPresent()) {
                logger.info("Data file not found. Will be starting with a sample FriendBook");
            }
            initialData = friendBookOptional.orElseGet(SampleDataUtil::getSampleFriendBook);
        } catch (DataConversionException e) {
            logger.warning("Data file not in the correct format. Will be starting with an empty FriendBook");
            initialData = new FriendBook();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty FriendBook");
            initialData = new FriendBook();
        }

        return new ModelManager(initialData, userPrefs);
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            logger.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                    + "Using default config properties");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty FriendBook");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting FriendBook " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping FriendBook ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
