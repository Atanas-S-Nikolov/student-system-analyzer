package student.system.analyzer.validator.distribution;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static student.system.analyzer.test.util.TestUtils.INCORRECT_TEST_DISTRIBUTIONS;
import static student.system.analyzer.test.util.TestUtils.TEST_DISTRIBUTIONS;

class FrequencyDistributionValidatorTest {

    private FrequencyDistributionValidator classUnderTest;

    @BeforeEach
    void setUp() {
        classUnderTest = new FrequencyDistributionValidator(TEST_DISTRIBUTIONS);
    }

    @Test
    public void givenDistributions_whenValidateAbsoluteFrequencyIsCalled_thenTrueIsReturned() {
        assertTrue(classUnderTest.validateAbsoluteFrequencyDistribution(12));
    }

    @Test
    public void givenDistributions_whenValidateRelativeFrequencyIsCalled_thenTrueIsReturned() {
        assertTrue(classUnderTest.validateRelativeFrequencyDistribution());
    }

    @Test
    public void givenInvalidDistributions_whenValidateAbsoluteFrequencyIsCalled_thenFalseIsReturned() {
        classUnderTest = new FrequencyDistributionValidator(INCORRECT_TEST_DISTRIBUTIONS);
        assertFalse(classUnderTest.validateAbsoluteFrequencyDistribution(12));
    }

    @Test
    public void givenInvalidDistributions_whenValidateRelativeFrequencyIsCalled_thenFalseIsReturned() {
        classUnderTest = new FrequencyDistributionValidator(INCORRECT_TEST_DISTRIBUTIONS);
        assertFalse(classUnderTest.validateRelativeFrequencyDistribution());
    }
}