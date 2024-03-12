package com.qacart.todo.testcases.base;

import com.qacart.todo.testcases.factory.DriverFactory;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BaseTest {
    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    public void setup() {
        driver.set(new DriverFactory().initializeDriver());
    }

    @AfterMethod
    public void tearDown(ITestResult testResult) {
        String testCaseName = testResult.getMethod().getMethodName();

        if (!testResult.isSuccess()) {
            File destFile = new File("target" + File.separator + "screenshots" + File.separator + testCaseName + ".png");
            takeScreenshot(destFile);
        }

        driver.get().quit();
    }

    public void takeScreenshot(File destFile) {
        File file = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, destFile);
            InputStream inputStream = new FileInputStream(destFile);
            Allure.addAttachment("screenshot", inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}