package student.system.analyzer.util;

import student.system.analyzer.model.StudentActivity;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import com.opencsv.CSVReader;

public class InputFileReader {

    public static Collection<StudentActivity> readCsvFile() {
        CSVReader reader = null;
        List<StudentActivity> activities = new ArrayList<>();
        try {
            FileInputStream fileStream = new FileInputStream("C:\\\\Users\\J\\Desktop\\Logs_Course A_StudentsActivities.csv");
            InputStreamReader cyrillicStreamReader = new InputStreamReader(fileStream, Charset.forName("Cp1251"));
            reader = new CSVReader(cyrillicStreamReader);
            String[] line;
            while ((line = reader.readNext()) != null) {
                String time = line[0];
                String eventContext = line[1];
                String component = line[2];
                String eventName = line[3];
                String description = line[4];

                StudentActivity studentActivity = new StudentActivity(time, eventContext, component, eventName, description);
                activities.add(studentActivity);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(reader).close();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return activities;
    }
}
