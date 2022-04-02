package student.system.analyzer.util;

import org.junit.jupiter.api.Test;

import student.system.analyzer.model.StudentActivity;

import java.util.Collection;
import java.util.Map;

import static java.util.Collections.singletonList;

import static student.system.analyzer.test.util.TestUtils.DEFAULT_STUDENT_ACTIVITY;
import static student.system.analyzer.test.util.TestUtils.EXPECTED_STUDENT_ACTIVITIES;
import static student.system.analyzer.test.util.TestUtils.TEST_STUDENT_ACTIVITIES;
import static student.system.analyzer.test.util.TestUtils.TEST_STUDENT_ACTIVITY;
import static student.system.analyzer.test.util.TestUtils.TEST_STUDENT_ACTIVITY_2;
import static student.system.analyzer.test.util.TestUtils.TEST_STUDENT_ACTIVITY_3;
import static student.system.analyzer.test.util.TestUtils.TEST_STUDENT_ACTIVITY_4;
import static student.system.analyzer.test.util.TestUtils.TEST_STUDENT_ACTIVITY_6;
import static student.system.analyzer.util.StudentActivityUtils.filterStudentActivities;
import static student.system.analyzer.util.StudentActivityUtils.getActivityDescriptionInfo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.text.MatchesPattern.matchesPattern;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class StudentActivityUtilsTest {

    private final String REGEX_PATTERN = "\\d+";

    private final StudentActivity ACTIVITY_THAT_MATCHES_THE_REGEX_PATTERN = TEST_STUDENT_ACTIVITY;
    private final StudentActivity ACTIVITY_THAT_NOT_MATCHES_THE_REGEX_PATTERN = DEFAULT_STUDENT_ACTIVITY;

    private final StudentActivity ACTIVITY_WITH_INCORRECT_COMPONENT_AND_CORRECT_EVENT_CONTEXT = TEST_STUDENT_ACTIVITY_2;
    private final StudentActivity ACTIVITY_WITH_CORRECT_COMPONENT_AND_INCORRECT_EVENT_CONTEXT = TEST_STUDENT_ACTIVITY_3;
    private final StudentActivity ACTIVITY_WITH_INCORRECT_COMPONENT_AND_INCORRECT_EVENT_CONTEXT = TEST_STUDENT_ACTIVITY_4;
    private final StudentActivity ACTIVITY_WITH_CORRECT_COMPONENT_AND_CORRECT_EVENT_CONTEXT = TEST_STUDENT_ACTIVITY_6;

    @Test
    public void givenStudentActivitiesWithActivityThatMatchesTheRegexPattern_whenGetActivityDescriptionInfoMethodIsCalled_thenTheReturnedResultIsNotEmpty() {
        assertFalse(getActivityDescriptionInfo(singletonList(ACTIVITY_THAT_MATCHES_THE_REGEX_PATTERN)).isEmpty());
    }

    @Test
    public void givenStudentActivitiesWithActivityThatNotMatchesTheRegexPattern_whenGetActivityDescriptionInfoMethodIsCalled_thenTheReturnedResultIsEmpty() {
        assertTrue(getActivityDescriptionInfo(singletonList(ACTIVITY_THAT_NOT_MATCHES_THE_REGEX_PATTERN)).isEmpty());
    }

    @Test
    public void givenStudentActivities_whenGetActivityDescriptionInfoMethodIsCalled_thenReturnedResultMatchesTheRegexPattern() {
        Map<String, Integer> expectedActivityDescriptionInfo = getActivityDescriptionInfo(singletonList(ACTIVITY_THAT_MATCHES_THE_REGEX_PATTERN));
        assertThat(expectedActivityDescriptionInfo.keySet().stream().findFirst().get(), matchesPattern(REGEX_PATTERN));
    }

    @Test
    public void givenListWithStudentActivityWithIncorrectComponentAndCorrectEventContext_whenFilterStudentActivitiesMethodIsCalled_thenFilteredListShouldNotContainsThisActivity() {
        assertThat(filterStudentActivities(singletonList(ACTIVITY_WITH_INCORRECT_COMPONENT_AND_CORRECT_EVENT_CONTEXT)), is(empty()));
    }

    @Test
    public void givenListWithStudentActivityWithCorrectComponentAndIncorrectEventContext_whenFilterStudentActivitiesMethodIsCalled_thenFilteredListShouldNotContainsThisActivity() {
        assertThat(filterStudentActivities(singletonList(ACTIVITY_WITH_CORRECT_COMPONENT_AND_INCORRECT_EVENT_CONTEXT)), is(empty()));
    }

    @Test
    public void givenListWithStudentActivityWithIncorrectComponentAndIncorrectEventContext_whenFilterStudentActivitiesMethodIsCalled_thenFilteredListShouldNotContainsThisActivity() {
        assertThat(filterStudentActivities(singletonList(ACTIVITY_WITH_INCORRECT_COMPONENT_AND_INCORRECT_EVENT_CONTEXT)), is(empty()));
    }

    @Test
    public void givenListWithStudentActivityWithCorrectComponentAndCorrectEventContext_whenFilterStudentActivitiesMethodIsCalled_thenFilteredListShouldContainsThisActivity() {
        Collection<StudentActivity> expectedActivities = filterStudentActivities(singletonList(ACTIVITY_WITH_CORRECT_COMPONENT_AND_CORRECT_EVENT_CONTEXT));
        assertThat(expectedActivities, not(empty()));
        assertThat(expectedActivities, contains(ACTIVITY_WITH_CORRECT_COMPONENT_AND_CORRECT_EVENT_CONTEXT));
    }

    @Test
    public void givenStudentActivities_whenFilterStudentActivitiesMethodIsCalled_thenActivitiesContainsTheCorrectObjects() {
        assertThat(filterStudentActivities(TEST_STUDENT_ACTIVITIES), is(EXPECTED_STUDENT_ACTIVITIES));
    }
}