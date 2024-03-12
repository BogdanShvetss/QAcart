package com.qacart.todo.testcases;

import com.qacart.todo.models.User;
import com.qacart.todo.pages.RegisterPage;
import com.qacart.todo.pages.TodoPage;
import com.qacart.todo.testcases.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTest extends BaseTest {

    @Test(description = "Should be able to sign up")
    public void shouldBeAbleToRegisterToTheApplication() {
        RegisterPage.getInstance().load(driver.get());
        User user = new User();
        RegisterPage.getInstance().registerUsingApi(driver.get());

        boolean isWelcomeDisplayed = TodoPage.getInstance().isWelcomeMessageDisplayed(driver.get());

        Assert.assertTrue(isWelcomeDisplayed);
    }
}