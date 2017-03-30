package com.dnr.espressosampleapp;


public class LoginPresenterImpl implements ILoginPresenter {
    ILoginView mLoginView;

    public LoginPresenterImpl(ILoginView loginView)
    {
            mLoginView = loginView;
    }


    @Override
    public void validateUserCredentials(String userName, String password) {
        boolean isValid = true;
        if(userName==null || userName.isEmpty())
        {
            mLoginView.showUserNameError();
            isValid = false;
        }
        if(password == null || password.isEmpty())
        {
            mLoginView.showPasswordError();
            isValid =false;
        }
        if(isValid)
        {
            mLoginView.loginSuccess();
        }
    }
}
