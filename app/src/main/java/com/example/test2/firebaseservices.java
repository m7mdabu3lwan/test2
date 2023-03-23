package com.example.test2;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public class firebaseservices
{
    public static firebaseservices instance;

    public FirebaseAuth getAuth() {
        return Auth;
    }

    public FirebaseFirestore getFire() {
        return fire;
    }

    public FirebaseStorage getStorage() {
        return storage;
    }

    private FirebaseAuth Auth;
    private FirebaseFirestore fire;
    private FirebaseStorage storage;

    public static firebaseservices getInstance()
    {
        if (instance==null)
        {
            instance=new firebaseservices();
        }
        return instance;
    }
}
