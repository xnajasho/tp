package seedu.friendbook.model;

import java.nio.file.Path;

import seedu.friendbook.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getFriendBookFilePath();

}
