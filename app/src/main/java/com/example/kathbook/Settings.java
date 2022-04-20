package com.example.kathbook;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Settings extends AppCompatActivity implements View.OnClickListener {
    FirebaseAuth mAuth;
    EditText editTextName, editTextPassword;
    TextView textView, textViewemail;
    DatabaseReference db;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViewById(R.id.updatename).setOnClickListener(this);
        findViewById(R.id.updatepassword).setOnClickListener(this);
        findViewById(R.id.deleteaccount).setOnClickListener(this);
        textView = findViewById(R.id.verify);
        textViewemail = findViewById(R.id.textviewemail);
        editTextName = findViewById(R.id.editName);
        editTextPassword = findViewById(R.id.editPassword);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar);
        loadUserInformation();
        verify();
        db = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid());
    }

    private void verify() {
        final FirebaseUser user = mAuth.getCurrentUser();
        if (user.isEmailVerified()) {
            textView.setText(" ✔️ ");
        } else {
            textView.setText(" ✘ ");
        }
    }

    private void loadUserInformation() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user.getDisplayName() != null) {
            editTextName.setText(user.getDisplayName());
        }
        if (user.getEmail() != null) {
            textViewemail.setText(user.getEmail());
        }

    }

    // Action On BackPress Button
    @Override
    public void onBackPressed() {
        Intent searchIntent = new Intent(Settings.this, MainActivity.class);
        searchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(searchIntent);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.updatename:
                updateUserName();
                break;
            case R.id.updatepassword:
                updateUserPassword();
                break;
            case R.id.deleteaccount:
                deleteaccount();
                break;

        }
    }

    private void deleteaccount() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(Settings.this);
        dialog.setTitle("Are you sure?");
        dialog.setMessage("Deleting this account will result in completely removing your account from the system and you won't be able to access the app.");
        dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressBar.setVisibility(View.VISIBLE);
                FirebaseUser user = mAuth.getCurrentUser();
                user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(Settings.this, "Account Deleted", Toast.LENGTH_SHORT).show();
                            finish();
                            Intent intent = new Intent(Settings.this, Login_Form.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        dialog.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();

    }


    private void updateUserName() {
        final String Name = editTextName.getText().toString();
        if (Name.isEmpty()) {
            editTextName.setError("Name is required");
            editTextName.requestFocus();
            return;
        }

        FirebaseUser user = mAuth.getCurrentUser();
        UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder().setDisplayName(Name).build();
        progressBar.setVisibility(View.VISIBLE);
        user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    db.child("Username").setValue(Name);
                    Toast.makeText(Settings.this, "Name Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void updateUserPassword() {

        String Password = editTextPassword.getText().toString();
        if (Password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;

        }
        if (Password.length() < 6) {
            editTextPassword.setError("Minimum length of password should be 6");
            editTextPassword.requestFocus();
            return;
        }
        FirebaseUser user = mAuth.getCurrentUser();
        progressBar.setVisibility(View.VISIBLE);
        user.updatePassword(Password).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Toast.makeText(Settings.this, "Password Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}

