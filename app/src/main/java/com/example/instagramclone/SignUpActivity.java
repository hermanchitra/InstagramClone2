package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edtEmail, edtUsername, edtPassword;
    private Button btnSignup, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setTitle("Sign Up");

        edtEmail = findViewById(R.id.edtEmail);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);

        btnLogin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null){
            transitionToSocialMediaActivity();
        }

        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    onClick(btnSignup);
                }
                return false;
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignup:
                if (edtEmail.getText().toString().equals("") || edtUsername.getText().toString().equals("") || edtPassword.getText().toString().equals("")) {
                    FancyToast.makeText(SignUpActivity.this, "Email, Username, Password is required", FancyToast.LENGTH_SHORT, FancyToast.INFO, false).show();
                } else {
                    ParseUser user = new ParseUser();
                    user.setEmail(edtEmail.getText().toString());
                    user.setUsername(edtUsername.getText().toString());
                    user.setPassword(edtPassword.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing up " + edtUsername.getText().toString());
                    progressDialog.show();
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(SignUpActivity.this, "Sign Up Successful", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                                transitionToSocialMediaActivity();
                            } else {
                                FancyToast.makeText(SignUpActivity.this, "Sign Up Failed.\n" + e.getLocalizedMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
                }
                break;
            case R.id.btnLogin:
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
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

    private void transitionToSocialMediaActivity() {
        Intent intent = new Intent(SignUpActivity.this, SocialMediaActivity.class);
        startActivity(intent);
    }
}