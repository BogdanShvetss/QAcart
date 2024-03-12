package com.qacart.todo.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewTodoPage {
    private static NewTodoPage newTodoPage;

    private NewTodoPage() {
    }

    public static NewTodoPage getInstance() {
        if (newTodoPage == null) {
            newTodoPage = new NewTodoPage();
        }

        return newTodoPage;
    }

    private By newTodoInput = By.cssSelector("[data-testid='new-todo']");
    private By newTodoSubmit = By.cssSelector("[data-testid='submit-newTask']");

    @Step("Add todo using UI")
    public void addTodo(WebDriver driver, String item) {
        driver.findElement(newTodoInput).sendKeys(item);
        driver.findElement(newTodoSubmit).click();
    }
}