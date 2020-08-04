package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtUsername, edtPassword;
    private Button btnLogin, btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Log In");

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
            ParseUser.logOut();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                if (edtUsername.getText().toString().equals("") || edtPassword.getText().toString().equals("")) {
                    FancyToast.makeText(LoginActivity.this, "Username and password is required.", FancyToast.LENGTH_LONG, FancyToast.INFO, false).show();
                } else {
                    ParseUser.logInInBackground(edtUsername.getText().toString(), edtPassword.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (e == null && user != null) {
                                FancyToast.makeText(LoginActivity.this, "Login Successful", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                            } else {
                                FancyToast.makeText(LoginActivity.this, "Login Failed.\n" + e.getLocalizedMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                            }
                        }
                    });
                }
                break;
            case R.id.btnSignUp:
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void rootLayoutTapped(View view) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}