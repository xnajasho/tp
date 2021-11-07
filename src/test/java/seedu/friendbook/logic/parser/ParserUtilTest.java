package seedu.friendbook.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.friendbook.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.friendbook.testutil.Assert.assertThrows;
import static seedu.friendbook.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.friendbook.logic.parser.exceptions.ParseException;
import seedu.friendbook.model.person.Address;
import seedu.friendbook.model.person.Avatar;
import seedu.friendbook.model.person.Birthday;
import seedu.friendbook.model.person.Description;
import seedu.friendbook.model.person.Email;
import seedu.friendbook.model.person.Name;
import seedu.friendbook.model.person.Phone;
import seedu.friendbook.model.person.TeleHandle;
import seedu.friendbook.model.reminder.Reminder;
import seedu.friendbook.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String
            INVALID_NAME_WITH_FIELDS_IN_SQUARE_BRACKETS = "Rachel [desc/DESCRIPTION] [avatar/AVATAR] [r/REMINDER]";

    private static final String INVALID_PHONE = "+651234";
    private static final String
            INVALID_PHONE_WITH_FIELDS_IN_SQUARE_BRACKETS = "12314 [tele/TELEHANDLE] [avatar/AVATAR] [t/TAG]";

    private static final String INVALID_ADDRESS = " ";
    private static final String
            INVALID_ADDRESS_WITH_FIELDS_IN_SQUARE_BRACKETS = "11 Street [tele/TELEHANDLE] [avatar/AVATAR] [t/TAG]";

    private static final String INVALID_EMAIL = "example.com";
    private static final String
            INVALID_EMAIL_WITH_FIELDS_IN_SQUARE_BRACKETS = "test@example.com [tele/TELEHANDLE] [t/TAG]";

    private static final String INVALID_TAG = "#friend";
    private static final String
            INVALID_TAG_WITH_FIELDS_IN_SQUARE_BRACKETS = "12314 [tele/TELEHANDLE] [avatar/AVATAR]";

    private static final String INVALID_BIRTHDAY_FORMAT = "1993/02/19";
    private static final String INVALID_BIRTHDAY_VALUES = "2021-09-31";
    private static final String INVALID_BIRTHDAY_NOT_LEAP_YEAR = "2021-02-29";
    private static final String
            INVALID_BIRTHDAY_WITH_FIELDS_IN_SQUARE_BRACKETS = "12314 [desc/DESCRIPTION]";

    private static final String INVALID_AVATAR = "21";
    private static final String
            INVALID_AVATAR_WITH_FIELDS_IN_SQUARE_BRACKETS = "1 [desc/DESCRIPTION] [t/TAG] [tele/TELEHANDLE]";

    private static final String INVALID_TELEHANDLE = "lol";
    private static final String
            INVALID_TELEHANDLE_WITH_FIELDS_IN_SQUARE_BRACKETS = "kimberlymaz [tele/TELEHANDLE] [t/TAG]";

    private static final String
            INVALID_DESCRIPTION_WITH_FIELDS_IN_SQUARE_BRACKETS = "funny guy [desc/DESCRIPTION] [t/TAG]";

    private static final String INVALID_REMINDER = "Onoff";
    private static final String
            INVALID_REMINDER_WITH_FIELDS_IN_SQUARE_BRACKETS = "off [desc/DESCRIPTION] [t/TAG] [tele/TELEHANDLE]";


    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_BIRTHDAY = "1994-04-10";
    private static final String VALID_AVATAR = "5";
    private static final String VALID_TELEHANDLE = "sgcickenrice";
    private static final String VALID_DESCRIPTION = "loves to eat chicken rice";
    private static final String VALID_REMINDER = "off";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    // ------------------------------ FIELD: NAME ---------------------------------------------
    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_invalidValueWithFieldsInSquareBrackets_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME_WITH_FIELDS_IN_SQUARE_BRACKETS));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    // ------------------------------ FIELD: PHONE ---------------------------------------------
    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone(null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_invalidValueWithFieldsInSquareBrackets_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE_WITH_FIELDS_IN_SQUARE_BRACKETS));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    // ------------------------------ FIELD: ADDRESS ---------------------------------------------
    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress(null));
    }

    @Test
    public void parseAddress_invalidValueWithFieldsInSquareBrackets_throwsParseException() {
        assertThrows(ParseException.class, () ->
                ParserUtil.parseAddress(INVALID_ADDRESS_WITH_FIELDS_IN_SQUARE_BRACKETS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    // ------------------------------ FIELD: EMAIL ---------------------------------------------
    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail(null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_invalidValueWithFieldsInSquareBrackets_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL_WITH_FIELDS_IN_SQUARE_BRACKETS));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    // ------------------------------ FIELD: BIRTHDAY ---------------------------------------------
    @Test
    public void parseBirthday_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseBirthday(null));
    }

    @Test
    public void parseBirthday_invalidFormat_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBirthday(INVALID_BIRTHDAY_FORMAT));
    }

    @Test
    public void parseBirthday_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBirthday(INVALID_BIRTHDAY_VALUES));
    }

    @Test
    public void parseBirthday_invalidValueNotLeapYear_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBirthday(INVALID_BIRTHDAY_NOT_LEAP_YEAR));
    }

    @Test
    public void parseBirthday_invalidValueWithFieldsInSquareBrackets_throwsParseException() {
        assertThrows(ParseException.class, () ->
                ParserUtil.parseBirthday(INVALID_BIRTHDAY_WITH_FIELDS_IN_SQUARE_BRACKETS));
    }

    @Test
    public void parseBirthday_validValueWithoutWhitespace_returnsBirthday() throws Exception {
        Birthday expectedBirthday = new Birthday(VALID_BIRTHDAY);
        assertEquals(expectedBirthday, ParserUtil.parseBirthday(VALID_BIRTHDAY));
    }

    @Test
    public void parseBirthday_validValueWithWhitespace_returnsTrimmedBirthday() throws Exception {
        String birthdayWithWhitespace = WHITESPACE + VALID_BIRTHDAY + WHITESPACE;
        Birthday expectedBirthday = new Birthday(VALID_BIRTHDAY);
        assertEquals(expectedBirthday, ParserUtil.parseBirthday(birthdayWithWhitespace));
    }

    // ------------------------------ FIELD: AVATAR ---------------------------------------------
    @Test
    public void parseAvatar_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAvatar(null));
    }

    @Test
    public void parseAvatar_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAvatar(INVALID_AVATAR));
    }

    @Test
    public void parseAvatar_invalidValueWithFieldsInSquareBrackets_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAvatar(INVALID_AVATAR_WITH_FIELDS_IN_SQUARE_BRACKETS));
    }

    @Test
    public void parseAvatar_validValueWithoutWhitespace_returnsAvatar() throws Exception {
        Avatar expectedAvatar = new Avatar(VALID_AVATAR);
        assertEquals(expectedAvatar, ParserUtil.parseAvatar(VALID_AVATAR));
    }

    @Test
    public void parseAvatar_validValueWithWhitespace_returnsTrimmedAvatar() throws Exception {
        String avatarWithWhitespace = WHITESPACE + VALID_AVATAR + WHITESPACE;
        Avatar expectedAvatar = new Avatar(VALID_AVATAR);
        assertEquals(expectedAvatar, ParserUtil.parseAvatar(avatarWithWhitespace));
    }

    // ------------------------------ FIELD: TELEHANDLE ---------------------------------------------
    @Test
    public void parseTeleHandle_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTeleHandle(null));
    }

    @Test
    public void parseTeleHandle_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTeleHandle(INVALID_TELEHANDLE));
    }

    @Test
    public void parseTeleHandle_invalidValueWithFieldsInSquareBrackets_throwsParseException() {
        assertThrows(ParseException.class, () ->
                ParserUtil.parseTeleHandle(INVALID_TELEHANDLE_WITH_FIELDS_IN_SQUARE_BRACKETS));
    }

    @Test
    public void parseTeleHandle_validValueWithoutWhitespace_returnsTeleHandle() throws Exception {
        TeleHandle expectedTeleHandle = new TeleHandle(VALID_TELEHANDLE);
        assertEquals(expectedTeleHandle, ParserUtil.parseTeleHandle(VALID_TELEHANDLE));
    }

    @Test
    public void parseTeleHandle_validValueWithWhitespace_returnsTrimmedTeleHandle() throws Exception {
        String telehandleWithWhitespace = WHITESPACE + VALID_TELEHANDLE + WHITESPACE;
        TeleHandle expectedTeleHandle = new TeleHandle(VALID_TELEHANDLE);
        assertEquals(expectedTeleHandle, ParserUtil.parseTeleHandle(telehandleWithWhitespace));
    }

    // ------------------------------ FIELD: DESCRIPTION ---------------------------------------------
    @Test
    public void parseDescription_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDescription(null));
    }


    @Test
    public void parseDescription_invalidValueWithFieldsInSquareBrackets_throwsParseException() {
        assertThrows(ParseException.class, () ->
                ParserUtil.parseDescription(INVALID_DESCRIPTION_WITH_FIELDS_IN_SQUARE_BRACKETS));
    }

    @Test
    public void parseDescription_validValueWithoutWhitespace_returnsDescription() throws Exception {
        Description expectedDescription = new Description(VALID_DESCRIPTION);
        assertEquals(expectedDescription, ParserUtil.parseDescription(VALID_DESCRIPTION));
    }

    @Test
    public void parseDescription_validValueWithWhitespace_returnsTrimmedDescription() throws Exception {
        String descriptionWithWhitespace = WHITESPACE + VALID_DESCRIPTION + WHITESPACE;
        Description expectedDescription = new Description(VALID_DESCRIPTION);
        assertEquals(expectedDescription, ParserUtil.parseDescription(descriptionWithWhitespace));
    }

    // ------------------------------ FIELD: REMINDER ---------------------------------------------
    @Test
    public void parseReminder_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseReminder(null));
    }

    @Test
    public void parseReminder_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseReminder(INVALID_REMINDER));
    }

    @Test
    public void parseReminder_invalidValueWithFieldsInSquareBrackets_throwsParseException() {
        assertThrows(ParseException.class, () ->
                ParserUtil.parseReminder(INVALID_REMINDER_WITH_FIELDS_IN_SQUARE_BRACKETS));
    }

    @Test
    public void parseReminder_validValueWithoutWhitespace_returnsReminder() throws Exception {
        Reminder expectedReminder = new Reminder(VALID_REMINDER);
        assertEquals(expectedReminder, ParserUtil.parseReminder(VALID_REMINDER));
    }

    @Test
    public void parseReminder_validValueWithWhitespace_returnsTrimmedReminder() throws Exception {
        String reminderWithWhitespace = WHITESPACE + VALID_REMINDER + WHITESPACE;
        Reminder expectedReminder = new Reminder(VALID_REMINDER);
        assertEquals(expectedReminder, ParserUtil.parseReminder(reminderWithWhitespace));
    }

    // ------------------------------ FIELD: TAG ---------------------------------------------
    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_invalidValueWithFieldsInSquareBrackets_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG_WITH_FIELDS_IN_SQUARE_BRACKETS));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }
}
