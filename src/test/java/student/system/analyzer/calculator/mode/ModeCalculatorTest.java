package student.system.analyzer.calculator.mode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import student.system.analyzer.calculator.ICalculator;

import static student.system.analyzer.test.util.TestUtils.DEFAULT_ACTIVITIES_DESCRIPTION_INFO;
import static student.system.analyzer.test.util.TestUtils.TEST_ACTIVITIES_DESCRIPTION_INFO;
import static student.system.analyzer.test.util.TestUtils.TEST_VALUE_4;
import static student.system.analyzer.test.util.TestUtils.TEST_VALUE_6;
import static student.system.analyzer.test.util.TestUtils.TEST_VALUE_9;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;

class ModeCalculatorTest {

    private ICalculator classUnderTest;

    @BeforeEach
    void setUp() {
        classUnderTest = new ModeCalculator(DEFAULT_ACTIVITIES_DESCRIPTION_INFO);
    }

    @Test
    public void givenActivitiesInfo_whenCalculateMethodIsCalled_thenOnlyOneValueForModeIsReturned() {
        assertThat(classUnderTest.calculate(), hasSize(1));
        assertThat(classUnderTest.calculate(), contains(TEST_VALUE_4));
    }

    @Test
    public void givenActivitiesInfo_whenCalculateMethodIsCalled_thenMoreThanOneValueForModeIsReturned() {
        classUnderTest = new ModeCalculator(TEST_ACTIVITIES_DESCRIPTION_INFO);

        assertThat(classUnderTest.calculate(), hasSize(3));
        assertThat(classUnderTest.calculate(), containsInAnyOrder(TEST_VALUE_4, TEST_VALUE_6, TEST_VALUE_9));
    }
}