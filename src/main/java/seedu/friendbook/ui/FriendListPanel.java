package seedu.friendbook.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Region;
import seedu.friendbook.commons.core.LogsCenter;
import seedu.friendbook.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class FriendListPanel extends UiPart<Region> {
    private static final String FXML = "FriendListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(FriendListPanel.class);

    @FXML
    private ListView<Person> friendListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public FriendListPanel(ObservableList<Person> personList) {
        super(FXML);
        System.out.println(personList.toString());
        friendListView.setItems(personList);
        friendListView.setCellFactory(listView -> new PersonListViewCell());
        setFriendListViewClickHandler();
    }

    /**
     * Sets double mouse click handler for selected list view for {@code FriendListPanel} selected friend.
     * Creates a {@code FriendWindow} with the given {@code Person} friend.
     */
    private void setFriendListViewClickHandler() {
        friendListView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                if (mouseEvent.getClickCount() == 2) {
                    Person friend = friendListView.getSelectionModel().getSelectedItem();
                    displayFriendWindow(friend);
                }
            }
        });
    }

    /**
     * Creates a {@code FriendWindow} with the given {@code Person} friend.
     */
    private void displayFriendWindow(Person friend) {
        if (friend != null) {
            FriendWindow friendWindow = new FriendWindow(friend);
            if (!friendWindow.isShowing()) {
                friendWindow.show();
            } else {
                friendWindow.focus();
            }
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FriendListCard(person, getIndex() + 1).getRoot());
            }
        }
    }

}
