package seedu.friendbook.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.DateTimeException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.friendbook.commons.core.Messages;
import seedu.friendbook.commons.core.index.Index;
import seedu.friendbook.commons.util.StringUtil;
import seedu.friendbook.logic.parser.exceptions.ParseException;
import seedu.friendbook.model.person.Address;
import seedu.friendbook.model.person.Avatar;
import seedu.friendbook.model.person.Birthday;
import seedu.friendbook.model.person.Description;
import seedu.friendbook.model.person.Email;
import seedu.friendbook.model.person.Name;
import seedu.friendbook.model.person.Phone;
import seedu.friendbook.model.person.TeleHandle;
import seedu.friendbook.model.person.exceptions.BirthdayHasNotOccurredException;
import seedu.friendbook.model.reminder.Reminder;
import seedu.friendbook.model.tag.Tag;


/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid, or contains optional fields enclosed in []
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (containsSquareBracketFields(trimmedName)) {
            throw new ParseException(String.format(Messages.MESSAGE_CONTAINS_FIELD_IN_BRACKETS, "Name"));
        }
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid, or contains optional fields enclosed in []
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (containsSquareBracketFields(trimmedPhone)) {
            throw new ParseException(String.format(Messages.MESSAGE_CONTAINS_FIELD_IN_BRACKETS, "Phone"));
        }
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid, or contains optional fields enclosed in []
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (containsSquareBracketFields(trimmedAddress)) {
            throw new ParseException(String.format(Messages.MESSAGE_CONTAINS_FIELD_IN_BRACKETS, "Address"));
        }
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid, or contains optional fields enclosed in []
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (containsSquareBracketFields(trimmedEmail)) {
            throw new ParseException(String.format(Messages.MESSAGE_CONTAINS_FIELD_IN_BRACKETS, "Email"));
        }
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String birthday} into a {@code Birthday}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code birthday} is invalid, or contains optional fields enclosed in []
     */
    public static Birthday parseBirthday(String bday) throws ParseException {
        requireNonNull(bday);
        String trimmedBday = bday.trim();
        if (containsSquareBracketFields(trimmedBday)) {
            throw new ParseException(String.format(Messages.MESSAGE_CONTAINS_FIELD_IN_BRACKETS, "Birthday"));
        }
        if (!Birthday.isValidFormat(trimmedBday)) {
            throw new ParseException(Birthday.MESSAGE_CONSTRAINTS);
        }
        try {
            Birthday.invalidValuesCheck(trimmedBday);
        } catch (DateTimeException | BirthdayHasNotOccurredException e) {
            throw new ParseException(e.getMessage());
        }
        return new Birthday(trimmedBday);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid, or contains optional fields enclosed in []
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (containsSquareBracketFields(trimmedTag)) {
            throw new ParseException(String.format(Messages.MESSAGE_CONTAINS_FIELD_IN_BRACKETS, "Tag"));
        }
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }
    /**
     * Parses a {@code String teleHandle} into a {@code TeleHandle}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code teleHandle} is invalid, or contains optional fields enclosed in []
     */
    public static TeleHandle parseTeleHandle(String teleHandle) throws ParseException {
        requireNonNull(teleHandle);
        String trimmedTeleHandle = teleHandle.trim();
        if (containsSquareBracketFields(teleHandle)) {
            throw new ParseException(String.format(Messages.MESSAGE_CONTAINS_FIELD_IN_BRACKETS, "TeleHandle"));
        }
        if (!TeleHandle.isValidTeleHandle(trimmedTeleHandle)) {
            throw new ParseException(TeleHandle.MESSAGE_CONSTRAINTS);
        }
        return new TeleHandle(trimmedTeleHandle);
    }
    /**
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid, or contains optional fields enclosed in []
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (containsSquareBracketFields(trimmedDescription)) {
            throw new ParseException(String.format(Messages.MESSAGE_CONTAINS_FIELD_IN_BRACKETS, "Description"));
        }
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String avatar} into a {@code Picture}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code avatar} is invalid, or contains optional fields enclosed in []
     */
    public static Avatar parseAvatar(String avatar) throws ParseException {
        requireNonNull(avatar);
        String trimmedPicture = avatar.trim();
        if (containsSquareBracketFields(trimmedPicture)) {
            throw new ParseException(String.format(Messages.MESSAGE_CONTAINS_FIELD_IN_BRACKETS, "Avatar"));
        }
        if (!Avatar.isValidAvatar(trimmedPicture)) {
            throw new ParseException(Avatar.MESSAGE_CONSTRAINTS);
        }
        return new Avatar(trimmedPicture);
    }

    /**
     * Parses a {@code String reminder} into a {@code Reminder}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code reminder} is invalid, or contains optional fields enclosed in []
     */
    public static Reminder parseReminder(String reminder) throws ParseException {
        requireNonNull(reminder);
        String trimmedReminder = reminder.trim();
        if (containsSquareBracketFields(trimmedReminder)) {
            throw new ParseException(String.format(Messages.MESSAGE_CONTAINS_FIELD_IN_BRACKETS, "Reminder"));
        }
        if (!Reminder.isValidReminder(trimmedReminder)) {
            throw new ParseException(Reminder.MESSAGE_CONSTRAINTS);
        }
        return new Reminder(trimmedReminder);
    }

    /**
     * Helper method to check if given message with prefix contains unremoved square brackets
     * @return true if unremoved square brackets are present
     */
    public static boolean containsSquareBracketFields(String message) {
        return message.contains("[avatar/") || message.contains("[tele/") || message.contains("[desc/")
                || message.contains("[r/") || message.contains("[t/");
    }
}
