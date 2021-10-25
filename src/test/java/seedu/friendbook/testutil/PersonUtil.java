package seedu.friendbook.testutil;

import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_AVATAR;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_REMINDER;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_TELEHANDLE;

import java.util.Set;

import seedu.friendbook.logic.commands.AddCommand;
import seedu.friendbook.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.friendbook.model.person.Person;
import seedu.friendbook.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        sb.append(PREFIX_BIRTHDAY + person.getBirthday().value + " ");
        if (!person.getAvatar().isEmpty()) {
            sb.append(PREFIX_AVATAR + person.getAvatar().value + " ");
        }
        if (!person.getTeleHandle().isEmpty()) {
            sb.append(PREFIX_TELEHANDLE + person.getTeleHandle().value + " ");
        }
        if (!person.getDescription().isEmpty()) {
            sb.append(PREFIX_DESCRIPTION + person.getDescription().value + " ");
        }
        sb.append(PREFIX_REMINDER + person.getReminder().getStringValue() + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value)
                .append(" "));
        descriptor.getBirthday().ifPresent(birthday -> sb.append(PREFIX_BIRTHDAY)
                .append(birthday.value).append(" "));
        descriptor.getAvatar().ifPresent(avatar -> sb.append(PREFIX_AVATAR).append(avatar.value).append("  "));
        descriptor.getTeleHandle().ifPresent(teleHandle -> sb.append(PREFIX_TELEHANDLE)
                .append(teleHandle.value).append(" "));
        descriptor.getDescription().ifPresent(description -> sb.append(PREFIX_DESCRIPTION)
                .append(description.value).append(" "));
        descriptor.getReminder().ifPresent(reminder -> sb.append(PREFIX_REMINDER)
                .append(reminder.getStringValue()).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
