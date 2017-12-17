package com.example.mars.zonedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerViewPosts.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPosts.setLayoutManager(layoutManager);
        recyclerViewPosts.setAdapter(postsAdapter);

        initPostsListener(pageNumber);

        return rootView;

    }

    private void initPostsListener(int p) {

        if(p == 0){

        }
        if (p == 1) {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("posts");
            ref.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Post newPost = dataSnapshot.getValue(Post.class);
                    postsAdapter.addPost(newPost, dataSnapshot.getKey());
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    Post newPost = dataSnapshot.getValue(Post.class);
                    postsAdapter.updatePost(newPost, dataSnapshot.getKey());
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    Post newPost = dataSnapshot.getValue(Post.class);
                    postsAdapter.removePost(newPost);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }

}

