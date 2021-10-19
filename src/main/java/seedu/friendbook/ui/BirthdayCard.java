package seedu.friendbook.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.friendbook.commons.core.LogsCenter;
import seedu.friendbook.model.person.Person;

import java.util.logging.Logger;

public class BirthdayCard extends UiPart<Region> {

    private static final String FXML = "BirthdayListCard.fxml";

    public final Person person;
    private final Logger logger = LogsCenter.getLogger(BirthdayCard.class);

    @FXML
    private HBox cardPane;
    @FXML
    private ImageView friendImage;
    @FXML
    private Label name;
    @FXML
    private Label dob;
    @FXML
    private Label age;
    @FXML
    private Label daysToBirthday;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public BirthdayCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;

        //TODO: to update friendpicture
        name.setText(person.getName().fullName);
        age.setText(String.valueOf(person.getAge()));
        dob.setText(person.getBirthday().getActualDate());
        daysToBirthday.setText(String.valueOf(person.getDaysToRemainingBirthday()));
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
        return person.equals(card.person);
    }
}
