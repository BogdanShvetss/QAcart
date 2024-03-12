package com.qacart.todo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.javafaker.Faker;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @JsonIgnore
    private String accessToken;

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }


    public String getAccessToken() {
        return accessToken;
    }

    public User setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User() {
        Faker faker = new Faker();
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.email = faker.internet().safeEmailAddress();
        this.password = faker.internet().password(9, 10, true, true);
    }
}