package com.example.test2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context Context;
    ArrayList<User>userArrayList;

    public MyAdapter(android.content.Context context, ArrayList<User> userArrayList) {
        Context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView firstName,lastName,age;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            //TODO: firstName=itemView.findViewById(R.id.)
        }
    }
}
