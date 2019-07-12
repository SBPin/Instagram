package com.example.instagram;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    EditText etUsernameInput;
    EditText etPasswordInput;
    EditText etEmail;
    EditText etPhoneNumber;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etUsernameInput = (EditText) findViewById(R.id.username);
        etPasswordInput = (EditText) findViewById(R.id.password);
        etEmail = (EditText) findViewById(R.id.emailAddress);
        etPhoneNumber = (EditText) findViewById(R.id.phoneNumber);
        signUpButton = findViewById(R.id.signUp);
        signUpButton.setText("Sign Up");


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser user = new ParseUser();

                final String username = etUsernameInput.getText().toString();
                final String password = etPasswordInput.getText().toString();
                final String email = etEmail.getText().toString();
                final String phoneNum = etPhoneNumber.getText().toString();

                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                user.put("handle", phoneNum);

                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            login(username, password);
                        } else {
                            Log.d("SignUpActivity", "Sign up failed");
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }

    private void login(String username, String password){
        hideKeyboard(this);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e == null) {
                    Log.d("SignUpActivity", "Login successful");
                    final Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }   else {
                    Log.e("SignUpActivity", "Login failure");
                    e.printStackTrace();
                    finish();
                }
            }
        });
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
