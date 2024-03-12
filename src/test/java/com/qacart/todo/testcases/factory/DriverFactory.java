package com.qacart.todo.testcases.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

;

public class DriverFactory {

    public WebDriver initializeDriver() {
        WebDriver driver;
        String browser = System.getProperty("browser", "CHROME");
        switch (browser) {
            case "CHROME" -> {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            case "FIREFOX" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            case "EDGE" -> {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
            default -> {
                throw new RuntimeException(String.format("Browser %s isn't supported", browser));
            }
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        return driver;
    }
}