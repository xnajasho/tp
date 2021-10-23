package seedu.friendbook.ui;

import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.friendbook.commons.core.LogsCenter;
import seedu.friendbook.model.person.Person;

/**
 * Panel containing the list of friends' birthdays.
 */
public class BirthdayListPanel extends UiPart<Region> {

    private static final String FXML = "BirthdayListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(FriendListPanel.class);

    @javafx.fxml.FXML
    private ListView<Person> birthdayListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public BirthdayListPanel(ObservableList<Person> personList) {
        super(FXML);
        birthdayListView.setItems(personList);
        birthdayListView.setCellFactory(listView -> new BirthdayListPanel.BirthdayListViewCell());

        ReminderService rs = new ReminderService();

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setOnCloseRequest(new EventHandler<DialogEvent>() {
            @Override
            public void handle(DialogEvent event) {
                System.out.println("close");
                //rs.start();
                System.out.println("resuming service");
            }
        });



        rs.setOnRunning(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                // check
                System.out.println("running bg service");
            }
        });

        rs.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                // get the
                //System.out.println(event.getSource().getValue());
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        a.show();
                    }
                });
            }
        });

        //rs.setPeriod(Duration.seconds(10));
        //rs.start();;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class BirthdayListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new BirthdayCard(person, getIndex() + 1).getRoot());
            }
        }
    }

    public static class ReminderService<String> extends ScheduledService<String> {
        // Pass in a list of person
        // check through reminder
        // return names of people birthday I have set reminder for

        @Override
        protected Task<String> createTask() {
            return reminderTask();
        }


        private Task<String> reminderTask() {
            return new Task<String>() {
                @Override
                protected String call() throws Exception {
                    return (String) "nice";
                }
            };
        }
    }
}

