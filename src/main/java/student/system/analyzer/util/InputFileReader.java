package student.system.analyzer.util;

import student.system.analyzer.model.StudentActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import com.opencsv.CSVReader;

import static student.system.analyzer.message.Constants.COMPONENT_COLUMN_TITLE;
import static student.system.analyzer.message.Constants.DESCRIPTION_COLUMN_TITLE;
import static student.system.analyzer.message.Constants.EVENT_CONTEXT_COLUMN_TITLE;
import static student.system.analyzer.message.Constants.EVENT_NAME_COLUMN_TITLE;
import static student.system.analyzer.message.Constants.TIME_COLUMN_TITLE;
import static student.system.analyzer.message.ErrorMessages.FILE_PATH_DOES_NOT_EXIST_ERROR_MESSAGE_FORMAT;
import static student.system.analyzer.message.ErrorMessages.INTERNAL_SERVER_ERROR;

public class InputFileReader {

    private static final String WINDOWS_DIRECTORY_DELIMITER = "\\";
    private static final String UNIX_DIRECTORY_DELIMITER = "/";
    private static final String CYRILLIC_CHARSET_NAME = "Cp1251";

    public static Collection<StudentActivity> readCsvFile(String filePath) {
        CSVReader reader = null;
        List<StudentActivity> activities = new ArrayList<>();
        try {
            FileInputStream fileStream = new FileInputStream(filePath.replace(WINDOWS_DIRECTORY_DELIMITER, UNIX_DIRECTORY_DELIMITER));
            InputStreamReader cyrillicStreamReader = new InputStreamReader(fileStream, Charset.forName(CYRILLIC_CHARSET_NAME));
            reader = new CSVReader(cyrillicStreamReader);
            String[] line;
            while ((line = reader.readNext()) != null) {
                String time = line[0];
                String eventContext = line[1];
                String component = line[2];
                String eventName = line[3];
                String description = line[4];

                boolean timeIsValid = !time.equals(TIME_COLUMN_TITLE);
                boolean eventContextIsValid = !eventContext.equals(EVENT_CONTEXT_COLUMN_TITLE);
                boolean componentIsValid = !component.equals(COMPONENT_COLUMN_TITLE);
                boolean eventNameIsValid = !eventName.equals(EVENT_NAME_COLUMN_TITLE);
                boolean descriptionIsValid = !description.equals(DESCRIPTION_COLUMN_TITLE);

                if (timeIsValid || eventContextIsValid || componentIsValid || eventNameIsValid || descriptionIsValid) {
                    StudentActivity studentActivity = new StudentActivity(time, eventContext, component, eventName, description);
                    activities.add(studentActivity);
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.printf(FILE_PATH_DOES_NOT_EXIST_ERROR_MESSAGE_FORMAT, filePath);
            System.exit(0);
        } catch(Exception e) {
            System.out.println(INTERNAL_SERVER_ERROR);
            System.exit(0);
        } finally {
            try {
                Objects.requireNonNull(reader).close();
            } catch (Exception e) {
                System.out.println(INTERNAL_SERVER_ERROR);
                System.exit(0);
            }
        }
        return activities;
    }
}
