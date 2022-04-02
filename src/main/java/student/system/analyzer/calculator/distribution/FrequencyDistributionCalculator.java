package student.system.analyzer.calculator.distribution;

import student.system.analyzer.calculator.ICalculator;
import student.system.analyzer.exception.distribution.FrequencyDistributionCalculationException;
import student.system.analyzer.model.FrequencyDistribution;
import student.system.analyzer.validator.distribution.FrequencyDistributionValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import static student.system.analyzer.message.FrequencyDistributionMessages.CALCULATE_ABSOLUTE_FREQUENCY_DISTRIBUTION_ERROR_MESSAGE;
import static student.system.analyzer.message.FrequencyDistributionMessages.CALCULATE_RELATIVE_FREQUENCY_DISTRIBUTION_ERROR_MESSAGE;

public class FrequencyDistributionCalculator implements ICalculator {

    private final Map<String, Integer> activityDescriptionInfo;
    private final double countOfObjectsToAnalyze;

    public FrequencyDistributionCalculator(Map<String, Integer> activityDescriptionInfo, double countOfObjectsToAnalyze) {
        this.activityDescriptionInfo = activityDescriptionInfo;
        this.countOfObjectsToAnalyze = countOfObjectsToAnalyze;
    }

    @Override
    public Collection<FrequencyDistribution> calculate() {
        Collection<FrequencyDistribution> distributions = new ArrayList<>();
        activityDescriptionInfo.forEach((id, absoluteFrequency) ->
                distributions.add(new FrequencyDistribution(id, absoluteFrequency, absoluteFrequency / countOfObjectsToAnalyze)));

        checkFrequencyDistributions(distributions);

        return distributions.stream()
                .sorted(comparing(FrequencyDistribution::getUserId))
                .collect(toList());
    }

    private void checkFrequencyDistributions(Collection<FrequencyDistribution> distributions) {
        FrequencyDistributionValidator validator = new FrequencyDistributionValidator(distributions);

        if (!validator.validateAbsoluteFrequencyDistribution(countOfObjectsToAnalyze)) {
            throw new FrequencyDistributionCalculationException(CALCULATE_ABSOLUTE_FREQUENCY_DISTRIBUTION_ERROR_MESSAGE);
        }

        if (!validator.validateRelativeFrequencyDistribution()) {
            throw new FrequencyDistributionCalculationException(CALCULATE_RELATIVE_FREQUENCY_DISTRIBUTION_ERROR_MESSAGE);
        }
    }
}
