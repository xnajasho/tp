package seedu.friendbook.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.friendbook.model.FriendBook;
import seedu.friendbook.model.ReadOnlyFriendBook;
import seedu.friendbook.model.person.Address;
import seedu.friendbook.model.person.Avatar;
import seedu.friendbook.model.person.Birthday;
import seedu.friendbook.model.person.Description;
import seedu.friendbook.model.person.Email;
import seedu.friendbook.model.person.Name;
import seedu.friendbook.model.person.Person;
import seedu.friendbook.model.person.Phone;
import seedu.friendbook.model.person.TeleHandle;
import seedu.friendbook.model.reminder.Reminder;
import seedu.friendbook.model.tag.Tag;

/**
 * Contains utility methods for populating {@code FriendBook} with sample data.
 * TeleHandles values are prefixed with @ for generation of sample FriendBook for jar file. Remove the
 * @ character when running the app for the first time via IntelliJ
 */

public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"), new Birthday("1998-03-16"), new TeleHandle("alexi"),
                    new Description("good friend"), new Avatar("1"), new Reminder("on")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"), new Birthday("1994-07-20"),
                    new TeleHandle("bertyy"), new Description("good friend"), new Avatar("3"), new Reminder("on")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"), new Birthday("1999-06-10"), new TeleHandle("cherry"),
                    new Description("good friend"), new Avatar("2"), new Reminder("on")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"), new Birthday("1992-09-24"), new TeleHandle("davelord"),
                    new Description("good friend"), new Avatar("5"), new Reminder("on")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"), new Birthday("1985-02-25"), new TeleHandle("irfan"),
                    new Description("good friend"), new Avatar("20"), new Reminder("on")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"), new Birthday("1994-11-28"), new TeleHandle("royyyyy"),
                    new Description("good friend"), new Avatar("18"), new Reminder("on"))
        };
    }

    public static ReadOnlyFriendBook getSampleFriendBook() {
        FriendBook sampleAb = new FriendBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
