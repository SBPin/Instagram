package com.example.instagram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.instagram.model.Post;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class PostDetailsActivity extends AppCompatActivity {

    Post post;
    TextView tvDescription;
    TextView tvUser;
    TextView tvCreatedAt;
    TextView tvNumLikes;
    ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        tvDescription = (TextView) findViewById(R.id.post_description);
        tvUser = (TextView) findViewById(R.id.username);
        tvCreatedAt = (TextView) findViewById(R.id.createdAt);
        tvNumLikes = (TextView) findViewById(R.id.numLikes);
        ivImage = (ImageView) findViewById(R.id.ivImageView);

        post = (Post) Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getSimpleName()));

        tvDescription.setText(post.getDescription());
        tvUser.setText(post.getUser().getUsername());
        tvCreatedAt.setText(getRelativeTimeAgo(String.valueOf(post.getCreatedAt())));
        tvNumLikes.setText(Integer.toString(post.getNumLikes()) + " likes");

        //  displays image
        ParseFile imageToLoad = post.getImage();
        Glide.with(this).load(imageToLoad.getUrl()).into(ivImage);
    }

    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return relativeDate;
    }

}
