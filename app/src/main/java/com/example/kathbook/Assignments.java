package com.example.kathbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Assignments extends AppCompatActivity {
    private static final String TAG = "Assignments";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    RecyclerView mRecyclerView;
    ArrayList<AssignmentsUser> userArrayList;
    AssignmentsAdpater adpater;
    private CollectionReference AssignRef = db.collection("Assignment");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        userArrayList = new ArrayList<>();
        setUpRecyclerView();
        /*FloatingActionButton button = findViewById(R.id.button_add_note);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Assignments.this, AddAssignment.class));
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.assignments_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.refreshbtn) {
            loadDataFromFirebase();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (userArrayList.size() > 0)
            userArrayList.clear();
        AssignRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot querySnapshot : queryDocumentSnapshots) {
                    {
                        AssignmentsUser user = new AssignmentsUser(
                                querySnapshot.getId(),
                                querySnapshot.getString("title"),
                                querySnapshot.getString("description"));
                        userArrayList.add(user);
                    }
                }
                adpater = new AssignmentsAdpater(Assignments.this, userArrayList);
                mRecyclerView.setAdapter(adpater);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Assignments.this, "Error!", Toast.LENGTH_SHORT).show();
                Log.d(TAG, e.toString());
            }
        });
    }

    public void loadDataFromFirebase() {
        if (userArrayList.size() > 0)
            userArrayList.clear();
        AssignRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for (QueryDocumentSnapshot querySnapshot : queryDocumentSnapshots) {
                    {
                        AssignmentsUser user = new AssignmentsUser(
                                querySnapshot.getId(),
                                querySnapshot.getString("title"),
                                querySnapshot.getString("description"));
                        userArrayList.add(user);
                    }
                }
                adpater = new AssignmentsAdpater(Assignments.this, userArrayList);
                mRecyclerView.setAdapter(adpater);
                Toast.makeText(Assignments.this, "Refreshed", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Assignments.this, "Error!", Toast.LENGTH_SHORT).show();
                Log.d(TAG, e.toString());
            }
        });
    }

    private void setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Action On BackPress Button
    @Override
    public void onBackPressed() {
        Intent searchIntent = new Intent(Assignments.this, MainActivity.class);
        searchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(searchIntent);
    }
}
