package seedu.friendbook.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.friendbook.commons.core.Messages.MESSAGE_COMMAND_CONTAINS_UPPERCASE;
import static seedu.friendbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.friendbook.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.friendbook.testutil.Assert.assertThrows;
import static seedu.friendbook.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.friendbook.logic.commands.AddCommand;
import seedu.friendbook.logic.commands.ClearCommand;
import seedu.friendbook.logic.commands.DeleteCommand;
import seedu.friendbook.logic.commands.EditCommand;
import seedu.friendbook.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.friendbook.logic.commands.ExitCommand;
import seedu.friendbook.logic.commands.FindCommand;
import seedu.friendbook.logic.commands.HelpCommand;
import seedu.friendbook.logic.commands.ListCommand;
import seedu.friendbook.logic.commands.ProfileCommand;
import seedu.friendbook.logic.commands.ViewCommand;
import seedu.friendbook.logic.parser.exceptions.ParseException;
import seedu.friendbook.model.person.NameContainsKeywordsPredicate;
import seedu.friendbook.model.person.Person;
import seedu.friendbook.testutil.EditPersonDescriptorBuilder;
import seedu.friendbook.testutil.PersonBuilder;
import seedu.friendbook.testutil.PersonUtil;

public class FriendBookParserTest {

    private static final String ADD_COMMAND_WITH_UPPERCASE = "Add";
    private static final String EDIT_COMMAND_WITH_UPPERCASE = "eDit";
    private static final String DELETE_COMMAND_WITH_UPPERCASE = "deLete";
    private static final String CLEAR_COMMAND_WITH_UPPERCASE = "CLEAR";
    private static final String FIND_COMMAND_WITH_UPPERCASE = "FiND";
    private static final String FINDTAG_COMMAND_WITH_UPPERCASE = "findTag";
    private static final String LIST_COMMAND_WITH_UPPERCASE = "List";
    private static final String EXIT_COMMAND_WITH_UPPERCASE = "EXIT";
    private static final String HELP_COMMAND_WITH_UPPERCASE = "HelP";
    private static final String VIEW_COMMAND_WITH_UPPERCASE = "VIew";
    private static final String PROFILE_COMMAND_WITH_UPPERCASE = "PROFILe";

    private static final String INVALID_ADD_COMMAND_WITH_UPPERCASE = "Adding";
    private static final String INVALID_EDIT_COMMAND_WITH_UPPERCASE = "Editing";
    private static final String INVALID_UNKNOWN_COMMAND_WITH_UPPERCASE = "asxvFSD";


