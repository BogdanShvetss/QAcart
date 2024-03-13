package com.qacart.todo.pages;

import com.qacart.todo.apis.UserApi;
import com.qacart.todo.models.User;
import com.qacart.todo.utils.ConfigUtils;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private static RegisterPage registerPage;

    private RegisterPage() {
    }


    public static RegisterPage getInstance() {
        if (registerPage == null) {
            registerPage = new RegisterPage();
        }
        return registerPage;
    }


    private By firstName = By.cssSelector("[data-testid='first-name']");
    private By lastName = By.cssSelector("[data-testid='last-name']");
    private By emailInput = By.cssSelector("[data-testid='email']");
    private By passwordInput = By.cssSelector("[data-testid='password']");
    private By confirmPasswordInput = By.cssSelector("[data-testid='confirm-password']");
    private By submitButton = By.cssSelector("[data-testid='submit']");


    @Step("Register using API")
    public void registerUsingApi(WebDriver driver) {
        User user = new User();
        Response response = UserApi.getInstance().register(user);
        Cookie accessTokenCookie = new Cookie("access_token", response.path("access_token"));
        Cookie userIdCookie = new Cookie("userID", response.path("userID"));
        Cookie firstNameCookie = new Cookie("firstName", response.path("firstName"));
        driver.manage().addCookie(accessTokenCookie);
        driver.manage().addCookie(userIdCookie);
        driver.manage().addCookie(firstNameCookie);
        load(driver);
    }

    @Step("Register using UI")
    public void register(WebDriver driver, User user) {
        driver.findElement(firstName).sendKeys(user.getFirstName());
        driver.findElement(lastName).sendKeys(user.getLastName());
        driver.findElement(emailInput).sendKeys(user.getEmail());
        driver.findElement(passwordInput).sendKeys(user.getPassword());
        driver.findElement(confirmPasswordInput).sendKeys(user.getPassword());
        driver.findElement(submitButton).click();
    }

    @Step("Visit sign up page")
    public void load(WebDriver driver) {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + "/signup");
    }
}