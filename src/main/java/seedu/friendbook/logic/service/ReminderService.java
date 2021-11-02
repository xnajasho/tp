package seedu.friendbook.logic.service;

import java.util.logging.Logger;
import java.util.stream.Collectors;

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
 * ReminderService to run in the background.
 */
public class ReminderService extends ScheduledService<String> {

    // reminder service polls every 12 hour
    private static final Duration periodDuration = Duration.hours(12);
    private static final Duration delayDuration = Duration.seconds(10);

    private final Logger logger = LogsCenter.getLogger(ReminderService.class);
    private final ObservableList<Person> personList;

    private boolean isReminderServiceCancelled = false;
    /**
     * Constructs a {@code ReminderService} with the given {@code personList}.
     */
    public ReminderService(ObservableList<Person> personList) {
        this.personList = personList;

        personList.addListener((ListChangeListener<Person>) c -> {
            // restart reminder service for any new addition to list
            if (c.next() && c.wasAdded() && isReminderServiceCancelled) {
                logger.info("reminder service restarting");
                restart();
                this.isReminderServiceCancelled = !isReminderServiceCancelled;
            }
        });
    }


    /**
     * Begins the reminder service with {@code Reminder}.
     * The reminder service also checks and displays an alert.
     */
    public void beginReminderService() {
        this.setOnSucceeded(event -> {
            String value;
            try {
                value = event.getSource().getValue().toString();
                logger.info("reminder service success with value:" + value);
            } catch (NullPointerException nullPointerException) {
                return;
            }
            ReminderService.this.cancel();
            this.isReminderServiceCancelled = !isReminderServiceCancelled;
            Platform.runLater(() -> showReminderAlert(value));
        });

        this.setPeriod(periodDuration);
        this.setDelay(delayDuration);
        this.start();
    }

    /**
     * Shows the Birthday reminder alert with the given {@code friendNames}.
     */
    public static void showReminderAlert(String friendNames) {
        final Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().getStylesheets().add("stylesheets/FriendBookTheme.css");
        alert.setTitle("Birthday Reminder Alert");
        alert.setHeaderText("Friend(s) Birthday coming in less than a week: ");
        alert.setContentText("\n" + friendNames);
        alert.showAndWait();
    }

    @Override
    protected Task<String> createTask() {
        return setReminderTask();
    }


    private Task<String> setReminderTask() {
        return new Task<>() {
            @Override
            protected String call() {
                // get names of person with names of friends whose birthday are coming in a week
                String value = personList.filtered(x -> x.getReminder().getBooleanValue())
                        .filtered(x -> x.getBirthday().calculateRemainingDaysToBirthday() <= 7)
                        .stream().map(x -> x.getName().fullName).collect(Collectors.joining("\n\n"));
                return value.equals("") ? null : value;
            }
        };
    }

    /**
     * Represents a function that can execute Reminder service.
     */
    @FunctionalInterface
    public interface SetRemindExecutor {
        void execute(Person oldPerson, Person updatedPerson) throws CommandException;
    }
}
