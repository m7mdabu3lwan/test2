package com.example.test2.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.test2.Data.FirebaseServices;
import com.example.test2.R;
import com.example.test2.Activities.User;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NutritionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NutritionFragment extends Fragment {

    TextView protein,carbohydrate,calories;
    Spinner bulkorcut;
    FirebaseServices fbs;
    User user;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public NutritionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        fbs=FirebaseServices.getInstance();
        fbs.getFire().collection("Users").whereEqualTo("user", fbs.getAuth().getCurrentUser().getEmail())
                .get()
                .addOnSuccessListener((QuerySnapshot querySnapshot) -> {
                    if (querySnapshot.isEmpty()) {
                        System.out.println("No users found.");
                        return;
                    }

                    System.out.println("Number of users: " + querySnapshot.size());

                    for (DocumentSnapshot doc : querySnapshot.getDocuments()) {
                        String userId = doc.getId();
                        user = doc.toObject(User.class);
                    }
                })
                .addOnFailureListener(e -> {
                    System.out.println("Error retrieving users: " + e.getMessage());
                });
        calories = getView().findViewById(R.id.tvcalories);
        protein = getView().findViewById(R.id.tvprotein);
        carbohydrate = getView().findViewById(R.id.tvcarbohydrate);
        bulkorcut = getView().findViewById(R.id.spbulkorcut);
        bulkorcut.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(bulkorcut.getSelectedItem().toString().equals("cut")){
                    calories.setText(""+2*18*Double.parseDouble(user.getW()));
                    protein.setText(""+2*Double.parseDouble(user.getW()));
                    carbohydrate.setText(""+3*Double.parseDouble(user.getW()));
                }else {
                    calories.setText(""+40*Double.parseDouble(user.getW()));
                    protein.setText(""+2*Double.parseDouble(user.getW()));
                    carbohydrate.setText(""+5*Double.parseDouble(user.getW()));
                }
            }
        });
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment nutrition.
     */
    // TODO: Rename and change types and number of parameters
    public static NutritionFragment newInstance(String param1, String param2) {
        NutritionFragment fragment = new NutritionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nutrition, container, false);
    }
}