    private final FriendBookParser parser = new FriendBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_view() throws Exception {
        ViewCommand command = (ViewCommand) parser.parseCommand(
                ViewCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new ViewCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_profile() throws Exception {
        assertTrue(parser.parseCommand(ProfileCommand.COMMAND_WORD + " n/me") instanceof ProfileCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void parseCommand_addCommandHasUpperCase_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_COMMAND_CONTAINS_UPPERCASE, "add"), () ->
                parser.parseCommand(ADD_COMMAND_WITH_UPPERCASE));
    }

    @Test
    public void parseCommand_editCommandHasUpperCase_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_COMMAND_CONTAINS_UPPERCASE, "edit"), () ->
                parser.parseCommand(EDIT_COMMAND_WITH_UPPERCASE));
    }

    @Test
    public void parseCommand_deleteCommandHasUpperCase_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_COMMAND_CONTAINS_UPPERCASE, "delete"), () ->
                parser.parseCommand(DELETE_COMMAND_WITH_UPPERCASE));
    }

    @Test
    public void parseCommand_clearCommandHasUpperCase_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_COMMAND_CONTAINS_UPPERCASE, "clear"), () ->
                parser.parseCommand(CLEAR_COMMAND_WITH_UPPERCASE));
    }

    @Test
    public void parseCommand_findCommandHasUpperCase_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_COMMAND_CONTAINS_UPPERCASE, "find"), () ->
                parser.parseCommand(FIND_COMMAND_WITH_UPPERCASE));
    }

    @Test
    public void parseCommand_findtagCommandHasUpperCase_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_COMMAND_CONTAINS_UPPERCASE, "findtag"), () ->
                parser.parseCommand(FINDTAG_COMMAND_WITH_UPPERCASE));
    }

    @Test
    public void parseCommand_listCommandHasUpperCase_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_COMMAND_CONTAINS_UPPERCASE, "list"), () ->
                parser.parseCommand(LIST_COMMAND_WITH_UPPERCASE));
    }

    @Test
    public void parseCommand_exitCommandHasUpperCase_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_COMMAND_CONTAINS_UPPERCASE, "exit"), () ->
                parser.parseCommand(EXIT_COMMAND_WITH_UPPERCASE));
    }

    @Test
    public void parseCommand_helpCommandHasUpperCase_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_COMMAND_CONTAINS_UPPERCASE, "help"), () ->
                parser.parseCommand(HELP_COMMAND_WITH_UPPERCASE));
    }

    @Test
    public void parseCommand_viewCommandHasUpperCase_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_COMMAND_CONTAINS_UPPERCASE, "view"), () ->
                parser.parseCommand(VIEW_COMMAND_WITH_UPPERCASE));
    }

    @Test
    public void parseCommand_profileCommandHasUpperCase_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_COMMAND_CONTAINS_UPPERCASE, "profile"), () ->
                parser.parseCommand(PROFILE_COMMAND_WITH_UPPERCASE));
    }

    @Test
    public void parseCommand_isAValidCommand() {
        assertTrue(FriendBookParser.isAValidCommand(ADD_COMMAND_WITH_UPPERCASE));
        assertTrue(FriendBookParser.isAValidCommand(EDIT_COMMAND_WITH_UPPERCASE));
        assertTrue(FriendBookParser.isAValidCommand(DELETE_COMMAND_WITH_UPPERCASE));
        assertTrue(FriendBookParser.isAValidCommand(CLEAR_COMMAND_WITH_UPPERCASE));
        assertTrue(FriendBookParser.isAValidCommand(FIND_COMMAND_WITH_UPPERCASE));
        assertTrue(FriendBookParser.isAValidCommand(FINDTAG_COMMAND_WITH_UPPERCASE));
        assertTrue(FriendBookParser.isAValidCommand(LIST_COMMAND_WITH_UPPERCASE));
        assertTrue(FriendBookParser.isAValidCommand(EXIT_COMMAND_WITH_UPPERCASE));
        assertTrue(FriendBookParser.isAValidCommand(HELP_COMMAND_WITH_UPPERCASE));
        assertTrue(FriendBookParser.isAValidCommand(VIEW_COMMAND_WITH_UPPERCASE));
        assertTrue(FriendBookParser.isAValidCommand(PROFILE_COMMAND_WITH_UPPERCASE));

        assertFalse(FriendBookParser.isAValidCommand(INVALID_ADD_COMMAND_WITH_UPPERCASE));
        assertFalse(FriendBookParser.isAValidCommand(INVALID_EDIT_COMMAND_WITH_UPPERCASE));
        assertFalse(FriendBookParser.isAValidCommand(INVALID_UNKNOWN_COMMAND_WITH_UPPERCASE));
    }

    @Test
    public void parseCommand_hasUpperCase() {
        assertTrue(FriendBookParser.hasUpperCase(ADD_COMMAND_WITH_UPPERCASE));
        assertTrue(FriendBookParser.hasUpperCase(EDIT_COMMAND_WITH_UPPERCASE));
        assertTrue(FriendBookParser.hasUpperCase(DELETE_COMMAND_WITH_UPPERCASE));
        assertTrue(FriendBookParser.hasUpperCase(CLEAR_COMMAND_WITH_UPPERCASE));
        assertTrue(FriendBookParser.hasUpperCase(FIND_COMMAND_WITH_UPPERCASE));
        assertTrue(FriendBookParser.hasUpperCase(FINDTAG_COMMAND_WITH_UPPERCASE));
        assertTrue(FriendBookParser.hasUpperCase(LIST_COMMAND_WITH_UPPERCASE));
        assertTrue(FriendBookParser.hasUpperCase(EXIT_COMMAND_WITH_UPPERCASE));
        assertTrue(FriendBookParser.hasUpperCase(HELP_COMMAND_WITH_UPPERCASE));
        assertTrue(FriendBookParser.hasUpperCase(VIEW_COMMAND_WITH_UPPERCASE));
        assertTrue(FriendBookParser.hasUpperCase(PROFILE_COMMAND_WITH_UPPERCASE));

        assertFalse(FriendBookParser.hasUpperCase(INVALID_ADD_COMMAND_WITH_UPPERCASE));
        assertFalse(FriendBookParser.hasUpperCase(INVALID_EDIT_COMMAND_WITH_UPPERCASE));
        assertFalse(FriendBookParser.hasUpperCase(INVALID_UNKNOWN_COMMAND_WITH_UPPERCASE));

        assertFalse(FriendBookParser.hasUpperCase("add"));
    }
}
