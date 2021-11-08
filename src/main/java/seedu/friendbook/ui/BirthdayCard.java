package seedu.friendbook.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import seedu.friendbook.commons.core.LogsCenter;
import seedu.friendbook.logic.commands.exceptions.CommandException;
import seedu.friendbook.model.person.Person;
import seedu.friendbook.reminder.ReminderManager;

/**
 * An UI component that displays information of a {@code Person} Birthday.
 */
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
    @FXML
    private VBox birthdayCircleContainer;
    @FXML
    private Circle birthdayCircle;
    @FXML
    private Label birthdayDaysLeftLabel;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public BirthdayCard(Person person, int displayedIndex,
                        ReminderManager.SetRemindExecutor setRemindExecutor) {
        super(FXML);
        this.person = person;
        name.setText(person.getName().fullName);
        age.setText("Currently " + person.getAge() + " Years Old");
        dob.setText(person.getBirthday().getActualDate());
        //Birthday
        daysToBirthday.setText(String.valueOf(person.getDaysToRemainingBirthday()));
        setBirthdayCircle(person.getDaysToRemainingBirthday());
        //Reminder
        reminderCheckBox.setSelected(person.getReminder().getBooleanValue());
        // update person model store in new value for every change in reminder checkbox
        reminderCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            logger.info("Updating reminder to " + newValue + " for " + person.getName().fullName);
            Person updatedFriend = Person.newInstance(person);
            updatedFriend.getReminder().setReminder(newValue);
            try {
                setRemindExecutor.execute(person, updatedFriend);
            } catch (CommandException e) {
                logger.info("Failed to update reminder for: " + person.getName().fullName);
            }
        });
    }

    /**
     * Sets display for Birthday Circle based on {@code daysLeftToBirthday}.
     */
    public void setBirthdayCircle(int daysLeftToBirthday) {
        if (daysLeftToBirthday == 0) {
            // friend birthday is today
            birthdayCircle.getStyleClass().add("circle-today");
            daysToBirthday.setText("Today");
            birthdayCircleContainer.getChildren().remove(birthdayDaysLeftLabel);
        } else if (daysLeftToBirthday <= 7) {
            // friend birthday is in less than a week
            birthdayCircle.getStyleClass().add("circle-week-away");
        } else {
            // default view
            birthdayCircle.getStyleClass().add("circle-default");
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
        return person.equals(card.person);
    }
}
