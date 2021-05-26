package com.example.brandstoreuz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.WindowManager;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.example.brandstoreuz.fragments.cart.CartFragment;
import com.example.brandstoreuz.fragments.chat.ChatFragment;
import com.example.brandstoreuz.fragments.favourites.FavouritesFragment;
import com.example.brandstoreuz.fragments.home.Adapters.GridBrandsRecyclerAdapter;
import com.example.brandstoreuz.fragments.home.Adapters.GridRecyclerAdapter;
import com.example.brandstoreuz.fragments.home.Adapters.HorizontalSliderAdapter;
import com.example.brandstoreuz.fragments.home.Adapters.Models.HorizontalModel;
import com.example.brandstoreuz.fragments.home.Adapters.Models.VerticalModel;
import com.example.brandstoreuz.fragments.home.Adapters.VerticalRecyclerViewAdapter;
import com.example.brandstoreuz.fragments.home.HomeFragment;
import com.example.brandstoreuz.fragments.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.main_activity);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,new HomeFragment()).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
    }

    @SuppressLint("NonConstantResourceId")
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        Fragment selectedFragment;
        switch (item.getItemId()){
            case R.id.page_1: selectedFragment = new HomeFragment(); break;
            case R.id.page_2: selectedFragment = new FavouritesFragment(); break;
            case R.id.page_3: selectedFragment = new ChatFragment(); break;
            case R.id.page_4: selectedFragment = new CartFragment(); break;
            case R.id.page_5: selectedFragment = new ProfileFragment(); break;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,selectedFragment).commit();
        return true;
    };
}