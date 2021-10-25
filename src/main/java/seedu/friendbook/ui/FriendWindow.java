package seedu.friendbook.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.friendbook.commons.core.LogsCenter;
import seedu.friendbook.model.person.Person;



public class FriendWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(FriendWindow.class);
    private static final String FXML = "FriendWindow.fxml";

    @FXML
    private HBox viewContainer;
    @FXML
    private VBox teleImageViewContainer;

    @FXML
    private ImageView avatar;
    @FXML
    private ImageView teleImageView;

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
    private Label teleHandle;
    @FXML
    private Label description;
    @FXML
    private Label daysToBirthday;


    /**
     * Creates a new FriendWindow.
     *
     * @param root Stage to use as the root of the FriendWindow.
     */
    public FriendWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new FriendWindow.
     */
    public FriendWindow(Person person) {
        this(new Stage());
        avatar.setImage(person.getAvatar().getImage());
        name.setText(String.format("Name: %s", person.getName().fullName));
        birthday.setText(String.format("DOB: %s", person.getBirthday().getActualDate()));
        phone.setText(String.format("Phone: %s", person.getPhone().value));
        address.setText(String.format("Address: %s", person.getAddress().value));
        email.setText(String.format("Email: %s", person.getEmail().value));
        daysToBirthday.setText("" + person.getDaysToRemainingBirthday());

        if (!person.getDescription().isEmpty()) {
            description.setText(String.format("Description: %s", person.getDescription().value));
        }
        if (person.getTeleHandle().isEmpty()) {
            viewContainer.getChildren().remove(teleImageViewContainer);
        } else {
            teleHandle.setText(String.format("Tele name: %s", person.getTeleHandle().value));
            setTeleImageView(person.getTeleHandle().value);
        }

    }

    private void setTeleImageView(String teleHandle) {
        teleImageView.setOnMouseClicked(event -> {
            try {
                Desktop.getDesktop().browse(new URL(String.format("https://t.me/%s", teleHandle))
                        .toURI());
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
        logger.fine("Showing selected friend window.");
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
}
