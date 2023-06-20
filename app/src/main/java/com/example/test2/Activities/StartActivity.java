package com.example.test2.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.test2.Fragments.NutritionFragment;
import com.example.test2.Fragments.UserAddFragment;
import com.example.test2.Fragments.UserListFragment;
import com.example.test2.R;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class StartActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    NutritionFragment nutritionFragment = new NutritionFragment();
    UserAddFragment userAddFragment = new UserAddFragment();
    UserListFragment userListFragment = new UserListFragment();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.Zain, nutritionFragment).commit();
       /* BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.nutrition);
        badgeDrawable.setVisible(true);*/

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nutrition:
                        getSupportFragmentManager().beginTransaction().replace(R.id.Zain, nutritionFragment).commit();
                        return true;
                    case R.id.useradd:
                        getSupportFragmentManager().beginTransaction().replace(R.id.Zain, userAddFragment).commit();
                        return true;
                    case R.id.userlist:
                        getSupportFragmentManager().beginTransaction().replace(R.id.Zain, userListFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }
}