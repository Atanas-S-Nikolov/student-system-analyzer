package student.system.analyzer.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static student.system.analyzer.test.util.TestUtils.DEFAULT_ABSOLUTE_FREQUENCY;
import static student.system.analyzer.test.util.TestUtils.DEFAULT_FREQUENCY_DISTRIBUTION;
import static student.system.analyzer.test.util.TestUtils.DEFAULT_RELATIVE_FREQUENCY;
import static student.system.analyzer.test.util.TestUtils.DEFAULT_USER_ID;
import static student.system.analyzer.test.util.TestUtils.TEST_ABSOLUTE_FREQUENCY;
import static student.system.analyzer.test.util.TestUtils.TEST_RELATIVE_FREQUENCY;
import static student.system.analyzer.test.util.TestUtils.TEST_USER_ID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;


class FrequencyDistributionTest {

    private FrequencyDistribution classUnderTest;
    private FrequencyDistribution expectedFrequencyDistribution;

    @BeforeEach
    void setUp() {
        classUnderTest = DEFAULT_FREQUENCY_DISTRIBUTION;
        expectedFrequencyDistribution = new FrequencyDistribution(DEFAULT_USER_ID, DEFAULT_ABSOLUTE_FREQUENCY, DEFAULT_RELATIVE_FREQUENCY);
    }

    @Test
    public void whenConstructorIsCalled_thenObjectIsCreated() {
        assertThat(classUnderTest, notNullValue());
    }

    @Test
    public void whenGettersAreCalled_thenCorrectResultIsReturned() {
        assertThat(classUnderTest.getUserId(), is(DEFAULT_USER_ID));
        assertThat(classUnderTest.getAbsoluteFrequency(), is(DEFAULT_ABSOLUTE_FREQUENCY));
        assertThat(classUnderTest.getRelativeFrequency(), is(DEFAULT_RELATIVE_FREQUENCY));
    }

    @Test
    public void givenSameObjects_whenToStringMethodIsCalled_thenCorrectResultIsReturned() {
        assertThat(classUnderTest.toString(), is(expectedFrequencyDistribution.toString()));
    }

    @Test
    public void givenSameObjects_whenToEqualsAndHashCodeAreCalled_thenCorrectResultIsReturned() {
        assertThat(classUnderTest, is(expectedFrequencyDistribution));
        assertThat(classUnderTest.hashCode(), is(expectedFrequencyDistribution.hashCode()));
    }

    @Test
    public void givenDifferentObjects_whenToEqualsAndHashCodeAreCalled_thenCorrectResultIsReturned() {
        expectedFrequencyDistribution = new FrequencyDistribution(TEST_USER_ID, TEST_ABSOLUTE_FREQUENCY, TEST_RELATIVE_FREQUENCY);
        assertThat(classUnderTest, is(not(expectedFrequencyDistribution)));
        assertThat(classUnderTest.hashCode(), is(not(expectedFrequencyDistribution.hashCode())));
    }
}