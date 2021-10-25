package seedu.friendbook.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.friendbook.commons.core.LogsCenter;
import seedu.friendbook.logic.commands.exceptions.CommandException;
import seedu.friendbook.logic.service.ReminderService;
import seedu.friendbook.model.person.Person;

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
    @FXML
    private CheckBox reminderCheckBox;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public BirthdayCard(Person person, int displayedIndex, ReminderService.SetRemindExecutor setRemindExecutor) {
        super(FXML);
        this.person = person;
        name.setText(person.getName().fullName);
        age.setText("Currently " + person.getAge() + " Years Old");
        dob.setText(person.getBirthday().getActualDate());
        daysToBirthday.setText(String.valueOf(person.getDaysToRemainingBirthday()));
        reminderCheckBox.setSelected(person.getReminder().getBooleanValue());

        reminderCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            // update person model store in new value
            Person updatedFriend = Person.newInstance(person);
            updatedFriend.getReminder().setReminder(newValue);
            try {
                setRemindExecutor.execute(person, updatedFriend);
            } catch (CommandException e) {
                e.printStackTrace();
            }
        });
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
        return person.equals(card.person);
    }
}
