package seedu.friendbook.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.TextAlignment;
import seedu.friendbook.MainApp;
import seedu.friendbook.commons.core.LogsCenter;
import seedu.friendbook.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class FriendCard extends UiPart<Region> {

    private static final String FXML = "FriendListCard.fxml";
    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */
    public final Person person;
    private final Logger logger = LogsCenter.getLogger(FriendCard.class);

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
    private ImageView picture;
    @FXML
    private FlowPane tags;

    //TODO: improve implementation, currently safely assumes fail.png is in the file
    private Image defaultPic = new Image(MainApp.class.getResourceAsStream("/images/fail.png"));

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public FriendCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        detectPicturePresent();
        //address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> {
                    Label label = new Label(tag.tagName + "\t");
                    label.getStyleClass().add("cell_tag_label");
                    label.setTextAlignment(TextAlignment.CENTER);
                    tags.getChildren().add(label);
                });
    }

    private void detectPicturePresent() {
        try {
            picture.setImage(person.getPicture().getImage());
        } catch (NullPointerException e) {
            // pop alert
            //Alert alert = new Alert(Alert.AlertType.ERROR);
            //alert.setContentText("Picture not found. Setting to default pic");
            //alert.setHeight(600);
            //alert.show();
            // TODO: IF PICTURE NOT FOUND -> PLACE A FAIL IMAGE
            picture.setImage(defaultPic);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FriendCard)) {
            return false;
        }

        // state check
        FriendCard card = (FriendCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
