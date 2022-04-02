package student.system.analyzer;

import student.system.analyzer.calculator.dispersion.DispersionCalculator;
import student.system.analyzer.calculator.distribution.FrequencyDistributionCalculator;
import student.system.analyzer.calculator.mode.ModeCalculator;
import student.system.analyzer.calculator.ICalculator;
import student.system.analyzer.exception.distribution.FrequencyDistributionCalculationException;
import student.system.analyzer.util.InputFileReader;
import student.system.analyzer.model.StudentActivity;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import static student.system.analyzer.message.CommandMessages.COMMANDS_DESCRIPTION;
import static student.system.analyzer.message.CommandMessages.DISPERSION_COMMAND;
import static student.system.analyzer.message.CommandMessages.FREQUENCY_DISTRIBUTION_COMMAND;
import static student.system.analyzer.message.CommandMessages.MODE_COMMAND;
import static student.system.analyzer.util.StudentActivityUtils.filterStudentActivities;
import static student.system.analyzer.util.StudentActivityUtils.getActivityDescriptionInfo;

import static java.util.stream.Collectors.toCollection;

public class Main {

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        System.out.println(COMMANDS_DESCRIPTION);
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        Collection<StudentActivity> filteredStudentActivities = filterStudentActivities(InputFileReader.readCsvFile());
        Map<String, Integer> activityDescriptionInfo = getActivityDescriptionInfo(filteredStudentActivities);
        double countOfObjectsToAnalyze = filteredStudentActivities.size();

        printResult(sendToBeCalculated(command, activityDescriptionInfo, countOfObjectsToAnalyze));
    }

    private static Object sendToBeCalculated(String command, Map<String, Integer> activityDescriptionInfo, double countOfObjectsToAnalyze) {
        ICalculator calculator = null;
        switch (command.trim()) {
            case FREQUENCY_DISTRIBUTION_COMMAND:
                calculator = new FrequencyDistributionCalculator(activityDescriptionInfo, countOfObjectsToAnalyze);
                break;
            case MODE_COMMAND:
                calculator = new ModeCalculator(activityDescriptionInfo);
                break;
            case DISPERSION_COMMAND:
                Set<String> ids = activityDescriptionInfo.keySet().stream().sorted().collect(toCollection(LinkedHashSet::new));
                int[] idsInts = ids.stream().mapToInt(Integer::valueOf).toArray();
                calculator = new DispersionCalculator(idsInts);
                break;
            default:
                System.out.println("Unrecognized command!");
                System.exit(-1);
        }

        Object result = new Object();
        try {
            result = calculator.calculate();
        } catch (FrequencyDistributionCalculationException e) {
            e.printStackTrace();
        } catch (NullPointerException npe) {
            System.out.println("Internal error occurred!");
        }
        return result;
    }

    private static void printResult(Object result) {
        if (result instanceof Collection) {
            ((Collection<?>) result).forEach(System.out::println);
        } else {
            double sumAverage = (double) result;
            System.out.printf("%.2f", sumAverage);
        }
    }
}
