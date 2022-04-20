package com.example.kathbook;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class Syllabus extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{
    Button l1,l2,l3,l4,l5,l6,l7,l8,r1,r2,r3,r4,r5,r6,r7,r8,s1,s2,s3,s4,s5,s6,s7,s8;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        grantper();
        l1 = findViewById(R.id.l1);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download1();
            }
        });
        l2 = findViewById(R.id.l2);
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download2();
            }
        });
        l3 = findViewById(R.id.l3);
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download3();
            }
        });
        l4 = findViewById(R.id.l4);
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download4();
            }
        });
        l5 = findViewById(R.id.l5);
        l5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download5();
            }
        });
        l6 = findViewById(R.id.l6);
        l6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download6();
            }
        });
        l7 = findViewById(R.id.l7);
        l7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download7();
            }
        });
        l8 = findViewById(R.id.l8);
        l8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download8();
            }
        });
        r1 = findViewById(R.id.r1);
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download9();
            }
        });
        r2 = findViewById(R.id.r2);
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download10();
            }
        });
        r3 = findViewById(R.id.r3);
        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download11();
            }
        });
        r4= findViewById(R.id.r4);
        r4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download12();
            }
        });
        r5 = findViewById(R.id.r5);
        r5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download13();
            }
        });
        r6 = findViewById(R.id.r6);
        r6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download14();
            }
        });
        r7 = findViewById(R.id.r7);
        r7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download15();
            }
        });
        r8 = findViewById(R.id.r8);
        r8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download16();
            }
        });
        s1 = findViewById(R.id.s1);
        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download17();
            }
        });
        s2 = findViewById(R.id.s2);
        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download18();
            }
        });
        s3 = findViewById(R.id.s3);
        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download19();
            }
        });
        s4= findViewById(R.id.s4);
        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download20();
            }
        });
        s5 = findViewById(R.id.s5);
        s5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download21();
            }
        });
        s6 = findViewById(R.id.s6);
        s6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download22();
            }
        });
        s7 = findViewById(R.id.s7);
        s7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download23();
            }
        });
        s8 = findViewById(R.id.s8);
        s8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download24();
            }
        });
    }

    public void download1() {
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("BCE_I_I.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFiles(Syllabus.this,"BCE_I_I",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public void download2() {
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("BCE_I_II.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFiles(Syllabus.this,"BCE_I_II",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public void download3() {
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("BCE_II_I.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFiles(Syllabus.this,"BCE_II_I",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public void download4() {
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("BCE_II_II.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFiles(Syllabus.this,"BCE_II_II",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public void download5() {
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("BCE_III_I.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFiles(Syllabus.this,"BCE_III_I",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public void download6() {
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("BCE_III_II.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFiles(Syllabus.this,"BCE_III_II",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public void download7() {
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("BCE_IV_I.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFiles(Syllabus.this,"BCE_IV_I",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public void download8() {
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("BCE_IV_II.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFiles(Syllabus.this,"BCE_IV_II",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public void download9() {
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("BCT_I_I.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFiles(Syllabus.this,"BCT_I_I",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public void download10() {
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("BCT_I_II.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFiles(Syllabus.this,"BCT_I_II",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public void download11() {
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("BCT_II_I.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFiles(Syllabus.this,"BCT_II_I",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public void download12() {
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("BCT_II_II.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFiles(Syllabus.this,"BCT_II_II",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public void download13() {
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("BCT_III_I.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFiles(Syllabus.this,"BCT_III_I",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public void download14() {
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("BCT_III_II.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFiles(Syllabus.this,"BCT_III_II",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public void download15() {
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("BCT_IV_I.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFiles(Syllabus.this,"BCT_IV_I",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public void download16() {
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("BCT_IV_II.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFiles(Syllabus.this,"BCT_IV_II",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public void download17() {
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("BEX_I_I.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFiles(Syllabus.this,"BEX_I_I",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public void download18() {
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("BEX_I_II.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFiles(Syllabus.this,"BEX_I_II",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public void download19() {
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("BEX_II_I.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFiles(Syllabus.this,"BEX_II_I",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public void download20() {
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("BEX_II_II.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFiles(Syllabus.this,"BEX_II_II",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public void download21() {
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("BEX_III_I.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFiles(Syllabus.this,"BEX_III_I",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public void download22() {
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("BEX_III_II.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFiles(Syllabus.this,"BEX_III_II",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public void download23() {
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("BEX_IV_I.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFiles(Syllabus.this,"BEX_IV_I",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    public void download24() {
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("BEX_IV_II.pdf");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFiles(Syllabus.this,"BEX_IV_II",".pdf",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void downloadFiles(Context context, String fileName, String fileExtension, String destinationDirectory, String url) {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory,fileName+fileExtension);
        downloadManager.enqueue(request);
    }

    @Override
    public void onBackPressed() {
        Intent searchIntent = new Intent(Syllabus.this, MainActivity.class);
        searchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(searchIntent);
    }
//storage permission grant
    @AfterPermissionGranted(123)
    private void grantper() {
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
            EasyPermissions.requestPermissions(this, "We need permissions to store the pdf files in your device",
                    123, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {

        }
    }
}


