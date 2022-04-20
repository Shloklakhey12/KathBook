package com.example.kathbook;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AssignmentsHolder extends RecyclerView.ViewHolder {
    public TextView mUsername, mUserStatus;
    public Button mDeleteRow;

    public AssignmentsHolder(View itemview) {
        super(itemview);
        mUsername = itemview.findViewById(R.id.mRowUserName);
        mUserStatus = itemview.findViewById(R.id.mRowUserStatus);
        //mDeleteRow = itemview.findViewById(R.id.mRowDeleteBtn);

    }
}
