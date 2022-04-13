package student.system.analyzer.util;

import student.system.analyzer.model.StudentActivity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StudentActivityUtils {

    private static final String EVENT_CONTEXT = "Assignment: Качване на курсови задачи и проекти";
    private static final String COMPONENT = "File submissions";

    public static Map<String, Integer> getActivityDescriptionInfo(Collection<StudentActivity> studentActivities) {
        Map<String, Integer> submittedUserFilesInfo = new HashMap<>();
        Pattern pattern = Pattern.compile("user\\s+with\\s+id\\s+'(\\d+)'");

        for (StudentActivity studentActivity : studentActivities) {
            String description = studentActivity.getDescription();
            Matcher matcher = pattern.matcher(description);

            if (matcher.find()) {
                String userId = matcher.group(1);
                submittedUserFilesInfo.putIfAbsent(userId, 0);
                submittedUserFilesInfo.put(userId, submittedUserFilesInfo.get(userId) + 1);
            }
        }

        return submittedUserFilesInfo;
    }

    public static Collection<StudentActivity> filterStudentActivities(Collection<StudentActivity> activities) {
        return activities.stream()
                .filter(studentActivity -> {
                    boolean componentIsCorrect = studentActivity.getComponent().equals(COMPONENT);
                    boolean eventContextIsCorrect = studentActivity.getEventContext().equals(EVENT_CONTEXT);
                    return componentIsCorrect && eventContextIsCorrect;
                })
                .collect(Collectors.toList());
    }
}
