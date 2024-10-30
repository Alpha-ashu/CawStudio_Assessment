package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DynamicTablePage extends BasePage {

    public DynamicTablePage(WebDriver driver) {
        super(driver);
    }

    public void enterData(String jsonData) {
        driver.findElement(By.xpath("//*[contains(text(),'Table Data')]")).click();
        WebElement dataInput = driver.findElement(By.xpath("//textarea[@id='jsondata']"));
        dataInput.clear();
        dataInput.sendKeys(jsonData);
        driver.findElement(By.xpath("//button[@id='refreshtable']")).click();
    }

    public List<List<String>> fetchDynamicTableData() {
        WebElement table = driver.findElement(By.id("dynamictable"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        List<List<String>> data = new ArrayList<>();

        for (int i = 1; i < rows.size(); i++) {
            List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
            List<String> rowData = new ArrayList<>();

            for (WebElement cell : cells) {
                rowData.add(cell.getText());
            }
            data.add(rowData);
        }

        return data;
    }
}
