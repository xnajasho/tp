package seedu.friendbook.testutil;

import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_TELEHANDLE_AMY;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_TELEHANDLE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.friendbook.model.FriendBook;
import seedu.friendbook.model.person.Person;


/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withBirthday("1995-04-14").withTeleHandle("wonderalice")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25").withTeleHandle("sonofben")
            .withEmail("johnd@example.com").withPhone("98765432").withBirthday("1992-04-13")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withBirthday("1991-08-12")
            .withTeleHandle("pilkington").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTeleHandle("daniella")
            .withBirthday("1999-02-05").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withBirthday("1993-07-13")
            .withTeleHandle("salmonella").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").withBirthday("1999-09-23")
            .withTeleHandle("loverofshrek").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").withBirthday("1997-05-14")
            .withTeleHandle("thedinosaur").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withBirthday("1995-10-12")
            .withTeleHandle("mainhunyaha").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withBirthday("1999-03-11")
            .withTeleHandle("idaaaa").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withBirthday("1998-04-18")
            .withTags(VALID_TAG_FRIEND).withTeleHandle(VALID_TELEHANDLE_AMY).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withBirthday("1995-02-15")
            .withTeleHandle(VALID_TELEHANDLE_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns a {@code FriendBook} with all the typical persons.
     */
    public static FriendBook getTypicalFriendBook() {
        FriendBook ab = new FriendBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
