package student.system.analyzer.util;

import org.junit.jupiter.api.Test;

import static student.system.analyzer.test.util.TestUtils.COLUMN_TITLES_ACTIVITY;
import static student.system.analyzer.test.util.TestUtils.TEST_ACTIVITY_1;
import static student.system.analyzer.test.util.TestUtils.TEST_ACTIVITY_2;
import static student.system.analyzer.test.util.TestUtils.TEST_ACTIVITY_3;
import static student.system.analyzer.test.util.TestUtils.TEST_ACTIVITY_4;
import static student.system.analyzer.test.util.TestUtils.TEST_ACTIVITY_5;
import static student.system.analyzer.test.util.TestUtils.TEST_ACTIVITY_6;
import static student.system.analyzer.test.util.TestUtils.TEST_ACTIVITY_7;
import static student.system.analyzer.test.util.TestUtils.TEST_ACTIVITY_8;
import static student.system.analyzer.test.util.TestUtils.TEST_ACTIVITY_9;
import static student.system.analyzer.util.InputFileReader.readCsvFile;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.not;

class InputFileReaderTest {

    private static final String TEST_CSV_FILE_PATH = "\\src\\test\\test-resources\\test_students_activities.csv";

    @Test
    public void givenCsvFile_whenReadCsvFileMethodIsCalled_thenCorrectDataIsReturned() {
        assertThat(readCsvFile(getTestCsvFilePath()), containsInAnyOrder(TEST_ACTIVITY_1, TEST_ACTIVITY_2, TEST_ACTIVITY_3, TEST_ACTIVITY_4,
                TEST_ACTIVITY_5, TEST_ACTIVITY_6, TEST_ACTIVITY_7, TEST_ACTIVITY_8, TEST_ACTIVITY_9));
    }

    @Test
    public void givenCsvFile_whenReadCsvFileMethodIsCalled_thenColumnTitlesAreNotPresent() {
        assertThat(readCsvFile(getTestCsvFilePath()), not(contains(COLUMN_TITLES_ACTIVITY)));
    }

    private String getTestCsvFilePath() {
        return System.getProperty("user.dir") + TEST_CSV_FILE_PATH;
    }
}