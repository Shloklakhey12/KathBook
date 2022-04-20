package com.example.kathbook;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class QueriesAdpater extends RecyclerView.Adapter<QueriesHolder> {
    Queries queries;
    ArrayList<QueriesUser> userArrayList;
    public QueriesAdpater(Queries queries, ArrayList<QueriesUser> userArrayList) {
        this.queries = queries;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public QueriesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(queries.getBaseContext());
        View view = layoutInflater.inflate(R.layout.queryrview, parent, false);
        return new QueriesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QueriesHolder holder, final int position) {
        holder.muname.setText(userArrayList.get(position).getUsername());
        holder.mUsername.setText(userArrayList.get(position).getTitle());
        holder.mUserStatus.setText(userArrayList.get(position).getDescription());
        /*holder.mDeleteRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                deleteSelectedRow(position);
                return false;
            }
        });*/
    }

    private void deleteSelectedRow(int position) {
        queries.db.collection("Queries").document(userArrayList.get(position).getUserId())
                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(queries.getBaseContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                queries.loadDataFromFirebase();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(queries.getBaseContext(), "Unable To Delete --3--", Toast.LENGTH_SHORT).show();
                Log.w("--3--", e.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }
}
