package com.example.mountainguide.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.mountainguide.R;
import com.example.mountainguide.HelperClasses.HomeAdapter.*;
import com.example.mountainguide.User.Location.*;
import com.example.mountainguide.User.Categories.AllCategories;
import com.example.mountainguide.User.Categories.*;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import com.example.mountainguide.Common.LoginSignup.*;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variables
    static final float END_SCALE = 0.7f;
    LinearLayout contentView;

    RecyclerView featuredRecycler, mostViewedRecycler, categoriesRecycler;
    RecyclerView.Adapter adapter;

    private GradientDrawable gradient1, gradient2, gradient3, gradient4;
    ImageView menuIcon;
    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageButton hikingguide, mountain,basecamp, location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

        //Hooks
//        featuredRecycler = findViewById(R.id.featured_recycler);
//        mostViewedRecycler = findViewById(R.id.most_viewed_recycler);
//        categoriesRecycler = findViewById(R.id.categories_recycler);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);
        hikingguide = findViewById(R.id.hikingguide);
        mountain = findViewById(R.id.mountain);
        location = findViewById(R.id.location);
        basecamp = findViewById(R.id.basecamp);

        //Menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        navigationDrawer();

        //Recycler Views Function Calls
//        featuredRecycler();
//        mostViewedRecycler();
//        categoriesRecycler();
        hikingguide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AllCategories.class));
                finish();
            }
        });
        mountain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Mountain.class));
                finish();
            }
        });
        basecamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Basecamp.class));
                finish();
            }
        });
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LocationActivity.class));
                finish();
            }
        });
    }

    //Navigations Drawer Functions
    private void navigationDrawer() {
        //Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {

        drawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimaryDark));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //scale the view based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                //Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_all_categories:
                startActivity(new Intent(getApplicationContext(), AllCategories.class));
                break;
            case R.id.nav_home:
                startActivity(new Intent(getApplicationContext(), UserDashboard.class));
                break;
            case R.id.nav_add_missing_place:
                startActivity(new Intent(getApplicationContext(), LocationActivity.class));
                break;
            case R.id.nav_profile:
                startActivity(new Intent(getApplicationContext(), Profile.class));
                break;
            case R.id.nav_logout:
                startActivity(new Intent(getApplicationContext(), Logout.class));
                break;
        }

        return true;
    }


    //Recycler View Functions
//    private void featuredRecycler() {
//        featuredRecycler.setHasFixedSize(true);
//        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//
//        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();
//        featuredLocations.add(new FeaturedHelperClass(R.drawable.merapi, "Merapi", "Merapi mountain"));
//        featuredLocations.add(new FeaturedHelperClass(R.drawable.merbabu, "Merbabu", "Merbabu mountain"));
//        featuredLocations.add(new FeaturedHelperClass(R.drawable.semeru, "Semeru", "Semeru mountain"));
//
//        adapter = new FeaturedAdapter(featuredLocations);
//        featuredRecycler.setAdapter(adapter);
//
//        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});
//    }

//    private void mostViewedRecycler(){
//        mostViewedRecycler.setHasFixedSize(true);
//        mostViewedRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
//
//        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();
//        featuredLocations.add(new FeaturedHelperClass(R.drawable.mcdonald_img, "Mcdonald's", "hahaha hahah hahah hahah hahah hahah hahah hahah hahhah haha haha haha "));
//        featuredLocations.add(new FeaturedHelperClass(R.drawable.city_1, "Edenrobe", "hahaha hahah hahah hahah hahah hahah hahah hahah hahhah haha haha haha "));
//        featuredLocations.add(new FeaturedHelperClass(R.drawable.city_2, "Sweet and Bakers", "hahaha hahah hahah hahah hahah hahah hahah hahah hahhah haha haha haha "));
//
//        adapter = new FeaturedAdapter(featuredLocations);
//        mostViewedRecycler.setAdapter(adapter);
//
//        GradientDrawable gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});
//    }
//
//    private void categoriesRecycler(){
//        categoriesRecycler.setHasFixedSize(true);
//        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
//
//        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();
//        featuredLocations.add(new FeaturedHelperClass(R.drawable.mcdonald_img, "Mcdonald's", "hahaha hahah hahah hahah hahah hahah hahah hahah hahhah haha haha haha "));
//        featuredLocations.add(new FeaturedHelperClass(R.drawable.city_1, "Edenrobe", "hahaha hahah hahah hahah hahah hahah hahah hahah hahhah haha haha haha "));
//        featuredLocations.add(new FeaturedHelperClass(R.drawable.city_2, "Sweet and Bakers", "hahaha hahah hahah hahah hahah hahah hahah hahah hahhah haha haha haha "));
//
//        adapter = new FeaturedAdapter(featuredLocations);
//        categoriesRecycler.setAdapter(adapter);
//
//        GradientDrawable gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});
//    }


    //Normal Functions
    public void callRetailerScreens(View view) {
        startActivity(new Intent(getApplicationContext(), RetailerStartUpScreen.class));
    }


}