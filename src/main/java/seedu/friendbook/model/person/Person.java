package seedu.friendbook.model.person;

import static seedu.friendbook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.friendbook.model.reminder.Reminder;
import seedu.friendbook.model.tag.Tag;

/**
 * Represents a Person in the friend book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Description description;
    private final TeleHandle teleHandle;
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Birthday birthday;
    private final Avatar avatar;
    private final Reminder reminder;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Birthday bday,
                  TeleHandle teleHandle, Description description, Avatar avatar, Reminder reminder) {
        requireAllNonNull(name, phone, email, address, tags, bday, teleHandle, description, avatar, reminder);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.birthday = bday;
        this.teleHandle = teleHandle;
        this.description = description;
        this.avatar = avatar;
        this.reminder = reminder;
    }

    private Person(Person person) {
        this.name = person.name;
        this.phone = person.phone;
        this.email = person.email;
        this.address = person.address;
        this.tags.addAll(person.tags);
        this.birthday = person.birthday;
        this.teleHandle = person.teleHandle;
        this.description = person.description;
        this.avatar = person.avatar;
        this.reminder = person.reminder;
    }

    public static Person newInstance(Person person) {
        return new Person(person);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Birthday getBirthday() {
        return birthday;
    }

    public TeleHandle getTeleHandle() {
        return teleHandle;
    }

    public Description getDescription() {
        return description;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public Reminder getReminder() {
        return reminder;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name, phone number, email, birthday
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getBirthday().equals(getBirthday());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getBirthday().equals(getBirthday())
                && otherPerson.getTeleHandle().equals(getTeleHandle())
                && otherPerson.getDescription().equals(getDescription())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getAvatar().equals(getAvatar())
                && otherPerson.getReminder().equals(getReminder());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Birthday: ")
                .append(getBirthday());
        if (!getAvatar().isEmpty()) {
            builder.append("; Avatar: ").append(getAvatar());
        }
        if (!getTeleHandle().isEmpty()) {
            builder.append("; Tele Handle: ").append(getTeleHandle());
        }
        if (!getDescription().isEmpty()) {
            builder.append("; Description: ").append(getDescription());
        }
        builder.append("; Reminder: ").append(getReminder());
        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    public int getDaysToRemainingBirthday() {
        return (int) birthday.calculateRemainingDaysToBirthday();
    }

    public int getAge() {
        return birthday.calculateAge();
    }
}
