package seedu.friendbook.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import seedu.friendbook.commons.core.LogsCenter;
import seedu.friendbook.model.person.Person;



public class FriendWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(FriendWindow.class);
    private static final String FXML = "FriendWindow.fxml";
    @FXML
    private HBox viewContainer;
    @FXML
    private Tooltip upcomingAgeToolTip;
    @FXML
    private VBox fieldContainer;
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
    private Hyperlink teleHandle;
    @FXML
    private Label description;
    @FXML
    private Label daysToBirthday;
    @FXML
    private Circle birthdayCircle;
    @FXML
    private Label daysToBirthdayLabel;

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
     * Creates a new FriendWindow.
     */
    public FriendWindow(Person person) {
        this(new Stage());
        avatar.setImage(person.getAvatar().getImage());
        name.setText(String.format("%s", person.getName().fullName));
        birthday.setText(String.format("%s", person.getBirthday().getActualDate()));
        phone.setText(String.format("%s", person.getPhone().value));
        address.setText(String.format("%s", person.getAddress().value));
        email.setText(String.format("%s", person.getEmail().value));
        daysToBirthday.setText(String.valueOf(person.getDaysToRemainingBirthday()));
        upcomingAgeToolTip.setText(String.format("Going to be %s Years Old", person.getAge() + 1));
        upcomingAgeToolTip.setShowDelay(Duration.ONE);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> {
                    Label label = new Label(tag.tagName + "\t");
                    label.getStyleClass().add("cell_tag_label");
                    label.setTextAlignment(TextAlignment.CENTER);
                    tags.getChildren().add(label);
                });

        if (!person.getDescription().isEmpty()) {
            description.setText(String.format("%s", person.getDescription().value));
        } else {
            fieldContainer.getChildren().remove(description);
        }
        if (person.getTeleHandle().isEmpty()) {
            fieldContainer.getChildren().remove(teleHandle);
        } else {
            //TODO: CLEAN UP code BEFORE COMMIT
            teleHandle.setText(String.format("@%s", person.getTeleHandle().value));
            teleHandle.setOnAction(event -> {
                try {
                    Desktop.getDesktop().browse(new URL(String.format("https://t.me/%s",
                            person.getTeleHandle().value))
                            .toURI());
                } catch (URISyntaxException | IOException e) {
                    e.printStackTrace();
                }
            });
        }

        setBirthdayCircle(person.getDaysToRemainingBirthday());

    }

    public void setBirthdayCircle(int daysLeftToBirthday) {
        //TODO: remove 365
        if (daysLeftToBirthday == 0 || daysLeftToBirthday == 365) {
            birthdayCircle.getStyleClass().add("circle-today");
            daysToBirthday.setText("Today");
            daysToBirthdayLabel.setVisible(false);
            upcomingAgeToolTip.setText("Today is your friend birthday");
            //birthdayCircleContainer.getChildren().remove(daysToBirthdayLabel);
        } else if (daysLeftToBirthday <= 7) {
            birthdayCircle.getStyleClass().add("circle-week-away");
        } else {
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
