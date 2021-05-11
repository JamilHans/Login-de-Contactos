package com.example.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class UserAdapter extends FirebaseRecyclerAdapter<User, UserAdapter.UserViewholder> {

    public UserAdapter(@NonNull FirebaseRecyclerOptions<User> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull UserViewholder holder, int position, @NonNull User model) {
        holder.firstname.setText(model.getNombre());
        holder.lastname.setText(model.getNumero());
    }

    @NonNull
    @Override
    public UserViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new  UserAdapter.UserViewholder(view);
    }

     class UserViewholder extends RecyclerView.ViewHolder {
        TextView firstname, lastname;
         public UserViewholder(@NonNull View itemView) {
             super(itemView);
             firstname = itemView.findViewById(R.id.firstname);
             lastname = itemView.findViewById(R.id.lastname);
         }
     }
}
