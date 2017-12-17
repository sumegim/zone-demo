package com.example.mars.zonedemo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    private AHBottomNavigation bottomNavigation;
    private Toolbar toolbar;
    private NoSwipePager viewPager;
    private BottomBarAdapter pagerAdapter;

    private boolean notificationVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_YES);

        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        //getSupportActionBar().setTitle("Bottom Navigation");

        setupViewPager();

        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        setupBottomNavStyle();
        //setupbehavior todo

        createFakeNotification();

        addBottomNavigationItems();
        bottomNavigation.setCurrentItem(0);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
//                fragment.updateColor(ContextCompat.getColor(MainActivity.this, colors[position]));

                if (!wasSelected)
                    viewPager.setCurrentItem(position);

                return true;
            }
        });


    }

    public static final String SERVICECMD = "com.android.music.musicservicecommand";


    private void addBottomNavigationItems(){
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("You", R.drawable.account);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Friends", R.drawable.account_multiple);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Discover", R.drawable.human_greeting);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
    }

    private void setupBottomNavStyle(){
        bottomNavigation.setBehaviorTranslationEnabled(true);
        bottomNavigation.setTranslucentNavigationEnabled(true);

        bottomNavigation.setDefaultBackgroundColor(getResources().getColor(R.color.colorPrimary));
        bottomNavigation.setAccentColor(Color.RED);
        bottomNavigation.setInactiveColor(getResources().getColor(R.color.colorDirty));

        // Colors for selected (active) and non-selected items.
        bottomNavigation.setColoredModeColors(Color.RED,
                getResources().getColor(R.color.colorPrimaryLight));

        //  Enables Reveal effect
        //bottomNavigation.setColored(true);

        //  Displays item Title always (for selected and non-selected items)
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
    }

    private void setupViewPager(){
        viewPager = (NoSwipePager) findViewById(R.id.viewpager);
        viewPager.setPagingEnabled(false);

        pagerAdapter = new BottomBarAdapter(getSupportFragmentManager());

        pagerAdapter.addFragments(createFragment(0));
        pagerAdapter.addFragments(createFragment(1));
        pagerAdapter.addFragments(createFragment(2));

        viewPager.setAdapter(pagerAdapter);
    }

    private void createFakeNotification() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AHNotification notification = new AHNotification.Builder()
                        .setText("1")
                        .setBackgroundColor(Color.RED)
                        .setTextColor(Color.WHITE)
                        .build();
                // Adding notification to last item.

                bottomNavigation.setNotification(notification, bottomNavigation.getItemsCount() - 1);

                notificationVisible = true;
            }
        }, 1000);
    }

    @NonNull
    private DummyFragment createFragment(int p) {
        DummyFragment fragment = new DummyFragment();
        fragment.setArguments(passFragmentArguments(p));
        return fragment;
    }

    @NonNull
    private Bundle passFragmentArguments(int p) {
        Bundle bundle = new Bundle();
        bundle.putInt("page", p);
        return bundle;
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
