package seedu.friendbook.model;

import java.nio.file.Path;

import seedu.friendbook.commons.core.GuiSettings;
import seedu.friendbook.model.person.Name;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getFriendBookFilePath();

    Name getUsername();

}
