package seedu.friendbook.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.friendbook.commons.core.GuiSettings;
import seedu.friendbook.commons.core.LogsCenter;
import seedu.friendbook.logic.Logic;
import seedu.friendbook.logic.commands.AddCommand;
import seedu.friendbook.logic.commands.CommandResult;
import seedu.friendbook.logic.commands.exceptions.CommandException;
import seedu.friendbook.logic.parser.exceptions.ParseException;
import seedu.friendbook.model.person.Person;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem helpMenuItem;

    private HelpWindow helpWindow;

    @FXML
    private StackPane profilePlaceHolder;
    private ProfileDisplay profileDisplay;

    @FXML
    private StackPane friendListPanelPlaceholder;
    private FriendListPanel friendListPanel;

    @FXML
    private StackPane birthdayListPanelPlaceholder;
    private BirthdayListPanel birthdayListPanel;

    @FXML
    private StackPane commandBoxPlaceholder;
    private CommandBox commandBox;

    @FXML
    private StackPane resultDisplayPlaceholder;
    private ResultDisplay resultDisplay;

    @FXML
    private StackPane statusbarPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();

    }


    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        //Profile display
        profileDisplay = new ProfileDisplay(logic.getUsernameProperty());
        profileDisplay.getAddButton().setOnAction(event -> handleAddButton());
        profilePlaceHolder.getChildren().add(profileDisplay.getRoot());

        // Friend list view
        friendListPanel = new FriendListPanel(logic.getFilteredPersonList());
        friendListPanelPlaceholder.getChildren().add(friendListPanel.getRoot());

        // Birthday list view
        birthdayListPanel = new BirthdayListPanel(logic.getFilteredPersonListSortedByBirthday(),
                this::executeReminderCheckBox);
        birthdayListPanelPlaceholder.getChildren().add(birthdayListPanel.getRoot());

        // Result view
        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        // Command box view
        commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        // Status bar view
        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getFriendBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the FriendWindow.
     */
    @FXML
    public void handleViewPerson(CommandResult command) {
        FriendWindow friendWindow = new FriendWindow(command.getPersonToView());
        if (!friendWindow.isShowing()) {
            friendWindow.show();
        } else {
            friendWindow.focus();
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }


    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.friendbook.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult;

            // clear prompt
            if (commandText.equals("clear")) {
                Alert clearConfirmationPrompt = new Alert(Alert.AlertType.CONFIRMATION,
                        "are sure you want to lose your friends?", ButtonType.NO, ButtonType.YES);
                clearConfirmationPrompt.setTitle("confirm clearing FriendBook?");
                clearConfirmationPrompt.showAndWait();
                if (clearConfirmationPrompt.getResult() == ButtonType.NO) {
                    // ignore
                    return null;
                }
            }

            commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }
            if (commandResult.isViewPerson()) {
                handleViewPerson(commandResult);
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    /**
     * Update friend's reminder on checkbox update.
     *
     * @see seedu.friendbook.logic.Logic#executeUpdateReminder(Person, Person)
     *
     */
    private void executeReminderCheckBox(Person person, Person newPerson) throws CommandException {
        try {
            this.logic.executeUpdateReminder(person, newPerson);
        } catch (CommandException e) {
            logger.info("fail to update person reminder through checkbox: " + newPerson);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    /**
     * Executes the add button and updates command text field.
     *
     */
    public void handleAddButton() {
        commandBox.setAddPlaceholderText(AddCommand.MESSAGE_PLACEHOLDER);
    }

}
