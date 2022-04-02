package student.system.analyzer.calculator.dispersion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class DispersionCalculatorTest {

    private static final int[] VALUES = new int[] {1, 2, 3, 4, 5, 6};
    private double expectedDispersion;
    private DispersionCalculator classUnderTest;

    @BeforeEach
    void setUp() {
        classUnderTest = new DispersionCalculator(VALUES);
        expectedDispersion = buildDispersion();
    }

    @Test
    public void givenArrayOfIds_whenCalculateMethodIsCalled_thenCorrectValueForDispersionIsReturned() {
        assertThat(classUnderTest.calculate(), is(expectedDispersion));
    }

    private double buildDispersion() {
        int valuesLength = VALUES.length;
        double average = Arrays.stream(VALUES).sum() / (double) valuesLength;
        double sum = Arrays.stream(VALUES).mapToDouble(id -> Math.pow(average - id, 2)).sum();
        return sum / valuesLength;
    }
}