package student.system.analyzer.test.util;

import student.system.analyzer.calculator.distribution.FrequencyDistributionCalculator;
import student.system.analyzer.model.FrequencyDistribution;
import student.system.analyzer.model.Mode;
import student.system.analyzer.model.StudentActivity;

import java.util.*;

public interface TestUtils {

    String DEFAULT_TIME = "default time";
    String DEFAULT_EVENT_CONTEXT = "default event context";
    String DEFAULT_COMPONENT = "default component";
    String DEFAULT_EVENT_NAME = "default event name";
    String DEFAULT_DESCRIPTION = "default description";

    String TEST_TIME = "test time";
    String TEST_EVENT_CONTEXT = "test event context";
    String TEST_COMPONENT = "test component";
    String TEST_EVENT_NAME = "test event name";
    String TEST_DESCRIPTION = "test description";

    String EVENT_CONTEXT = "Assignment: Качване на курсови задачи и проекти";
    String COMPONENT = "File submissions";

    StudentActivity DEFAULT_STUDENT_ACTIVITY = new StudentActivity(DEFAULT_TIME, DEFAULT_EVENT_CONTEXT, DEFAULT_COMPONENT,
            DEFAULT_EVENT_NAME, DEFAULT_DESCRIPTION);
    StudentActivity TEST_STUDENT_ACTIVITY = new StudentActivity("01.01.1970", "Course: Semantic Web",
            "System", "Course viewed", "The user with id '8429' viewed the course with id '130'.");
    StudentActivity TEST_STUDENT_ACTIVITY_2 = new StudentActivity("01.01.1970", EVENT_CONTEXT,
            "System", "Course viewed", "The user with id '8429' viewed the course with id '130'.");
    StudentActivity TEST_STUDENT_ACTIVITY_3 = new StudentActivity("01.01.1970", "Course: Semantic Web",
            COMPONENT, "Course viewed", "The user with id '8429' viewed the course with id '130'.");
    StudentActivity TEST_STUDENT_ACTIVITY_4 = new StudentActivity("26.01.2021", "File: Текущи резултати към 26.01.2021",
            "File", "Course module viewed", "The user with id '8380' viewed the 'resource' activity with course module id '6519'.");
    StudentActivity TEST_STUDENT_ACTIVITY_5 = new StudentActivity("26.02.2021", "Wiki: Избор нa тема за проект (40 т.)",
            "Wiki", "Course module viewed", "The user with id '8408' viewed the 'wiki' activity with course module id '5135'.");
    // filtered activities
    StudentActivity TEST_STUDENT_ACTIVITY_6 = new StudentActivity("22.02.2021", EVENT_CONTEXT,
            COMPONENT, "Submission updated.", "The user with id '8444' updated a file submission and uploaded '2' file/s in the assignment with course module id '4717'.");
    StudentActivity TEST_STUDENT_ACTIVITY_7 = new StudentActivity("26.02.2021", EVENT_CONTEXT,
            COMPONENT, "A file has been uploaded.", "The user with id '8444' has uploaded a file to the submission with id '22303' in the assignment activity with course module id '4717'.");
    StudentActivity TEST_STUDENT_ACTIVITY_8 = new StudentActivity("26.02.2021", EVENT_CONTEXT,
            COMPONENT, "Course viewed", "The user with id '8429' viewed the course with id '130'.");

    List<StudentActivity> TEST_STUDENT_ACTIVITIES = Arrays.asList(TEST_STUDENT_ACTIVITY, TEST_STUDENT_ACTIVITY_2, TEST_STUDENT_ACTIVITY_3,
            TEST_STUDENT_ACTIVITY_4, TEST_STUDENT_ACTIVITY_5, TEST_STUDENT_ACTIVITY_6, TEST_STUDENT_ACTIVITY_7, TEST_STUDENT_ACTIVITY_8);
    List<StudentActivity> EXPECTED_STUDENT_ACTIVITIES = Arrays.asList(TEST_STUDENT_ACTIVITY_6, TEST_STUDENT_ACTIVITY_7, TEST_STUDENT_ACTIVITY_8);

    String DEFAULT_USER_ID = "default user id";
    int DEFAULT_ABSOLUTE_FREQUENCY = 1;
    double DEFAULT_RELATIVE_FREQUENCY = 1.0;

    String TEST_USER_ID = "default user id";
    int TEST_ABSOLUTE_FREQUENCY = 2;
    double TEST_RELATIVE_FREQUENCY = 2.0;

    FrequencyDistribution DEFAULT_FREQUENCY_DISTRIBUTION = new FrequencyDistribution(DEFAULT_USER_ID, DEFAULT_ABSOLUTE_FREQUENCY,
            DEFAULT_RELATIVE_FREQUENCY);

    Collection<String> DEFAULT_VALUES = Collections.emptyList();
    String TEST_VALUE = "1";
    String TEST_VALUE_2 = "2";
    String TEST_VALUE_3 = "3";
    String TEST_VALUE_4 = "4";
    String TEST_VALUE_5 = "5";
    String TEST_VALUE_6 = "6";
    String TEST_VALUE_7 = "7";
    String TEST_VALUE_8 = "8";
    String TEST_VALUE_9 = "9";
    String TEST_VALUE_10 = "10";

    Mode DEFAULT_MODE = new Mode();

    Map<String, Integer> DEFAULT_ACTIVITIES_DESCRIPTION_INFO = new TreeMap<String, Integer>() {
        {
            put(TEST_VALUE, 2);
            put(TEST_VALUE_2, 1);
            put(TEST_VALUE_3, 1);
            put(TEST_VALUE_4, 5);
            put(TEST_VALUE_5, 1);
            put(TEST_VALUE_6, 2);
            put(TEST_VALUE_7, 3);
            put(TEST_VALUE_8, 1);
            put(TEST_VALUE_9, 4);
            put(TEST_VALUE_10, 2);
        }
    };

    Map<String, Integer> TEST_ACTIVITIES_DESCRIPTION_INFO = new TreeMap<String, Integer>() {
        {
            put(TEST_VALUE, 3);
            put(TEST_VALUE_2, 2);
            put(TEST_VALUE_3, 1);
            put(TEST_VALUE_4, 4);
            put(TEST_VALUE_5, 1);
            put(TEST_VALUE_6, 4);
            put(TEST_VALUE_7, 2);
            put(TEST_VALUE_8, 3);
            put(TEST_VALUE_9, 4);
            put(TEST_VALUE_10, 1);
        }
    };

    int COUNT_OF_OBJECTS_TO_ANALYZE = DEFAULT_ACTIVITIES_DESCRIPTION_INFO.values().stream().mapToInt(Integer::valueOf).sum();
    FrequencyDistributionCalculator DEFAULT_FREQUENCY_DISTRIBUTION_CALCULATOR = new FrequencyDistributionCalculator(DEFAULT_ACTIVITIES_DESCRIPTION_INFO, COUNT_OF_OBJECTS_TO_ANALYZE);
    FrequencyDistributionCalculator TEST_FREQUENCY_DISTRIBUTION_CALCULATOR = new FrequencyDistributionCalculator(DEFAULT_ACTIVITIES_DESCRIPTION_INFO, 1);
}
