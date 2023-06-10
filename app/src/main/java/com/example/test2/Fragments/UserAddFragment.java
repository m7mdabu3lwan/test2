package com.example.test2.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.test2.Data.FirebaseServices;
import com.example.test2.R;
import com.example.test2.Activities.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserAddFragment extends Fragment {
    EditText firstname, lastname, weight;
    Button enter;
    FirebaseServices fbs;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserAddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserAdd.
     */
    // TODO: Rename and change types and number of parameters
    public static UserAddFragment newInstance(String param1, String param2) {
        UserAddFragment fragment = new UserAddFragment();
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
        return inflater.inflate(R.layout.fragment_user_add, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        firstname = getView().findViewById(R.id.etfirstname);
        lastname = getView().findViewById(R.id.etlastname);
        weight = getView().findViewById(R.id.etweight);
        enter = getView().findViewById(R.id.btnenter);
        fbs = FirebaseServices.getInstance();
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToFirestore();
            }
        });

    }

    private void addToFirestore() {
        String fname, lname;
        String weight1;
        String email;
        fname = firstname.getText().toString();
        lname = lastname.getText().toString();
        weight1 = weight.getText().toString();
        email = fbs.getAuth().getCurrentUser().getEmail();
        User user = new User(fname, lname, weight1, email);

        fbs.getFire().collection("Users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.framelayoutmain, new NutritionFragment());
                ft.commit();
            }
        }).addOnFailureListener(new OnFailureListener(){
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }

    private void gotonutritionfragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framelayoutmain, new NutritionFragment());
        ft.commit();
    }
}