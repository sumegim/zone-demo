package com.example.mars.zonedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private String welcomemsg = "Welcome " + FirebaseAuth.getInstance().getCurrentUser().getDisplayName() + "!\n\nIt's time to get in the Zone!";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(welcomemsg);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText("What are your Friends listening to?");
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText("Discover new People and new Music");
                    return true;
            }
            return false;
        }

    };

    private RecyclerView recyclerViewPosts;
    private PostsAdapter postsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem("You", R.drawable.account);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Friends", R.drawable.account_multiple);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Discover", R.drawable.human_greeting);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);

        bottomNavigation.setBehaviorTranslationEnabled(true);
        bottomNavigation.setTranslucentNavigationEnabled(true);

        postsAdapter = new PostsAdapter(getApplicationContext());
        recyclerViewPosts = (RecyclerView) findViewById(
                R.id.recyclerViewPosts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerViewPosts.setLayoutManager(new GridLayoutManager(recyclerViewPosts.getContext(), 1));
        recyclerViewPosts.setAdapter(postsAdapter);

        initPostsListener();
    }
    private void initPostsListener() {
        /*for (int i = 0; i < 10; i++) {
            Post newPost = new Post("007", "User " + i + " is Listening to:", "Track " + i, "by Big Shaq");
            postsAdapter.addPost(newPost, "key");
        }*/

        Post newPost = new Post("007", "Your latest favorite Track is:", "Gucci Gang", "by Lil Pump");
        Post newPost2 = new Post("007", "New:", "You have a match!", "Check them out!");
        postsAdapter.addPost(newPost2, "key");
        postsAdapter.addPost(newPost, "key");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            });
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
