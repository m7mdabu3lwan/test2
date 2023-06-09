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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.test2.Data.FirebaseServices;
import com.example.test2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link signupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class signupFragment extends Fragment {
    private EditText etusername , etpassword;
    private Button btnsignup;
    private ImageView imgb;
    private FirebaseServices fbs;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public signupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment signupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static signupFragment newInstance(String param1, String param2) {
        signupFragment fragment = new signupFragment();
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
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        fbs = FirebaseServices.getInstance();
        etusername= getView().findViewById(R.id.etusernamesignup);
        etpassword=getView().findViewById(R.id.etpasswordsignup);
        imgb=getView().findViewById(R.id.imgback2);
        imgb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {gotologinfragment();}
        });
        btnsignup=getView().findViewById(R.id.btnsignupsignup);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=etusername.getText().toString();
                String password=etpassword.getText().toString();
                if(username.trim().isEmpty()&&password.trim().isEmpty())
                {
                    Toast.makeText(getActivity(), "some fields are empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                fbs.getAuth().createUserWithEmailAndPassword(username,password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "you have successfully signed up", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            task.getException().getMessage();
                            Toast.makeText(getActivity(), "field to signup", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });

    }
    private void gotologinfragment()
    {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, new loginFragment());
        ft.commit();
    }
}