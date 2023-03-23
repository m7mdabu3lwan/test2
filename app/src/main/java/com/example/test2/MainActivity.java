package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gotologinfragment();
    }
    private void gotologinfragment()
    {
        FragmentTransaction ft =getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framelayoutmain,new loginFragment());
        ft.commit();
    }
}