package student.system.analyzer.validator.distribution;

import student.system.analyzer.model.FrequencyDistribution;

import java.util.Collection;

public class FrequencyDistributionValidator {

    private final Collection<FrequencyDistribution> distributions;

    public FrequencyDistributionValidator(Collection<FrequencyDistribution> distributions) {
        this.distributions = distributions;
    }

    public Collection<FrequencyDistribution> getDistributions() {
        return this.distributions;
    }

    public boolean validateAbsoluteFrequencyDistribution(double expectedAbsoluteFrequenciesSum) {
        return getDistributions().stream()
                .mapToInt(FrequencyDistribution::getAbsoluteFrequency)
                .sum() == expectedAbsoluteFrequenciesSum;
    }

    public boolean validateRelativeFrequencyDistribution() {
        return Math.round(getDistributions().stream()
                .mapToDouble(FrequencyDistribution::getRelativeFrequency)
                .sum()) == 1;
    }
}
