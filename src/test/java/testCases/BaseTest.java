package testCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import pages.DynamicTablePage;

import java.time.Duration;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})  // Moved here to the test class
public class BaseTest {
    protected WebDriver driver;
    protected DynamicTablePage dynamicTablePage;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
        dynamicTablePage = new DynamicTablePage(driver);
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
