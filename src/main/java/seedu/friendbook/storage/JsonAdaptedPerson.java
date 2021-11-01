package seedu.friendbook.storage;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.friendbook.commons.exceptions.IllegalValueException;
import seedu.friendbook.model.person.Address;
import seedu.friendbook.model.person.Avatar;
import seedu.friendbook.model.person.Birthday;
import seedu.friendbook.model.person.Description;
import seedu.friendbook.model.person.Email;
import seedu.friendbook.model.person.Name;
import seedu.friendbook.model.person.Person;
import seedu.friendbook.model.person.Phone;
import seedu.friendbook.model.person.TeleHandle;
import seedu.friendbook.model.person.exceptions.BirthdayHasNotOccurredException;
import seedu.friendbook.model.reminder.Reminder;
import seedu.friendbook.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String birthday;
    private final String teleHandle;
    private final String description;
    private final String avatar;
    private final String reminder;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("birthday") String birthday,
            @JsonProperty("avatar") String avatar,
            @JsonProperty("teleHandle") String teleHandle, @JsonProperty("description") String description,
                             @JsonProperty("reminder") String reminder) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.avatar = avatar;
        this.teleHandle = teleHandle;
        this.description = description;
        this.reminder = reminder;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        birthday = source.getBirthday().value;
        teleHandle = source.getTeleHandle().value;
        description = source.getDescription().value;
        avatar = source.getAvatar().value;
        reminder = source.getReminder().getStringValue();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (birthday == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Birthday.class.getSimpleName()));
        }
        if (!Birthday.isValidFormat(birthday)) {
            throw new IllegalValueException(Birthday.MESSAGE_CONSTRAINTS);
        }
        try {
            Birthday.invalidValuesCheck(birthday);
        } catch (DateTimeException | BirthdayHasNotOccurredException e) {
            throw new IllegalValueException(e.getMessage());
        }
        final Birthday modelBirthday = new Birthday(birthday);

        if (teleHandle == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, TeleHandle.class.getSimpleName()));
        }
        if (!TeleHandle.isValidTeleHandle(teleHandle)) {
            throw new IllegalValueException(TeleHandle.MESSAGE_CONSTRAINTS);
        }
        final TeleHandle modelTeleHandle = new TeleHandle(teleHandle);

        if (description == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (avatar == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Avatar.class.getSimpleName()));
        }
        if (!Avatar.isValidAvatar(avatar)) {
            throw new IllegalValueException(Avatar.MESSAGE_CONSTRAINTS);
        }
        final Avatar modelAvatar = new Avatar(avatar);

        if (reminder == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Reminder.class.getSimpleName()));
        }
        if (!Reminder.isValidReminder(reminder)) {
            throw new IllegalValueException(Reminder.MESSAGE_CONSTRAINTS);
        }
        final Reminder modelReminder = new Reminder(reminder);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelTags, modelBirthday,
                modelTeleHandle, modelDescription, modelAvatar, modelReminder);
    }

}
