package eu.profinit.education.flightlog;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Slf4j
@ActiveProfiles("inttest")
public class FlightLogSeleniumTests {

    WebDriver webDriver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }


    @Value("${application.url}")
    private String baseUrl;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        webDriver = new ChromeDriver(options);
    }

    @AfterEach
    public void tearDown() {
        webDriver.quit();
    }

    @Test
    public void shouldAddOnlyOneNewFlight(){
        webDriver.get(baseUrl);
        // wait till the application is fully loaded
        new WebDriverWait(webDriver, Duration.ofSeconds(25)).until(ExpectedConditions.numberOfElementsToBe(By.xpath("//tbody//tr"), 1));
        // get the number of flights already registered, there should already be one
        int numberOfRegisteredFlightsBefore = webDriver.findElements(By.xpath("//tbody//tr")).size();
        assertEquals( 1, numberOfRegisteredFlightsBefore,"There is a one flight at the start of the application");
        // open new flight tab
        webDriver.findElement(By.xpath("/html/body/div/div/ul/li[2]/a")).click();
        // fill in information about a flight and confirm it
        webDriver.findElement(By.xpath("//*[@id=\"takeoffTime\"]")).sendKeys("27100020221830");
        webDriver.findElement(By.xpath("/html/body/router-view/div/form/compose[2]/compose/div[1]/div/div/label[3]")).click();
        // let's wait till the pilots will be loaded into the form
        new WebDriverWait(webDriver, Duration.ofSeconds(30))
            .until(ExpectedConditions.presenceOfNestedElementsLocatedBy(
                By.xpath("/html/body/router-view/div/form/compose[1]/div[2]/compose/div[3]/div/select"),
                By.tagName("option")));
        webDriver.findElement(By.xpath("/html/body/router-view/div/form/div[2]/div/button")).click();
        // let's wait till the data will be processed and saved
        new WebDriverWait(webDriver, Duration.ofSeconds(60)).until(ExpectedConditions.alertIsPresent());
        webDriver.switchTo().alert().accept();
        // move to a list of all flights
        webDriver.findElement(By.xpath("/html/body/div/div/ul/li[1]/a")).click();
        // wait till records will appear on the page
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
            .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//tbody//tr")));

        // get a list of created flights
        int numberOfRegisteredFlightsAfter = webDriver.findElements(By.xpath("//tbody//tr")).size();

        // perform necessarry checks
        assertEquals( 1,numberOfRegisteredFlightsAfter - numberOfRegisteredFlightsBefore,"There should be only one new flight created.");
    }

}
