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

    public void handleAddFriend() {
        //TODO:
        // flow:
        // when clicking on add Friend button (method handeler is handleAddFriend() )
        //  -> some logic to link to get add friend placeholder => copy that text to
        // CommandBox.java and set the text to Commandtextfield
    }
}
