package seedu.friendbook.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.friendbook.commons.core.GuiSettings;
import seedu.friendbook.model.person.Name;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path friendBookFilePath = Paths.get("data" , "friendbook.json");
    private Name username = new Name("generic username");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setFriendBookFilePath(newUserPrefs.getFriendBookFilePath());
        setUsername(newUserPrefs.getUsername());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getFriendBookFilePath() {
        return friendBookFilePath;
    }

    public void setFriendBookFilePath(Path friendBookFilePath) {
        requireNonNull(friendBookFilePath);
        this.friendBookFilePath = friendBookFilePath;
    }

    public void setUsername(Name username) {
        requireNonNull(username);
        this.username = username;
    }

    public Name getUsername() {
        return this.username;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.getGuiSettings())
                && friendBookFilePath.equals(o.getFriendBookFilePath())
                && username.equals(o.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, friendBookFilePath, username);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + friendBookFilePath);
        sb.append("\nUsername : " + username.fullName);
        return sb.toString();
    }

}
