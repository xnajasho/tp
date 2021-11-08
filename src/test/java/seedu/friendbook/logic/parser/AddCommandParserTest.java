package seedu.friendbook.logic.parser;

import static seedu.friendbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.friendbook.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.friendbook.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.AVATAR_DESC_AMY;
import static seedu.friendbook.logic.commands.CommandTestUtil.AVATAR_DESC_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.BIRTHDAY_DESC_AMY;
import static seedu.friendbook.logic.commands.CommandTestUtil.BIRTHDAY_DESC_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.DESCRIPTION_DESC_AMY;
import static seedu.friendbook.logic.commands.CommandTestUtil.DESCRIPTION_DESC_BOB;
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
import static seedu.friendbook.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.friendbook.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.friendbook.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.friendbook.logic.commands.CommandTestUtil.REMINDER_DESC_AMY;
import static seedu.friendbook.logic.commands.CommandTestUtil.REMINDER_DESC_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.friendbook.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.friendbook.logic.commands.CommandTestUtil.TELEHANDLE_DESC_AMY;
import static seedu.friendbook.logic.commands.CommandTestUtil.TELEHANDLE_DESC_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_BIRTHDAY_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.friendbook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.friendbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.friendbook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.friendbook.testutil.TypicalPersons.AMY;
import static seedu.friendbook.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.friendbook.logic.commands.AddCommand;
import seedu.friendbook.model.person.Avatar;
import seedu.friendbook.model.person.Birthday;
import seedu.friendbook.model.person.Email;
import seedu.friendbook.model.person.Name;
import seedu.friendbook.model.person.Person;
import seedu.friendbook.model.person.Phone;
import seedu.friendbook.model.person.TeleHandle;
import seedu.friendbook.model.reminder.Reminder;
import seedu.friendbook.model.tag.Tag;
import seedu.friendbook.testutil.PersonBuilder;


public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + BIRTHDAY_DESC_BOB
                        + DESCRIPTION_DESC_BOB
                        + AVATAR_DESC_BOB
                        + TELEHANDLE_DESC_BOB
                        + REMINDER_DESC_BOB
                        + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_BOB + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + BIRTHDAY_DESC_BOB
                        + DESCRIPTION_DESC_BOB
                        + AVATAR_DESC_BOB
                        + TELEHANDLE_DESC_BOB
                        + REMINDER_DESC_BOB
                        + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + BIRTHDAY_DESC_BOB
                        + DESCRIPTION_DESC_BOB
                        + AVATAR_DESC_BOB
                        + TELEHANDLE_DESC_BOB
                        + REMINDER_DESC_BOB
                        + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));


        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + BIRTHDAY_DESC_BOB
                        + DESCRIPTION_DESC_BOB
                        + AVATAR_DESC_BOB
                        + TELEHANDLE_DESC_BOB
                        + REMINDER_DESC_BOB
                        + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                        + ADDRESS_DESC_BOB + BIRTHDAY_DESC_BOB
                        + DESCRIPTION_DESC_BOB
                        + AVATAR_DESC_BOB
                        + TELEHANDLE_DESC_BOB
                        + REMINDER_DESC_BOB
                        + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags =
                new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + TAG_DESC_HUSBAND + BIRTHDAY_DESC_BOB
                        + DESCRIPTION_DESC_BOB
                        + AVATAR_DESC_BOB
                        + TELEHANDLE_DESC_BOB
                        + REMINDER_DESC_BOB
                        + TAG_DESC_FRIEND,
                new AddCommand(expectedPersonMultipleTags));

        // multiple birthday - last birthday accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + BIRTHDAY_DESC_AMY + BIRTHDAY_DESC_BOB
                        + DESCRIPTION_DESC_BOB
                        + AVATAR_DESC_BOB
                        + TELEHANDLE_DESC_BOB
                        + REMINDER_DESC_BOB
                        + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        //multiple descriptions - last description accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + BIRTHDAY_DESC_BOB
                        + DESCRIPTION_DESC_AMY + DESCRIPTION_DESC_BOB
                        + AVATAR_DESC_BOB
                        + TELEHANDLE_DESC_BOB
                        + REMINDER_DESC_BOB
                        + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple avatar - last avatar accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + BIRTHDAY_DESC_BOB
                        + DESCRIPTION_DESC_BOB
                        + AVATAR_DESC_AMY + AVATAR_DESC_BOB
                        + TELEHANDLE_DESC_BOB
                        + REMINDER_DESC_BOB
                        + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple telehandles - last telehandle accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + BIRTHDAY_DESC_BOB
                        + DESCRIPTION_DESC_BOB
                        + AVATAR_DESC_BOB
                        + TELEHANDLE_DESC_AMY + TELEHANDLE_DESC_BOB
                        + REMINDER_DESC_BOB
                        + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));


        // multiple reminder - last reminder accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + BIRTHDAY_DESC_BOB
                        + DESCRIPTION_DESC_BOB
                        + AVATAR_DESC_BOB
                        + TELEHANDLE_DESC_BOB
                        + REMINDER_DESC_AMY + REMINDER_DESC_BOB
                        + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags, telehandle, description, avatar
        Person expectedPerson = new PersonBuilder(AMY).withTeleHandle("")
                                                    .withDescription("")
                                                    .withAvatar("0").withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + BIRTHDAY_DESC_AMY, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + BIRTHDAY_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + BIRTHDAY_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB
                        + ADDRESS_DESC_BOB + BIRTHDAY_DESC_BOB, expectedMessage);
        // missing birthday prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + VALID_BIRTHDAY_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB
                        + VALID_ADDRESS_BOB + VALID_BIRTHDAY_BOB, expectedMessage);

    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + BIRTHDAY_DESC_BOB
                        + DESCRIPTION_DESC_BOB + AVATAR_DESC_BOB + TELEHANDLE_DESC_BOB + REMINDER_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + BIRTHDAY_DESC_BOB
                        + DESCRIPTION_DESC_BOB + AVATAR_DESC_BOB + TELEHANDLE_DESC_BOB + REMINDER_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                        + ADDRESS_DESC_BOB + BIRTHDAY_DESC_BOB
                        + DESCRIPTION_DESC_BOB + AVATAR_DESC_BOB + TELEHANDLE_DESC_BOB + REMINDER_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Email.MESSAGE_CONSTRAINTS);

        // invalid birthday
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + INVALID_BIRTHDAY_DESC
                        + DESCRIPTION_DESC_BOB + AVATAR_DESC_BOB + TELEHANDLE_DESC_BOB + REMINDER_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Birthday.MESSAGE_CONSTRAINTS);

        // invalid avatar
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + BIRTHDAY_DESC_BOB
                        + DESCRIPTION_DESC_BOB + INVALID_AVATAR_DESC + TELEHANDLE_DESC_BOB + REMINDER_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Avatar.MESSAGE_CONSTRAINTS);

        // invalid telehandle
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + BIRTHDAY_DESC_BOB
                        + DESCRIPTION_DESC_BOB + AVATAR_DESC_BOB + INVALID_TELEHANDLE_DESC + REMINDER_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                TeleHandle.MESSAGE_CONSTRAINTS);

        // invalid reminder
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + BIRTHDAY_DESC_BOB
                        + DESCRIPTION_DESC_BOB + AVATAR_DESC_BOB + TELEHANDLE_DESC_BOB + INVALID_REMINDER_DESC
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Reminder.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB
                        + BIRTHDAY_DESC_BOB + DESCRIPTION_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND,
                Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB
                + BIRTHDAY_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + BIRTHDAY_DESC_BOB + DESCRIPTION_DESC_BOB + TAG_DESC_HUSBAND
                        + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
