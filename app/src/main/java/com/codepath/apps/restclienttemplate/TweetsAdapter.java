package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {
    Context context;
    List<Tweet> tweets;

    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }
    // Pass in the context and list of tweets

    @NonNull
    @NotNull
    @Override

    // For each row, inflate the layout

    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }

    // Bind values based on the position of the element
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        // Get the data at position
           Tweet tweet = tweets.get(position);
        // Bind the tweet with the view holder

        holder.bind(tweet);

    }


    @Override
    public int getItemCount() {
        return tweets.size();
    }


    // Define a view holder

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreenName;
        TextView tvScreenNameBold;
        TextView tvTime;
        ImageView ivTweetImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            tvTime = itemView.findViewById(R.id.tvTime);
            ivTweetImage = itemView.findViewById(R.id.ivTweetImage);
            tvScreenNameBold = itemView.findViewById(R.id.tvScreenNameBold);


        }

        public void bind(Tweet tweet) {
            tvBody.setText(tweet.body);
            tvScreenNameBold.setText(tweet.user.screenName);
            tvScreenName.setText("@" + tweet.user.screenName);
            tvTime.setText(tweet.getRelativeTimeAgo(tweet.createdAt));
            Glide.with(context).load(tweet.user.profileImgUrl).into(ivProfileImage);
            if(tweet.tweetImageUrl == null) {
              ivTweetImage.setVisibility(ImageView.GONE);
            } else {
                Glide.with(context)
                        .load(tweet.tweetImageUrl)
                        .override(Target.SIZE_ORIGINAL)
                        .into(ivTweetImage);
            }

        }
    }

    /* Within the RecyclerView.Adapter class */

    // Clean all elements of the recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> list) {
        tweets.addAll(list);
        notifyDataSetChanged();
    }

}

