package com.dnr.espressosampleapp;


public interface ILoginView {
    void showUserNameError();
    void showPasswordError();
    void showApiErrorMessage(String errorMessage);
    void loginSuccess();
    void loginClick();

}
