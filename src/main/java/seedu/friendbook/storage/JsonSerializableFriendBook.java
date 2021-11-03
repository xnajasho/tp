package seedu.friendbook.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.friendbook.commons.exceptions.IllegalValueException;
import seedu.friendbook.model.FriendBook;
import seedu.friendbook.model.ReadOnlyFriendBook;
import seedu.friendbook.model.person.Person;

/**
 * An Immutable FriendBook that is serializable to JSON format.
 */
@JsonRootName(value = "friendbook")
class JsonSerializableFriendBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    public static final String MESSAGE_PHONE_NUMBER_EXISTS = "Persons list contains duplicate phone numbers(s).";

    public static final String MESSAGE_EMAIL_EXISTS = "Persons list contains duplicate phone email(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFriendBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableFriendBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyFriendBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableFriendBook}.
     */
    public JsonSerializableFriendBook(ReadOnlyFriendBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this friend book into the model's {@code FriendBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FriendBook toModelType() throws IllegalValueException {
        FriendBook friendBook = new FriendBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (friendBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            if (friendBook.hasPhone(person.getPhone())) {
                throw new IllegalValueException(MESSAGE_PHONE_NUMBER_EXISTS);
            }
            if (friendBook.hasEmail(person.getEmail())) {
                throw new IllegalValueException(MESSAGE_EMAIL_EXISTS);
            }
            friendBook.addPerson(person);
        }
        return friendBook;
    }

}
