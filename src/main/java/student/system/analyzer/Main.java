package student.system.analyzer;

import student.system.analyzer.calculator.dispersion.DispersionCalculator;
import student.system.analyzer.calculator.distribution.FrequencyDistributionCalculator;
import student.system.analyzer.calculator.mode.ModeCalculator;
import student.system.analyzer.calculator.ICalculator;
import student.system.analyzer.exception.distribution.FrequencyDistributionCalculationException;
import student.system.analyzer.model.StudentActivity;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import static student.system.analyzer.message.CommandMessages.COMMANDS_DESCRIPTION;
import static student.system.analyzer.message.CommandMessages.CSV_FILE_PATH_INPUT_MESSAGE;
import static student.system.analyzer.message.CommandMessages.DISPERSION_COMMAND;
import static student.system.analyzer.message.CommandMessages.FREQUENCY_DISTRIBUTION_COMMAND;
import static student.system.analyzer.message.CommandMessages.MODE_COMMAND;
import static student.system.analyzer.message.Constants.UNRECOGNIZED_COMMAND_MESSAGE;
import static student.system.analyzer.message.ErrorMessages.INTERNAL_SERVER_ERROR;
import static student.system.analyzer.util.InputFileReader.readCsvFile;
import static student.system.analyzer.util.StudentActivityUtils.filterStudentActivities;
import static student.system.analyzer.util.StudentActivityUtils.getActivityDescriptionInfo;

import static java.util.stream.Collectors.toCollection;

public class Main {

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(CSV_FILE_PATH_INPUT_MESSAGE);
        String csvFilePath = scanner.nextLine();

        Collection<StudentActivity> studentActivities = readCsvFile(csvFilePath);

        System.out.println(COMMANDS_DESCRIPTION);
        String command = scanner.nextLine();

        Collection<StudentActivity> filteredStudentActivities = filterStudentActivities(studentActivities);
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
                System.out.println(UNRECOGNIZED_COMMAND_MESSAGE);
                System.exit(0);
        }

        Object result = new Object();
        try {
            result = calculator.calculate();
        } catch (FrequencyDistributionCalculationException e) {
            e.printStackTrace();
        } catch (NullPointerException npe) {
            System.out.println(INTERNAL_SERVER_ERROR);
            System.exit(0);
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
