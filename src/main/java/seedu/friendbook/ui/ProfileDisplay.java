package seedu.friendbook.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;


public class ProfileDisplay extends UiPart<Region> {

    private static final String FXML = "ProfileDisplay.fxml";

    @FXML
    private Button addFriendButton;

    public ProfileDisplay() {
        super(FXML);
    }

    //TODO: create test case for method
    public Button getAddButton() {
        return this.addFriendButton;
    }
}
