package seedu.friendbook.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.friendbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
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

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.friendbook.commons.core.index.Index;
import seedu.friendbook.logic.commands.EditCommand;
import seedu.friendbook.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.friendbook.logic.parser.exceptions.ParseException;
import seedu.friendbook.model.tag.Tag;


/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_BIRTHDAY, PREFIX_AVATAR, PREFIX_TELEHANDLE, PREFIX_DESCRIPTION, PREFIX_REMINDER,
                        PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_BIRTHDAY).isPresent()) {
            editPersonDescriptor.setBirthday(ParserUtil.parseBirthday(argMultimap.getValue(PREFIX_BIRTHDAY).get()));
        }
        if (argMultimap.getValue(PREFIX_AVATAR).isPresent()) {
            editPersonDescriptor.setAvatar(ParserUtil.parseAvatar(argMultimap.getValue(PREFIX_AVATAR).get()));
        }
        if (argMultimap.getValue(PREFIX_TELEHANDLE).isPresent()) {
            editPersonDescriptor.setTeleHandle(ParserUtil
                    .parseTeleHandle(argMultimap.getValue(PREFIX_TELEHANDLE).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editPersonDescriptor.setDescription(ParserUtil
                    .parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_REMINDER).isPresent()) {
            editPersonDescriptor.setReminder(ParserUtil
                    .parseReminder(argMultimap.getValue(PREFIX_REMINDER).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
