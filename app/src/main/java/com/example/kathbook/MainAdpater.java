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

public class MainAdpater extends RecyclerView.Adapter<MainHolder> {
    MainActivity mainActivity;
    ArrayList<MainUser> userArrayList;


    public MainAdpater(MainActivity mainActivity, ArrayList<MainUser> userArrayList) {
        this.mainActivity = mainActivity;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mainActivity.getBaseContext());
        View view = layoutInflater.inflate(R.layout.single_row, parent, false);
        return new MainHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder holder, final int position) {
        holder.mUsername.setText(userArrayList.get(position).getTitle());
        holder.mUserStatus.setText(userArrayList.get(position).getDescription());
        holder.mDeleteRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                deleteSelectedRow(position);
                return false;
            }
        });
    }

    private void deleteSelectedRow(int position) {

        mainActivity.db.collection("Notices")
                .document(userArrayList.get(position).getUserId())
                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(mainActivity.getBaseContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                mainActivity.loadDataFromFirebase();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(mainActivity.getBaseContext(), "Unable To Delete --3--", Toast.LENGTH_SHORT).show();
                Log.w("--3--", e.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }
}
