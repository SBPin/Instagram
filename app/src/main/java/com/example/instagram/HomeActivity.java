package com.example.instagram;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.parse.ParseUser;

import java.io.File;

public class HomeActivity extends AppCompatActivity {

    //private static final String imagePath ;

    private Button logOutButton;
    private BottomNavigationView bottomNavigationView;

    public final String APP_TAG = "HomeActivity";
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    public String photoFileName = "photo.jpg";
    File photoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logOutButton = findViewById(R.id.logout);
        bottomNavigationView = findViewById(R.id.bottom_navigation);


        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null

                //  send user back to login activity
                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        Toast.makeText(HomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_compose:
                        Toast.makeText(HomeActivity.this, "Compose", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_profile:
                    default:
                        Toast.makeText(HomeActivity.this, "Profile!", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
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
