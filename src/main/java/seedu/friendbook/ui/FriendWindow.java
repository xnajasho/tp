package seedu.friendbook.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.friendbook.commons.core.LogsCenter;
import seedu.friendbook.model.person.Person;



public class FriendWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(FriendWindow.class);
    private static final String FXML = "FriendWindow.fxml";

    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label birthday;
    @FXML
    private Hyperlink teleLink;
    @FXML
    private Label teleHandle;
    @FXML
    private Label description;


    /**
     * Creates a new FriendWindow.
     *
     * @param root Stage to use as the root of the FriendWindow.
     */
    public FriendWindow(Stage root) {
        super(FXML, root);
        //helpMessage.setText(HELP_MESSAGE);
    }

    /**
     * Creates a new FriendWindow.
     */
    public FriendWindow(Person person) {
        this(new Stage());

        //TODO: add the remaining, make sure to set id in the UI and link to code
        //ignore pictures first
        //how layout looks can ignore also.
        name.setText(String.format("Name: %s", person.getName().fullName));
        birthday.setText(String.format("DOB: %s", person.getBirthday().getActualDate()));
        phone.setText(String.format("Phone: %s", person.getPhone().value));
        address.setText(String.format("Address: %s", person.getAddress().value));
        email.setText(String.format("Email: %s", person.getEmail().value));
        teleLink.setText(String.format("https://t.me/%s", person.getTeleHandle().value));
        teleHandle.setText(String.format("Tele name: %s", person.getTeleHandle().value));
        description.setText(String.format("Description: %s", person.getDescription().value));

        teleLink.setOnAction(event -> {
            try {
                Desktop.getDesktop().browse(new URL(teleLink.getText()).toURI());
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Shows the friend window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing selected friend page.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the friend window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        //url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }

}
