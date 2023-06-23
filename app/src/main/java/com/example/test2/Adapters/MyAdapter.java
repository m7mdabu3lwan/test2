package com.example.test2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test2.R;
import com.example.test2.Activities.User;

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
        View v = LayoutInflater.from(Context).inflate(R.layout.item,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        User user=userArrayList.get(position);
        holder.firstName.setText(user.getFirstname());
        holder.lastName.setText(user.getLastname());
        holder.weight.setText(user.getW());
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView firstName,lastName,weight;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            firstName=itemView.findViewById(R.id.tvfirstname);
            lastName=itemView.findViewById(R.id.tvlastname);
            weight=itemView.findViewById(R.id.tvweight);
        }
    }
}
