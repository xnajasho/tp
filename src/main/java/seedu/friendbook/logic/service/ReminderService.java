package seedu.friendbook.logic.service;

import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.util.Duration;
import seedu.friendbook.commons.core.LogsCenter;
import seedu.friendbook.model.person.Person;

/**
 * ReminderService to run in the background.
 */
public class ReminderService extends ScheduledService<String> {

    private static final Duration periodDuration = Duration.hours(1);
    private static final Duration delayDuration = Duration.minutes(5);

    private final Logger logger = LogsCenter.getLogger(ReminderService.class);


    // Pass in a list of person
    // check through reminder
    // return names of people birthday I have set reminder for
    private ObservableList<Person> personList;

    /**
     * Constructs a {@code ReminderService} with the given {@code personList}.
     */
    public ReminderService(ObservableList<Person> personList) {
        this.personList = personList;

        personList.addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> c) {
                if (c.next() && c.wasAdded()) {
                    ReminderService.this.restart();
                }
            }
        });
    }


    /**
     * Begins the reminder service with {@code Reminder}.
     * The reminder service also checks and displays an alert.
     */
    public void beginReminderService() {
        this.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                String value = event.getSource().getValue().toString();
                if (value == null) {
                    return;
                }

                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Friends Birthday coming in a day's time: " + value);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        a.show();
                        ReminderService.this.cancel();
                    }
                });
            }
        });

        this.setPeriod(periodDuration);
        this.setDelay(delayDuration);
        this.start();
    }

    @Override
    protected Task<String> createTask() {
        return setReminderTask();
    }


    private Task<String> setReminderTask() {
        return new Task<>() {
            @Override
            protected String call() {
                String value = personList.filtered(x -> x.getReminder().getBooleanValue() == true)
                        .filtered(x -> x.getBirthday().calculateRemainingDaysToBirthday() == 1)
                        .stream().map(x -> x.getName().fullName).collect(Collectors.joining("\n"));
                return !value.equals("") ? value : null;
            }
        };
    }

}
