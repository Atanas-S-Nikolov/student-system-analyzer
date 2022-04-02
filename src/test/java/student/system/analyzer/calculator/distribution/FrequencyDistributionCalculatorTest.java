package student.system.analyzer.calculator.distribution;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import student.system.analyzer.calculator.ICalculator;
import student.system.analyzer.exception.distribution.FrequencyDistributionCalculationException;
import student.system.analyzer.model.FrequencyDistribution;
import student.system.analyzer.validator.distribution.FrequencyDistributionValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static student.system.analyzer.test.util.TestUtils.COUNT_OF_OBJECTS_TO_ANALYZE;
import static student.system.analyzer.test.util.TestUtils.DEFAULT_ABSOLUTE_FREQUENCY;
import static student.system.analyzer.test.util.TestUtils.DEFAULT_ACTIVITIES_COMPONENT_INFO;
import static student.system.analyzer.test.util.TestUtils.DEFAULT_FREQUENCY_DISTRIBUTION_CALCULATOR;
import static student.system.analyzer.test.util.TestUtils.TEST_FREQUENCY_DISTRIBUTION_CALCULATOR;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.when;

class FrequencyDistributionCalculatorTest {

    @Mock
    private FrequencyDistributionValidator validator;

    @InjectMocks
    private ICalculator classUnderTest;

    @BeforeEach
    void setUp() {
        classUnderTest = DEFAULT_FREQUENCY_DISTRIBUTION_CALCULATOR;
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenCalculateMethodIsCalled_thenCorrectValueForAbsoluteFrequencyIsReturned() {
        Collection<FrequencyDistribution> expectedDistributions = buildDistributions();

        when(validator.validateAbsoluteFrequencyDistribution(DEFAULT_ABSOLUTE_FREQUENCY)).thenReturn(true);
        when(validator.validateRelativeFrequencyDistribution()).thenReturn(true);

        assertThat(classUnderTest.calculate(), is(expectedDistributions));
    }

    @Test
    public void whenAbsoluteFrequencyIsInvalid_thenExceptionIsThrown() {
        classUnderTest = TEST_FREQUENCY_DISTRIBUTION_CALCULATOR;

        when(validator.validateAbsoluteFrequencyDistribution(DEFAULT_ABSOLUTE_FREQUENCY)).thenReturn(false);

        assertThrows(FrequencyDistributionCalculationException.class, () -> classUnderTest.calculate());
    }

    @Test
    public void whenRelativeFrequencyIsInvalid_thenExceptionIsThrown() {
        classUnderTest = TEST_FREQUENCY_DISTRIBUTION_CALCULATOR;

        when(validator.validateAbsoluteFrequencyDistribution(DEFAULT_ABSOLUTE_FREQUENCY)).thenReturn(true);
        when(validator.validateRelativeFrequencyDistribution()).thenReturn(false);

        assertThrows(FrequencyDistributionCalculationException.class, () -> classUnderTest.calculate());
    }

    private Collection<FrequencyDistribution> buildDistributions() {
        List<FrequencyDistribution> distributions = new ArrayList<>();

        DEFAULT_ACTIVITIES_COMPONENT_INFO.forEach((key, value) ->
                distributions.add(new FrequencyDistribution(key, value, (double) value / COUNT_OF_OBJECTS_TO_ANALYZE)));

        return distributions.stream()
                .sorted(comparing(FrequencyDistribution::getUserId))
                .collect(toList());
    }
}