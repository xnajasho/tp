package seedu.friendbook.ui;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;


public class ProfileDisplay extends UiPart<Region> {

    private static final String FXML = "ProfileDisplay.fxml";

    @FXML
    private Label usernameLabel;

    @FXML
    private Button addFriendButton;

    /**
     * Creates a {@code ProfileDisplay} with the given {@code usernameProperty}.
     */
    public ProfileDisplay(StringProperty usernameProperty) {
        super(FXML);
        if (!usernameProperty.getValue().equals("generic username")) {
            usernameLabel.textProperty().bind(usernameProperty);
        } else {
            usernameLabel.setText("");
        }

    }

    public Button getAddButton() {
        return this.addFriendButton;
    }
}
