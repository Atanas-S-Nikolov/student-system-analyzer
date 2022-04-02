package student.system.analyzer.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static student.system.analyzer.test.util.TestUtils.DEFAULT_COMPONENT;
import static student.system.analyzer.test.util.TestUtils.DEFAULT_DESCRIPTION;
import static student.system.analyzer.test.util.TestUtils.DEFAULT_EVENT_CONTEXT;
import static student.system.analyzer.test.util.TestUtils.DEFAULT_EVENT_NAME;
import static student.system.analyzer.test.util.TestUtils.DEFAULT_STUDENT_ACTIVITY;
import static student.system.analyzer.test.util.TestUtils.DEFAULT_TIME;
import static student.system.analyzer.test.util.TestUtils.TEST_COMPONENT;
import static student.system.analyzer.test.util.TestUtils.TEST_DESCRIPTION;
import static student.system.analyzer.test.util.TestUtils.TEST_EVENT_CONTEXT;
import static student.system.analyzer.test.util.TestUtils.TEST_EVENT_NAME;
import static student.system.analyzer.test.util.TestUtils.TEST_TIME;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

class StudentActivityTest {

    private StudentActivity classUnderTest;
    private StudentActivity expectedStudentActivity;

    @BeforeEach
    void setUp() {
        classUnderTest = DEFAULT_STUDENT_ACTIVITY;
        expectedStudentActivity = new StudentActivity(DEFAULT_TIME, DEFAULT_EVENT_CONTEXT, DEFAULT_COMPONENT, DEFAULT_EVENT_NAME,
                DEFAULT_DESCRIPTION);
    }

    @Test
    public void whenConstructorIsCalled_thenObjectIsCreated() {
        assertThat(classUnderTest, notNullValue());
    }

    @Test
    public void whenGettersAreCalled_thenCorrectResultIsReturned() {
        assertThat(classUnderTest.getTime(), is(DEFAULT_TIME));
        assertThat(classUnderTest.getEventContext(), is(DEFAULT_EVENT_CONTEXT));
        assertThat(classUnderTest.getComponent(), is(DEFAULT_COMPONENT));
        assertThat(classUnderTest.getEventName(), is(DEFAULT_EVENT_NAME));
        assertThat(classUnderTest.getDescription(), is(DEFAULT_DESCRIPTION));
    }

    @Test
    public void givenSameObjects_whenToStringMethodIsCalled_thenCorrectResultIsReturned() {
        assertThat(classUnderTest.toString(), is(expectedStudentActivity.toString()));
    }

    @Test
    public void givenSameObjects_whenToEqualsAndHashCodeAreCalled_thenCorrectResultIsReturned() {
        assertThat(classUnderTest, is(expectedStudentActivity));
        assertThat(classUnderTest.hashCode(), is(expectedStudentActivity.hashCode()));
    }

    @Test
    public void givenDifferentObjects_whenToEqualsAndHashCodeAreCalled_thenCorrectResultIsReturned() {
        expectedStudentActivity = new StudentActivity(TEST_TIME, TEST_EVENT_CONTEXT, TEST_COMPONENT, TEST_EVENT_NAME, TEST_DESCRIPTION);
        assertThat(classUnderTest, is(not(expectedStudentActivity)));
        assertThat(classUnderTest.hashCode(), is(not(expectedStudentActivity.hashCode())));
    }
}