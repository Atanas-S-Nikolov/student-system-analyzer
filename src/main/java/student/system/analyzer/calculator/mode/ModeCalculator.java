package student.system.analyzer.calculator.mode;

import student.system.analyzer.calculator.ICalculator;
import student.system.analyzer.model.Mode;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public class ModeCalculator implements ICalculator {

    private final Map<String, Integer> activityDescriptionInfo;

    public ModeCalculator(Map<String, Integer> activityDescriptionInfo) {
        this.activityDescriptionInfo = activityDescriptionInfo;
    }

    @Override
    public Collection<String> calculate() {
        Mode mode = new Mode();
        Integer higherAbsoluteFrequency = Collections.max(activityDescriptionInfo.values());
        activityDescriptionInfo.entrySet().stream()
                .filter(entry -> Objects.equals(entry.getValue(), higherAbsoluteFrequency))
                .map(Map.Entry::getKey)
                .forEach(mode::addValue);
        return mode.getValues();
    }
}
