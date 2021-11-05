package seedu.friendbook.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.TextAlignment;
import seedu.friendbook.commons.core.LogsCenter;
import seedu.friendbook.model.person.Avatar;
import seedu.friendbook.model.person.Person;

/**
 * An UI component that displays brief information of a {@code Person} friend.
 */
public class FriendListCard extends UiPart<Region> {

    private static final String FXML = "FriendListCard.fxml";
    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */
    public final Person person;
    private final Logger logger = LogsCenter.getLogger(FriendListCard.class);

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private ImageView avatar;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public FriendListCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ".");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        email.setText(person.getEmail().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> {
                    Label label = new Label(tag.tagName + "\t");
                    label.getStyleClass().add("cell_tag_label");
                    label.setTextAlignment(TextAlignment.CENTER);
                    tags.getChildren().add(label);
                });
        setFriendAvatar();
    }

    private void setFriendAvatar() {
        try {
            avatar.setImage(person.getAvatar().getImage());
        } catch (NullPointerException e) {
            // set default avatar if friend has no avatar
            logger.info("Failed to set avatar for " + person.getName().fullName
                    + " setting default avatar instead.");
            avatar.setImage(Avatar.DEFAULT_AVATAR_IMAGE);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FriendListCard)) {
            return false;
        }

        // state check
        FriendListCard card = (FriendListCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
