package com.example.kathbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class FPass extends AppCompatActivity implements View.OnClickListener {
    EditText userEmail;
    FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fpass);
        userEmail = findViewById(R.id.editTextsendEmail);
        findViewById(R.id.buttonResetPass).setOnClickListener(this);
        getWindow().setBackgroundDrawableResource(R.drawable.back);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar);
    }

    @Override
    public void onBackPressed() {
        Intent searchIntent = new Intent(FPass.this, Login_Form.class);
        searchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(searchIntent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonResetPass:
                final String Email = userEmail.getText().toString();
                if (Email.isEmpty()) {
                    userEmail.setError("Email is required");
                    userEmail.requestFocus();
                    return;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                    userEmail.setError("Please Enter a Valid Email");
                    userEmail.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                mAuth.sendPasswordResetEmail(userEmail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Reset Link Sent to your Email", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;
        }
    }
}