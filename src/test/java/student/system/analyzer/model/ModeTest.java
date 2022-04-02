package student.system.analyzer.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static student.system.analyzer.test.util.TestUtils.DEFAULT_MODE;
import static student.system.analyzer.test.util.TestUtils.DEFAULT_VALUES;
import static student.system.analyzer.test.util.TestUtils.TEST_VALUE;
import static student.system.analyzer.test.util.TestUtils.TEST_VALUE_2;
import static student.system.analyzer.test.util.TestUtils.TEST_VALUE_3;
import static student.system.analyzer.test.util.TestUtils.TEST_VALUE_4;
import static student.system.analyzer.test.util.TestUtils.TEST_VALUE_5;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;


class ModeTest {

    private Mode classUnderTest;
    private Mode expectedMode;

    @BeforeEach
    void setUp() {
        classUnderTest = DEFAULT_MODE;
        expectedMode = buildWithValues(TEST_VALUE, TEST_VALUE_2, TEST_VALUE_3, TEST_VALUE_4, TEST_VALUE_5);
    }

    @Test
    public void whenConstructorIsCalled_thenObjectIsCreated() {
        assertThat(classUnderTest, notNullValue());
    }

    @Test
    public void whenGettersAreCalled_thenCorrectResultIsReturned() {
        assertThat(classUnderTest.getValues(), is(DEFAULT_VALUES));
    }

    @Test
    public void whenAddValueMethodIsCalled_thenValuesContainTheAddedValue() {
        assertThat(expectedMode.getValues(), containsInAnyOrder(TEST_VALUE, TEST_VALUE_2, TEST_VALUE_3, TEST_VALUE_4, TEST_VALUE_5));
    }

    @Test
    public void givenSameObjects_whenToStringMethodIsCalled_thenCorrectResultIsReturned() {
        classUnderTest = expectedMode;
        assertThat(classUnderTest.toString(), is(expectedMode.toString()));
    }

    @Test
    public void givenSameObjects_whenToEqualsAndHashCodeAreCalled_thenCorrectResultIsReturned() {
        classUnderTest = expectedMode;
        assertThat(classUnderTest, is(expectedMode));
        assertThat(classUnderTest.hashCode(), is(expectedMode.hashCode()));
    }

    @Test
    public void givenDifferentObjects_whenToEqualsAndHashCodeAreCalled_thenCorrectResultIsReturned() {
        assertThat(classUnderTest, is(not(expectedMode)));
        assertThat(classUnderTest.hashCode(), is(not(expectedMode.hashCode())));
    }

    private static Mode buildWithValues(String... values) {
        Mode mode = new Mode();
        Arrays.stream(values).forEach(mode::addValue);
        return mode;
    }
}