package seedu.friendbook.reminder;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import seedu.friendbook.commons.core.LogsCenter;
import seedu.friendbook.logic.commands.exceptions.CommandException;
import seedu.friendbook.model.person.Person;

/**
 * The main BirthdayReminderManager to run birthday reminder in the background.
 */
public class ReminderManager extends ScheduledService<List<Person>> implements Reminder {


    // reminder service polls every 12 hour starting with a 10second Delay
    private static final Duration periodDuration = Duration.hours(12);
    private static final Duration delayDuration = Duration.seconds(10);

    private final ObservableList<Person> personList;
    private final Logger logger = LogsCenter.getLogger(ReminderManager.class);
    /**
     * Constructs a {@code ReminderService} with the given {@code personList}.
     */
    public ReminderManager(ObservableList<Person> personList) {
        this.personList = personList;

        // restart reminder service for any new addition to personList
        personList.addListener((ListChangeListener<Person>) c -> {
            if (c.next() && c.wasAdded()) {
                logger.info("reminder service restarting");
                if (isRunning()) {
                    cancel();
                    Platform.runLater(this::restart);
                }
            }
        });
    }


    /**
     * Starts the Birthday Reminder.
     * The birthday reminder also checks and displays an alert on success.
     */
    @Override
    public void startBirthdayReminder() {
        this.setPeriod(periodDuration);
        this.setDelay(delayDuration);
        this.handleOnSucceedHandler();

        Platform.runLater(this::start);

        logger.info("Starting Reminder Service...");
    }

    private void handleOnSucceedHandler() {

        this.setOnSucceeded(event -> {
            if (event.getSource().getValue() == null) {
                return;
            }
            @SuppressWarnings("unchecked")
            List<Person> reminderList = (List<Person>) event.getSource().getValue();
            if (reminderList.size() > 0) {
                Platform.runLater(() -> showReminderAlert(reminderList));
                logger.info("reminder service success with list:" + reminderList);
            }
        });
    }

    /**
     * Shows the Birthday reminder alert with the given {@code reminderList}.
     */
    public static void showReminderAlert(List<Person> reminderList) {
        final Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().getStylesheets().add("stylesheets/FriendBookTheme.css");
        alert.setTitle("Birthday Reminder Alert");
        alert.setHeaderText("Friend(s) Birthday coming in less than a week: ");
        StringBuilder text = new StringBuilder();
        text.append("\n");
        for (Person friend: reminderList) {
            if (friend.getDaysToRemainingBirthday() == 0) {
                text.append("\n" + friend.getName().fullName + " is " + friend.getAge() + " years old today.");
            } else {
                text.append("\n"
                        + friend.getName().fullName + " is going to be " + friend.getAge() + " years old soon.");
            }
        }
        alert.setContentText(text.toString());
        alert.showAndWait();
    }

    private Task<List<Person>> setReminderTask() {
        return new Task<>() {
            @Override
            protected List<Person> call() {
                // get names of person with names of friends whose birthday are coming in a week
                return new ArrayList<>(personList
                        .filtered(x -> x.getReminder().getBooleanValue())
                        .filtered(x -> x.getBirthday().calculateRemainingDaysToBirthday() <= 7));
            }
        };
    }


    @Override
    protected Task<List<Person>> createTask() {
        return setReminderTask();
    }

    /**
     * Represents a function that can execute Reminder service.
     */
    @FunctionalInterface
    public interface SetRemindExecutor {
        void execute(Person oldPerson, Person updatedPerson) throws CommandException;
    }
}
