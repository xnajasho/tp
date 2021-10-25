package seedu.friendbook.testutil;

import java.util.HashSet;
import java.util.Set;

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
import seedu.friendbook.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_BIRTHDAY = "1994-09-12";
    public static final String DEFAULT_TELEHANDLE = "";
    public static final String DEFAULT_DESCRIPTION = "";
    public static final String DEFAULT_AVATAR = "0";
    public static final String DEFAULT_REMINDER = "off";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private Birthday birthday;
    private TeleHandle teleHandle;
    private Description description;
    private Avatar avatar;
    private Reminder reminder;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        birthday = new Birthday(DEFAULT_BIRTHDAY);
        teleHandle = new TeleHandle(DEFAULT_TELEHANDLE);
        description = new Description(DEFAULT_DESCRIPTION);
        avatar = new Avatar(DEFAULT_AVATAR);
        reminder = new Reminder(DEFAULT_REMINDER);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        birthday = personToCopy.getBirthday();
        avatar = personToCopy.getAvatar();
        teleHandle = personToCopy.getTeleHandle();
        description = personToCopy.getDescription();
        reminder = personToCopy.getReminder();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Birthday} of the {@code Person} that we are building.
     */
    public PersonBuilder withBirthday(String birthday) {
        this.birthday = new Birthday(birthday);
        return this;
    }

    /**
     * Sets the {@code TeleHandle} of the {@code Person} that we are building.
     */
    public PersonBuilder withTeleHandle(String teleHandle) {
        this.teleHandle = new TeleHandle(teleHandle);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Person} that we are building.
     */
    public PersonBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Picture} of the {@code Person} that we are building.
     */
    public PersonBuilder withAvatar(String avatar) {
        this.avatar = new Avatar(avatar);
        return this;
    }

    /**
     * Sets the {@code Reminder} of the {@code Person} that we are building.
     */
    public PersonBuilder withReminder(String reminder) {
        this.reminder = new Reminder(reminder);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, tags, birthday, teleHandle, description, avatar, reminder);
    }

}
