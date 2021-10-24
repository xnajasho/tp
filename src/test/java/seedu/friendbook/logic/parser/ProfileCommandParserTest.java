package seedu.friendbook.logic.parser;

import static seedu.friendbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.friendbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.friendbook.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.friendbook.logic.commands.ProfileCommand;
import seedu.friendbook.model.person.Name;

public class ProfileCommandParserTest {

    private ProfileCommandParser parser = new ProfileCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ProfileCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsProfileCommand() {
        ProfileCommand expectedProfileCommand = new ProfileCommand(new Name("imhere"));
        assertParseSuccess(parser, " n/imhere", expectedProfileCommand);
    }

}
