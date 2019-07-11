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

/*
LOGIN ACTIVITY
 */
public class MainActivity extends AppCompatActivity {

    EditText etUsernameInput;
    EditText etPasswordInput;
    Button loginButton;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            //  continue to next activity if user previously logged in
           Intent i = new Intent(MainActivity.this, HomeActivity.class);
           startActivity(i);

        } else {
            // displayes the signup or login screen
            setContentView(R.layout.activity_main);

            etUsernameInput = findViewById(R.id.ivImageView);
            etPasswordInput = findViewById(R.id.password);
            loginButton = findViewById(R.id.loginButton);
            loginButton.setText("Login");
            signUpButton = findViewById(R.id.signUp);
            signUpButton.setText("Sign Up");

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String username = etUsernameInput.getText().toString();
                    final String password = etPasswordInput.getText().toString();
                    Log.i("Main Activity", username);
                    Log.i("Main Activity", password);
                    login(username, password);
                }
            });

            signUpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ParseUser user = new ParseUser();

                    final String username = etUsernameInput.getText().toString();
                    final String password = etPasswordInput.getText().toString();

                    user.setUsername(username);
                    user.setPassword(password);
                    //user.setEmail("email@example.com");
                    //user.put("phone", "650-253-0000"); // TODO : DO YOU NEED THESE?

                    user.signUpInBackground(new SignUpCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                login(username, password);
                            } else {
                                Log.d("Main Activity", "Sign up failed");
                            }
                        }
                    });

                }
            });
        }
    }

    private void login(String username, String password){
        hideKeyboard(this);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e == null) {
                    Log.d("LoginActivity", "Login successful");
                    final Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }   else {
                    Log.e("LoginActivity", "Login failure");
                    e.printStackTrace();
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
