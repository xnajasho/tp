package seedu.friendbook.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import seedu.friendbook.commons.core.LogsCenter;
import seedu.friendbook.model.person.Person;


/**
 * An UI Window for displaying selected {@code Person} Friend information.
 */
public class FriendWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(FriendWindow.class);
    private static final String FXML = "FriendWindow.fxml";

    private Person friend;

    @FXML
    private ImageView avatar;
    @FXML
    private ImageView teleImageView;

    @FXML
    private VBox topContainer;
    @FXML
    private VBox fieldContainer;
    @FXML
    private VBox addressContainer;
    @FXML
    private VBox descriptionContainer;

    @FXML
    private Hyperlink teleHandle;

    @FXML
    private Tooltip upcomingAgeToolTip;

    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label addressLabel;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label description;
    @FXML
    private Label daysToBirthdayLabel;
    @FXML
    private Label daysToBirthday;
    @FXML
    private Label birthday;
    @FXML
    private Circle birthdayCircle;
    @FXML
    private FlowPane tags;
    /**
     * Creates a new FriendWindow.
     *
     * @param root Stage to use as the root of the FriendWindow.
     */
    public FriendWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a  {@code FriendWindow} with the given {@code Person} friend.
     */
    public FriendWindow(Person friend) {
        this(new Stage());
        this.friend = friend;

        avatar.setImage(friend.getAvatar().getImage());
        name.setText(friend.getName().fullName);
        birthday.setText(friend.getBirthday().getActualDate());
        phone.setText(friend.getPhone().value);
        email.setText(friend.getEmail().value);
        daysToBirthday.setText(String.valueOf(friend.getDaysToRemainingBirthday()));

        upcomingAgeToolTip.setText(String.format("Going to be %s Years Old", friend.getAge() + 1));
        upcomingAgeToolTip.setShowDelay(Duration.ONE);

        setBirthdayCircle(friend.getDaysToRemainingBirthday());

        friend.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> {
                    Label label = new Label(tag.tagName + "\t");
                    label.getStyleClass().add("cell_tag_label");
                    label.setTextAlignment(TextAlignment.CENTER);
                    tags.getChildren().add(label);
                });

        setOptionalAddress();
        setOptionalDescription();
        setOptionalTeleHandle();
    }

    /**
     * Display address only if there is address about {@code Person} friend.
     */
    public void setOptionalAddress() {
        if (!friend.getAddress().isEmpty()) {
            address.setText(friend.getAddress().value);
        } else {
            addressContainer.getChildren().remove(addressLabel);
            addressContainer.getChildren().remove(address);
            VBox.setMargin(description, new Insets(-10, 0, 0, 0));
        }

    }

    /**
     * Display description only if there is description about {@code Person} friend.
     */
    public void setOptionalDescription() {
        if (!friend.getDescription().isEmpty()) {
            description.setText(friend.getDescription().value);
        } else {
            descriptionContainer.getChildren().remove(descriptionLabel);
            descriptionContainer.getChildren().remove(description);
        }
    }

    /**
     * Display telegram info only if {@code Person} friend tele handle is given.
     */
    public void setOptionalTeleHandle() {
        if (friend.getTeleHandle().isEmpty()) {
            topContainer.getChildren().remove(teleHandle);
        } else {
            teleHandle.setText(friend.getTeleHandle().value);
            teleHandle.setOnAction(event -> {
                try {
                    // open browser
                    Desktop.getDesktop().browse(new URL(String.format("https://t.me/%s",
                            friend.getTeleHandle().value)).toURI());
                } catch (URISyntaxException | IOException e) {
                    logger.info("friend " + friend.getName().fullName + " tele handle failed to open browser.");
                }
            });
        }
    }

    /**
     * Sets display for Birthday Circle based on {@code daysLeftToBirthday}.
     */
    public void setBirthdayCircle(int daysLeftToBirthday) {
        if (daysLeftToBirthday == 0) {
            // friend birthday is today
            daysToBirthdayLabel.setVisible(false);
            daysToBirthday.setText("Today");
            birthdayCircle.getStyleClass().add("circle-today");
            upcomingAgeToolTip.setText("Today is your friend birthday");
        } else if (daysLeftToBirthday <= 7) {
            // friend birthday is in less than a week
            birthdayCircle.getStyleClass().add("circle-week-away");
        } else {
            // default view
            birthdayCircle.getStyleClass().add("circle-default");
        }
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
