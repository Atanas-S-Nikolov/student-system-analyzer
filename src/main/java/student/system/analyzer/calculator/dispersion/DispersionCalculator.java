package student.system.analyzer.calculator.dispersion;

import student.system.analyzer.calculator.ICalculator;

import java.util.Arrays;

public class DispersionCalculator implements ICalculator {

    private final int[] values;

    public DispersionCalculator(int[] values) {
        this.values = values;
    }

    @Override
    public Double calculate() {
        int valuesLength = values.length;
        double average = Arrays.stream(values).sum() / (double) valuesLength;
        double sum = Arrays.stream(values).mapToDouble(id -> Math.pow(average - id, 2)).sum();
        return sum / valuesLength;
    }
}
