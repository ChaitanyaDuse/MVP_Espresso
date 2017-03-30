package com.dnr.espressosampleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {

    private ILoginPresenter mLoginPresenter;
    private EditText et_user_name, et_password;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_login = (Button) findViewById(R.id.btn_login);
        et_user_name = (EditText) findViewById(R.id.et_user_name);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login.setOnClickListener(this);

        mLoginPresenter = new LoginPresenterImpl(this);
    }

    @Override
    public void showUserNameError() {
        et_user_name.setError("Invalid username");
    }

    @Override
    public void showPasswordError() {
        et_password.setError("Invalid password");
    }

    @Override
    public void showApiErrorMessage(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, DashboardActivity.class);
        startActivity(i);
    }

    @Override
    public void loginClick() {
        mLoginPresenter.validateUserCredentials(et_user_name.getText().toString(), et_password.getText().toString());
    }

    @Override
    public void onClick(View v) {
        loginClick();
    }
}
