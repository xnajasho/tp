package seedu.friendbook.logic.parser;

import static seedu.friendbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.friendbook.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.friendbook.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.AVATAR_DESC_AMY;
import static seedu.friendbook.logic.commands.CommandTestUtil.BIRTHDAY_DESC_AMY;
import static seedu.friendbook.logic.commands.CommandTestUtil.DESCRIPTION_DESC_AMY;
import static seedu.friendbook.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.friendbook.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.INVALID_AVATAR_DESC;
import static seedu.friendbook.logic.commands.CommandTestUtil.INVALID_BIRTHDAY_DESC;
import static seedu.friendbook.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.friendbook.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.friendbook.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.friendbook.logic.commands.CommandTestUtil.INVALID_REMINDER_DESC;
import static seedu.friendbook.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.friendbook.logic.commands.CommandTestUtil.INVALID_TELEHANDLE_DESC;
import static seedu.friendbook.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.friendbook.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.friendbook.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.REMINDER_DESC_AMY;
import static seedu.friendbook.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.friendbook.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.friendbook.logic.commands.CommandTestUtil.TELEHANDLE_DESC_AMY;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_AVATAR_AMY;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_BIRTHDAY_AMY;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AMY;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_REMINDER_AMY;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_TELEHANDLE_AMY;
import static seedu.friendbook.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.friendbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.friendbook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.friendbook.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.friendbook.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.friendbook.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.friendbook.commons.core.index.Index;
import seedu.friendbook.logic.commands.EditCommand;
import seedu.friendbook.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.friendbook.model.person.Avatar;
import seedu.friendbook.model.person.Birthday;
import seedu.friendbook.model.person.Email;
import seedu.friendbook.model.person.Name;
import seedu.friendbook.model.person.Phone;
import seedu.friendbook.model.person.TeleHandle;
import seedu.friendbook.model.reminder.Reminder;
import seedu.friendbook.model.tag.Tag;
import seedu.friendbook.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag
        assertParseFailure(parser, "1" + INVALID_BIRTHDAY_DESC, Birthday.MESSAGE_CONSTRAINTS); // invalid birthday
        assertParseFailure(parser, "1" + INVALID_AVATAR_DESC, Avatar.MESSAGE_CONSTRAINTS); // invalid avatar
        assertParseFailure(parser, "1" + INVALID_TELEHANDLE_DESC, TeleHandle.MESSAGE_CONSTRAINTS); // invalid tele
        assertParseFailure(parser, "1" + INVALID_REMINDER_DESC, Reminder.MESSAGE_CONSTRAINTS); // invalid reminder

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND
                + AVATAR_DESC_AMY + BIRTHDAY_DESC_AMY + DESCRIPTION_DESC_AMY + TELEHANDLE_DESC_AMY + REMINDER_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                .withAvatar(VALID_AVATAR_AMY).withBirthday(VALID_BIRTHDAY_AMY).withDescription(VALID_DESCRIPTION_AMY)
                .withTeleHandle(VALID_TELEHANDLE_AMY).withReminder(VALID_REMINDER_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditPersonDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // birthday
        userInput = targetIndex.getOneBased() + BIRTHDAY_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withBirthday(VALID_BIRTHDAY_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // avatar
        userInput = targetIndex.getOneBased() + AVATAR_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withAvatar(VALID_AVATAR_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withDescription(VALID_DESCRIPTION_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // TeleHandle
        userInput = targetIndex.getOneBased() + TELEHANDLE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withTeleHandle(VALID_TELEHANDLE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // reminder
        userInput = targetIndex.getOneBased() + REMINDER_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withReminder(VALID_REMINDER_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_HUSBAND;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
