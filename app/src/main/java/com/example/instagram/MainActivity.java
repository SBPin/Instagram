package com.example.instagram;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText etUsernameInput;
    EditText etPasswordInput;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsernameInput = findViewById(R.id.username);
        etPasswordInput = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        loginButton.setText("Login");


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
              final String username = etUsernameInput.getText().toString();
              final String password = etPasswordInput.getText().toString();
                Log.i("Main Activity", username);
                Log.i("Main Activity", password);
            }
        });
    }

    private void login(String username, String password){
        hideKeyboard(this);
        //  TODO - complete later :)
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
