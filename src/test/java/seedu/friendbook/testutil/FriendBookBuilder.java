package seedu.friendbook.testutil;

import seedu.friendbook.model.FriendBook;
import seedu.friendbook.model.person.Person;

/**
 * A utility class to help with building Friendbook objects.
 * Example usage: <br>
 *     {@code FriendBook ab = new FriendBookBuilder().withPerson("John", "Doe").build();}
 */
public class FriendBookBuilder {

    private FriendBook friendBook;

    public FriendBookBuilder() {
        friendBook = new FriendBook();
    }

    public FriendBookBuilder(FriendBook friendBook) {
        this.friendBook = friendBook;
    }

    /**
     * Adds a new {@code Person} to the {@code FriendBook} that we are building.
     */
    public FriendBookBuilder withPerson(Person person) {
        friendBook.addPerson(person);
        return this;
    }

    public FriendBook build() {
        return friendBook;
    }
}
