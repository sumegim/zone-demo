package com.example.mars.zonedemo;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mars on 2017.12.13..
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvAuthor;
        public TextView tvTitle;
        public TextView tvBody;
        public ImageView imgPost;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvAuthor = (TextView) itemView.findViewById(R.id.tvAuthor);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvBody = (TextView) itemView.findViewById(R.id.tvBody);
            imgPost = (ImageView) itemView.findViewById(R.id.imgPost);
        }
    }

    private Context context;
    private List<Post> postList;
    private int lastPosition = -1;

    public PostsAdapter(Context context) {
        this.context = context;
        this.postList = new ArrayList<Post>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_post, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final Post tmpPost = postList.get(position);
        viewHolder.tvAuthor.setText(tmpPost.getUserName());
        viewHolder.tvTitle.setText(tmpPost.getSongTitle());
        viewHolder.tvBody.setText(tmpPost.getSongArtist());

        /*if (!TextUtils.isEmpty(tmpPost.getImageUrl())) {
            Glide.with(context).load(tmpPost.getImageUrl()).into(viewHolder.imgPost);
            viewHolder.imgPost.setVisibility(View.VISIBLE);
        } else {
            viewHolder.imgPost.setVisibility(View.GONE);
        }*/
        viewHolder.imgPost.setVisibility(View.VISIBLE); // temp
        //viewHolder.imgPost.setVisibility(View.GONE);

        //setAnimation(viewHolder.itemView, position);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            // spotify:track:0osPX8TruIebOhKuG6GK8C
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Searching ...", Toast.LENGTH_SHORT).show();
                String uri = tmpPost.getSongSpotifyID();

                if(uri == null){

                    String query = tmpPost.getSongArtist() + " " + tmpPost.getSongTitle();
                    Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                    intent.putExtra(SearchManager.QUERY, query); // query contains search string
                    context.startActivity(intent);
                }
                else if (uri.contains("spotify")) {
                    Intent launcher = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    context.startActivity(launcher);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void addPost(Post post, String key) {
        postList.add(0, post);
        notifyItemInserted(0);
    }

    public void updatePost(Post post, String key){
        String newPostUid = post.getUid();
        notifyDataSetChanged();
        for (int i = 0; i < postList.size(); i++) {
            if (postList.get(i).getUid().equals(newPostUid)){
                postList.set(i, post);
                notifyItemChanged(i);
            }
        }
    }

    public void removePost(Post post){
        postList.remove(post);
        notifyDataSetChanged();
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context,
                    android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}