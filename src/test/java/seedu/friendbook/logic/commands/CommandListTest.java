package seedu.friendbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class CommandListTest {
    private Command.CommandList commandList;

    @Test
    public void getAddCommandUsage() {
        assertEquals(commandList.ADD.getCommandMessageUsage(), AddCommand.MESSAGE_USAGE);
        assertTrue(commandList.ADD.toString().equals(AddCommand.COMMAND_WORD));
    }

    @Test
    public void getEditCommandUsage() {
        assertEquals(commandList.EDIT.getCommandMessageUsage(), EditCommand.MESSAGE_USAGE);
        assertTrue(commandList.EDIT.toString().equals(EditCommand.COMMAND_WORD));
    }

    @Test
    public void getClearCommandUsage() {
        assertEquals(commandList.CLEAR.getCommandMessageUsage(), ClearCommand.MESSAGE_USAGE);
        assertTrue(commandList.CLEAR.toString().equals(ClearCommand.COMMAND_WORD));
    }

    @Test
    public void getDeleteCommandUsage() {
        assertEquals(commandList.DELETE.getCommandMessageUsage(), DeleteCommand.MESSAGE_USAGE);
        assertTrue(commandList.DELETE.toString().equals(DeleteCommand.COMMAND_WORD));
    }

    @Test
    public void getExitCommandUsage() {
        assertEquals(commandList.EXIT.getCommandMessageUsage(), ExitCommand.MESSAGE_USAGE);
        assertTrue(commandList.EXIT.toString().equals(ExitCommand.COMMAND_WORD));
    }

    @Test
    public void getFindCommandUsage() {
        assertEquals(commandList.FIND.getCommandMessageUsage(), FindCommand.MESSAGE_USAGE);
        assertTrue(commandList.FIND.toString().equals(FindCommand.COMMAND_WORD));
    }

    @Test
    public void getFindTagCommandUsage() {
        assertEquals(commandList.FINDTAG.getCommandMessageUsage(), FindTagCommand.MESSAGE_USAGE);
        assertTrue(commandList.FINDTAG.toString().equals(FindTagCommand.COMMAND_WORD));
    }

    @Test
    public void getHelpCommandUsage() {
        assertEquals(commandList.HELP.getCommandMessageUsage(), HelpCommand.MESSAGE_USAGE);
        assertTrue(commandList.HELP.toString().equals(HelpCommand.COMMAND_WORD));
    }

    @Test
    public void getListCommandUsage() {
        assertEquals(commandList.LIST.getCommandMessageUsage(), ListCommand.MESSAGE_USAGE);
        assertTrue(commandList.LIST.toString().equals(ListCommand.COMMAND_WORD));
    }

    @Test
    public void getProfileCommandUsage() {
        assertEquals(commandList.PROFILE.getCommandMessageUsage(), ProfileCommand.MESSAGE_USAGE);
        assertTrue(commandList.PROFILE.toString().equals(ProfileCommand.COMMAND_WORD));
    }

    @Test
    public void getViewCommandUsage() {
        assertEquals(commandList.VIEW.getCommandMessageUsage(), ViewCommand.MESSAGE_USAGE);
        assertTrue(commandList.VIEW.toString().equals(ViewCommand.COMMAND_WORD));
    }

    @Test
    public void execute_getNoCommand_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> commandList.toString());
    }
}
