package com.example.instagram;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.instagram.model.Post;
import com.parse.ParseFile;
import com.parse.SaveCallback;

import org.parceler.Parcels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Post post = posts.get(i);
        viewHolder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView ivImage;
        private ImageView ivHeart;
        private TextView tvHandle;
        private TextView  tvDescription;
        private TextView  tvTimeCreated;
        private TextView  numLikes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHandle = itemView.findViewById(R.id.tvHandle);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvTimeCreated = itemView.findViewById(R.id.timeCreated);
            numLikes = itemView.findViewById(R.id.numLikes);
            ivHeart = itemView.findViewById(R.id.likeImage);
            itemView.setOnClickListener(this);

            ivHeart.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    ivHeart.setImageResource(R.drawable.ufi_heart_active);

                    int position = getAdapterPosition();
                    Post post = posts.get(position);

                    int curNumLikes = post.getNumLikes();
                    post.setNumLikes(curNumLikes+1);

                    post.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(com.parse.ParseException e) {
                            if(e != null){
                                e.printStackTrace();
                                return;
                            }
                        }
                    });

                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            //  checks pos. is valid
            if (position != RecyclerView.NO_POSITION) {
                //  get movie at the position
                Post post = posts.get(position);
                //  create intent for new activity
                Intent intent = new Intent (context, PostDetailsActivity.class);
                //  serialize the movie using parceler, use its short name as a key
                intent.putExtra(Post.class.getSimpleName(), Parcels.wrap(post));
                //  show activity
                context.startActivity(intent);
            }
        }

        public void bind(Post post){

            tvHandle.setText(post.getUser().getUsername());
            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivImage);
            }
            tvDescription.setText(post.getDescription());
            tvTimeCreated.setText(getRelativeTimeAgo(String.valueOf(post.getCreatedAt())));
            numLikes.setText((Integer.toString(post.getNumLikes())));
        }
    }

    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
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
