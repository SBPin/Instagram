package com.example.instagram.model;

import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

@ParseClassName("Post")
public class Post extends ParseObject implements Parcelable {
    public static final String KEY_DESCRIPTION = "Description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "user";
    public static final String KEY_CREATED_AT = "createdAt";
    public static final String KEY_NUMBER_OF_LIKES = "numLikes";

    public Post(){}

    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    public Integer getNumLikes() { return getInt(KEY_NUMBER_OF_LIKES); }

    public void setNumLikes(Integer i) { put(KEY_NUMBER_OF_LIKES, i);}

    public ParseFile getImage(){
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile image){
        put(KEY_IMAGE, image);
    }

    //  ParseUser used instead of pointer for better funcitonality
    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user){
        put(KEY_USER, user);
    }

    //  Can return different things to feed depending on specifications
    public static class Query extends ParseQuery <Post> {
        public Query() {
            super(Post.class);
        }

        //  Retrieves only top 20 posts
        public Query getTop() {
            setLimit(20);
            return this;
        }

        public Query withUser() {
            include("user");
            return this;
        }

    }


}
