package com.qacart.todo.testcases;

import com.qacart.todo.apis.TodoApi;
import com.qacart.todo.models.User;
import com.qacart.todo.pages.RegisterPage;
import com.qacart.todo.pages.TodoPage;
import com.qacart.todo.testcases.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TodoTest extends BaseTest {

    @Test(description = "Should be able to create todo")
    public void shouldBeAbleToAddTodo() {
        RegisterPage.getInstance().load(driver.get());
        User user = new User();

        String todoItem = "Learn Java";
        RegisterPage.getInstance().registerUsingApi(driver.get());

        TodoApi.getInstance().addTodo(driver.get().manage().getCookieNamed("access_token").getValue(), todoItem);
        TodoPage.getInstance().load(driver.get());

        String actualItem = TodoPage.getInstance().getTodoText(driver.get());
        Assert.assertEquals(actualItem, todoItem);
    }

    @Test(description = "Should be able to delete todo")
    public void shouldBeAbleToDeleteTodo() {
        RegisterPage.getInstance().load(driver.get());
        User user = new User();
        String todoItem = "Learn Java";

        RegisterPage.getInstance().registerUsingApi(driver.get());

        TodoApi.getInstance().addTodo(driver.get().manage().getCookieNamed("access_token").getValue(), todoItem);
        TodoPage.getInstance().load(driver.get());

        boolean isNoTodosMessageDisplayed = TodoPage.getInstance()
                .deleteTodo(driver.get())
                .isNoTodosMessageDisplayed(driver.get());

        Assert.assertTrue(isNoTodosMessageDisplayed);
    }
}