package seedu.friendbook.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.friendbook.logic.service.ReminderService;
import seedu.friendbook.model.person.Person;

/**
 * Panel containing the list of friends' birthdays.
 */
public class BirthdayListPanel extends UiPart<Region> {

    private static final String FXML = "BirthdayListPanel.fxml";

    private final ReminderService.SetRemindExecutor setRemindExecutor;

    @FXML
    private ListView<Person> birthdayListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public BirthdayListPanel(ObservableList<Person> personList, ReminderService.SetRemindExecutor setRemindExecutor) {
        super(FXML);
        this.setRemindExecutor = setRemindExecutor;
        birthdayListView.setItems(personList);
        birthdayListView.setCellFactory(listView -> new BirthdayListPanel.BirthdayListViewCell());
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
                setGraphic(new BirthdayCard(person, getIndex() + 1, setRemindExecutor).getRoot());
            }
        }
    }
}

