package com.example.instagram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.instagram.model.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ParseQuery.getQuery(Post.class).findInBackground(new FindCallback<Post>() {

             @Override
             public void done(List<Post> objects, ParseException e) {
                 if(e == null){
                     for(int i = 0; i < objects.size(); i++){
                         Log.d("HomeActivity", "Post["+i+"] = "+objects.get(i).getDescription());
                     }
                 } else {
                     e.printStackTrace();
                 }
              }
        });

    }

}