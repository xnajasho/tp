package seedu.friendbook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

import seedu.friendbook.model.person.Person;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** Prompt for clear command. */
    private final boolean clearPrompt;

    /** Command result to view a specific person. */
    private final Optional<Person> personToView;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.clearPrompt = false;
        this.personToView = Optional.ofNullable(null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields to view a person's info.
     */
    public CommandResult(String feedbackToUser, Person personToView, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.clearPrompt = false;
        this.personToView = Optional.of(personToView);
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields to clear prompt.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit,
                         boolean clearPrompt) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.clearPrompt = clearPrompt;
        this.personToView = Optional.of(null);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and {@code clearPrompt} and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean clearPrompt) {
        this(feedbackToUser, false, false, clearPrompt);
    }


    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public Person getPersonToView() {
        return personToView.orElse(null);
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isViewPerson() {
        return personToView.isPresent();
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isClearPrompt() {
        return clearPrompt;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

}
