package com.qacart.todo.pages;

import com.qacart.todo.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TodoPage {
    private static TodoPage todoPage;

    private By welcomeMessage = By.cssSelector("[data-testid='welcome']");
    private By plusButton = By.cssSelector("[data-testid='add']");
    private By todoItem = By.cssSelector("[data-testid='todo-item']");
    private By deleteTodo = By.cssSelector("[data-testid='delete']");
    private By noTodosMessage = By.cssSelector("[data-testid='no-todos']");


    private TodoPage() {
    }

    public static TodoPage getInstance() {
        if (todoPage == null) {
            todoPage = new TodoPage();
        }
        return todoPage;
    }

    @Step("Check that welcome message is displayed")
    public boolean isWelcomeMessageDisplayed(WebDriver driver) {
        return driver.findElement(welcomeMessage).isDisplayed();
    }

    @Step("Click on the plus button")
    public void clickOnPlusButton(WebDriver driver) {
        driver.findElement(plusButton).click();
    }

    @Step("Get todo title")
    public String getTodoText(WebDriver driver) {
        return driver.findElement(todoItem).getText();
    }

    @Step("Click on the delete todo icon")
    public TodoPage deleteTodo(WebDriver driver) {
        driver.findElement(deleteTodo).click();
        return this;
    }

    @Step("Check that no todos message is displayed")
    public boolean isNoTodosMessageDisplayed(WebDriver driver) {
        return driver.findElement(noTodosMessage).isDisplayed();
    }

    @Step("Visit todo page")
    public void load(WebDriver driver) {
        driver.get(ConfigUtils.getInstance().getBaseUrl() + "/todo");
    }
}