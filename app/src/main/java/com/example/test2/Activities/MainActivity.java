package com.example.test2.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.test2.Data.FirebaseServices;
import com.example.test2.Fragments.HomeFragment;
import com.example.test2.Fragments.NutritionFragment;
import com.example.test2.Fragments.UserAddFragment;
import com.example.test2.Fragments.UserListFragment;
import com.example.test2.Fragments.loginFragment;
import com.example.test2.R;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {
    FirebaseServices fbs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fbs=FirebaseServices.getInstance();
        if (fbs.getAuth().getCurrentUser()!=null){

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, new HomeFragment());
            ft.commit();
        }
        else gotologinfragment();
    }



    private void gotologinfragment()
    {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, new loginFragment());
        ft.commit();
    }
}