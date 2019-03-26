package com.student.myapplication.utils;

import java.time.LocalDate;

public class LoginController {
    private static final LoginController ourInstance = new LoginController();

    public static LoginController getInstance() {
        return ourInstance;
    }

    private LoginController() {
    }
    private String login;

    private LocalDate date;

    private boolean isUser;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        isUser = user;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
