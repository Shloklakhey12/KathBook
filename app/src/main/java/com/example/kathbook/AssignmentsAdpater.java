package com.example.kathbook;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class AssignmentsAdpater extends RecyclerView.Adapter<AssignmentsHolder> {
    Assignments assignments;
    ArrayList<AssignmentsUser> userArrayList;


    public AssignmentsAdpater(Assignments assignments, ArrayList<AssignmentsUser> userArrayList) {
        this.assignments = assignments;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public AssignmentsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(assignments.getBaseContext());
        View view = layoutInflater.inflate(R.layout.single_row, parent, false);
        return new AssignmentsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentsHolder holder, final int position) {
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

        assignments.db.collection("Assignment")
                .document(userArrayList.get(position).getUserId())
                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(assignments.getBaseContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                assignments.loadDataFromFirebase();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(assignments.getBaseContext(), "Unable To Delete --3--", Toast.LENGTH_SHORT).show();
                Log.w("--3--", e.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }
}
