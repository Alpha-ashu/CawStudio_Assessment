package testCases;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;


@Listeners({AllureTestNg.class})
public class DynamicTableTestCase extends BaseTest {

    private final String jsonData = "[{\"name\": \"Bob\", \"age\": 20, \"gender\": \"male\"}," +
            "{\"name\": \"George\", \"age\": 42, \"gender\": \"male\"}," +
            "{\"name\": \"Sara\", \"age\": 42, \"gender\": \"female\"}," +
            "{\"name\": \"Conor\", \"age\": 40, \"gender\": \"male\"}," +
            "{\"name\": \"Jennifer\", \"age\": 42, \"gender\": \"female\"}]";

    private final List<List<String>> expectedData = Arrays.asList(
            Arrays.asList("Bob", "20", "male"),
            Arrays.asList("George", "42", "male"),
            Arrays.asList("Sara", "42", "female"),
            Arrays.asList("Conor", "40", "male"),
            Arrays.asList("Jennifer", "42", "female")
    );

    @Test
    @Description("Send data to the dynamic table, fetch the displayed data, and validate it.")
    public void sendDataFetchDataAndAssert() {
        enterData(jsonData);
        List<List<String>> actualData = dynamicTablePage.fetchDynamicTableData();
        validateData(actualData, expectedData);
    }

    @Step("Enter JSON data into the dynamic table")
    public void enterData(String jsonData) {
        dynamicTablePage.enterData(jsonData);
    }

    @Step("Validate fetched data against expected data")
    public void validateData(List<List<String>> actualData, List<List<String>> expectedData) {
        if (actualData.size() != expectedData.size()) {
            System.out.println("Validation failed: Number of rows do not match.");
            return;
        }

        for (int i = 0; i < actualData.size(); i++) {
            List<String> actualRow = actualData.get(i);
            List<String> expectedRow = expectedData.get(i);

            if (!actualRow.equals(expectedRow)) {
                System.out.printf("Validation failed for row %d: Expected %s but found %s%n", i + 1, expectedRow, actualRow);
            } else {
                System.out.printf("Row %d validated successfully: %s%n", i + 1, actualRow);
            }
        }
    }
}
