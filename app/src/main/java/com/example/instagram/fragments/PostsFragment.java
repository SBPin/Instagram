package com.example.instagram.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.instagram.PostAdapter;
import com.example.instagram.R;
import com.example.instagram.model.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class PostsFragment extends Fragment {

    protected RecyclerView rvPosts;
    protected PostAdapter adapter;
    protected List<Post> mPosts;

    public final String APP_TAG = "PostsFragment";

    //  onCreateView inflates view
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rvPosts = view.findViewById(R.id.rvPosts);

        //  create the data source
        mPosts = new ArrayList<>();
        //  create adapter
        adapter = new PostAdapter(getContext(), mPosts);
        //  set the adapter on the recycler view
        rvPosts.setAdapter(adapter);
        //  set the layout manager on the recycler view
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));

        queryPosts();

    }

    //  protected gives us the ability to override in other classes
    protected void queryPosts() {
        ParseQuery<Post> postQuery = new ParseQuery<Post>(Post.class);
        postQuery.include(Post.KEY_USER);
        postQuery.setLimit(20); //  Last 20 posts
        postQuery.addDescendingOrder(Post.KEY_CREATED_AT);  // Most recent
        postQuery.findInBackground(new FindCallback<Post>() {

            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e != null){
                    Log.e(APP_TAG, "Error with query");
                    e.printStackTrace();
                    return;
                }
                mPosts.addAll(posts);
                adapter.notifyDataSetChanged(); //  MUST NOTIFY ADAPTER

                for (int i = 0; i<posts.size(); i++){
                    Post post = posts.get(i);
                    //Log.d(APP_TAG, "POST: "+post.getDescription()+" username: "+post.getUser().getUsername());
                }
            }
        });
    }

}
