package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseServices fbs =FirebaseServices.getInstance();
        if(fbs.getAuth().getCurrentUser()!=null){
            fbs.getFire().collection("Users").whereEqualTo("user", fbs.getAuth().getCurrentUser().getEmail())
                    .get()
                    .addOnSuccessListener((QuerySnapshot querySnapshot) -> {
                        if (querySnapshot.isEmpty()) {
                            System.out.println("No users found.");
                            FragmentTransaction ft =getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.framelayoutmain,new UserAdd());
                            ft.commit();
                            return;
                        }
                        System.out.println("Number of users: " + querySnapshot.size());
                        for (DocumentSnapshot doc : querySnapshot.getDocuments()) {
                            String userId = doc.getId();
                        }
                        Check();
                    })
                    .addOnFailureListener(e -> {
                        System.out.println("Error retrieving users: " + e.getMessage());
                    });
        }else {
            gotologinfragment();
        }
    }

    private void Check() {
        FragmentTransaction ft =getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framelayoutmain,new nutrition());
        ft.commit();
    }

    private void gotologinfragment()
    {
        FragmentTransaction ft =getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framelayoutmain,new loginFragment());
        ft.commit();
    }
}