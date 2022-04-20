package com.example.kathbook;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackDialog extends AppCompatDialogFragment {
    EditText editText;
    DatabaseReference db;
    FirebaseAuth firebaseAuth;
    RatingBar rb;
    TextView value;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getCurrentUser().getUid());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.layout_feedback, null);
        builder.setView(view)
                .setTitle("Rate us")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        float rate = rb.getRating();
                        db.child("Rate").setValue(rate);
                        final String feeds = editText.getText().toString();
                        db.child("Feedback").setValue(feeds);
                        Toast.makeText(getContext(), "Sent", Toast.LENGTH_SHORT).show();

                    }
                });
        editText = view.findViewById(R.id.feedback);
        rb = view.findViewById(R.id.ratingbar);
        value = view.findViewById(R.id.tvRatingScale);
        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        value.setText("Very bad");
                        break;
                    case 2:
                        value.setText("Need some improvement");
                        break;
                    case 3:
                        value.setText("Good");
                        break;
                    case 4:
                        value.setText("Great");
                        break;
                    case 5:
                        value.setText("Awesome.I love it");
                        break;
                    default:
                        value.setText("");
                }
            }
        });
        return builder.create();

    }
}