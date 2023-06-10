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
import android.widget.TextView;
import android.widget.Toast;

import com.example.test2.Data.FirebaseServices;
import com.example.test2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link loginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class loginFragment extends Fragment {
    private EditText etusername , etpassword;
    private TextView tvsignuplink;
    private Button btnlogin;
    private FirebaseServices fbs;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public loginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment loginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static loginFragment newInstance(String param1, String param2) {
        loginFragment fragment = new loginFragment();
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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();
        fbs = FirebaseServices.getInstance();
        etusername = getView().findViewById(R.id.etusernamelogin);
        etpassword = getView().findViewById(R.id.etpasswordlogin);
        btnlogin = getView().findViewById(R.id.btnloginlogin);
        tvsignuplink = getView().findViewById(R.id.tvsignuplinklogin);
        tvsignuplink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotosignupfragment();
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                String username = etusername.getText().toString();
                String password = etpassword.getText().toString();
                if (username.trim().isEmpty() && password.trim().isEmpty()) {
                    Toast.makeText(getActivity(), "some fields are empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                fbs.getAuth().signInWithEmailAndPassword(username, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // TODO: goto user list
                            FragmentTransaction ft =getActivity().getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.framelayoutmain,new UserAddFragment());
                            ft.commit();

                        } else {

                            Toast.makeText(getActivity(), "field to login", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }


    private void gotosignupfragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framelayoutmain, new signupFragment());
        ft.commit();
    }
}
