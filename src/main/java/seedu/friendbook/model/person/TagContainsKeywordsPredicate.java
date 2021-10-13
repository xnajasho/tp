package seedu.friendbook.model.person;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.friendbook.commons.util.StringUtil;
import seedu.friendbook.model.tag.Tag;

public class TagContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        Set<Tag> tags = person.getTags();
        boolean checker = false;
        for (Tag tag : tags) {
            String check = tag.getTagName();
            for (String keyword : keywords) {
                if (StringUtil.containsWordIgnoreCase(check, keyword)) {
                    checker = true;
                }
            }
        }
        return checker;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }

}
