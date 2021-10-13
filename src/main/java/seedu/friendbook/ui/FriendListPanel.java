package seedu.friendbook.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.friendbook.commons.core.LogsCenter;
import seedu.friendbook.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class FriendListPanel extends UiPart<Region> {
    private static final String FXML = "FriendListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(FriendListPanel.class);

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public FriendListPanel(ObservableList<Person> personList) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());

        //TODO: improve implementation
        //ON DOUBLE CLICK
        personListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        Person item = personListView.getSelectionModel()
                                .getSelectedItem();

                        if (item != null) {
                            VBox pane = new VBox();

                            // content bias is null:
                            double prefWidth = pane.prefWidth(-1);
                            double prefHeight = pane.prefHeight(-1);


                            pane.getChildren().addAll(new Label(item.getName().fullName),
                                    new Label(item.getEmail().value));

                            Scene scene = new Scene(pane);

                            Stage newWindow = new Stage();
                            newWindow.setTitle(item.getName().fullName);
                            // Set position of second window, related to primary window.
                            newWindow.setScene(scene);

                            newWindow.requestFocus();
                            newWindow.show();
                            newWindow.toFront();

                            // no fill width:
                            double paneWidth = Math.max(newWindow.getWidth(), prefWidth);
                            // vgrow, so ignore preferred height and size to height of the vbox:
                            double paneHeight = newWindow.getHeight();
                            pane.resizeRelocate(0, 0, paneWidth, paneHeight);
                        }

                    }
                }
            }
        });
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
                setGraphic(new FriendCard(person, getIndex() + 1).getRoot());
            }
        }
    }

}
