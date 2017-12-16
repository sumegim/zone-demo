package com.example.mars.zonedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mars on 2017.12.15..
 */

public class DummyFragment extends Fragment {

    public DummyFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerViewPosts;
    private PostsAdapter postsAdapter;

    private static final String ARG_PAGE= "page";
    private int pageNumber;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pageNumber = getArguments().getInt(ARG_PAGE);
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.content_posts, container, false);

        postsAdapter = new PostsAdapter(getActivity());
        recyclerViewPosts = (RecyclerView) rootView.findViewById(R.id.recyclerViewPosts);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        //layoutManager.setReverseLayout(true);
        //layoutManager.setStackFromEnd(true);

        recyclerViewPosts.setLayoutManager(layoutManager);
        recyclerViewPosts.setAdapter(postsAdapter);

        initPostsListener(pageNumber);

        return rootView;

    }

    private void initPostsListener(int p) {

        if(p == 1){
            for (int i = 0; i < 10; i++) {
                Post newPost = new Post("007", "User " + i + " is Listening to:", "Track " + i, "by Big Shaq");
                postsAdapter.addPost(newPost, "key");
            }
        }
        if (p == 0) {
            Post newPost = new Post("007", "Your latest favorite Track is:", "Gucci Gang", "by Lil Pump");
            Post newPost2 = new Post("007", "New:", "You have a match!", "Check them out!");
            postsAdapter.addPost(newPost2, "key");
            postsAdapter.addPost(newPost, "key");
        }

    }

}

