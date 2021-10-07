package seedu.address.ui;

import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

public class BirthdayCard extends UiPart<Region> {

    private static final String FXML = "BirthdayListCard.fxml";

    public final Person person;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public BirthdayCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FriendCard)) {
            return false;
        }

        // state check
        FriendCard card = (FriendCard) other;
        return person.equals(card.person);
        //id.getText().equals(card.id.getText())
    }
}
