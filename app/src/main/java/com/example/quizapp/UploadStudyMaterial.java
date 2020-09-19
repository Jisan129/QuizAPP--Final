package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadStudyMaterial extends AppCompatActivity {
    private static final int PICK_IMAGE = 1;
    private static final String TAG = "Tag";
    Button uploadbtn,uploadFinal;
    TextView uploadText;
    private Uri imageUri;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Uploads");
    StorageReference storageReference= FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_study_material);
        uploadbtn=findViewById(R.id.upload);
        uploadText=findViewById(R.id.UploadMaterial);
        uploadFinal=findViewById(R.id.goBackbtn);

        uploadFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //UploadFile();
            }
        });

        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFile();
               // getFileExtension();

            }
        });
}

    private void selectFile() {
        Intent intent =new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PICK_IMAGE:
                if (resultCode == RESULT_OK) {
                    assert data != null;

                    String path = data.getData().getPath();
                    uploadText.setText(path);
                    imageUri=data.getData();
                }
                break;
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver cr=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        uploadText.setText(mimeTypeMap.getExtensionFromMimeType(cr.getType(uri)));
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    private void UploadFile(){
        if (imageUri != null)
        {

            storageReference.child("New folder").child(uploadText.getText().toString()).putFile(imageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>()


            {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                {
                    if (!task.isSuccessful())
                    {
                        throw task.getException();
                    }
                    return storageReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>()
            {
                @Override
                public void onComplete(@NonNull Task<Uri> task)
                {
                    if (task.isSuccessful())
                    {
                        Uri downloadUri = task.getResult();
                        Log.e(TAG, "then: " + downloadUri.toString());


                        Upload upload = new Upload(uploadText.getText().toString().trim(),
                                downloadUri.toString());
                        databaseReference.push().setValue(upload);
                    } else
                    {
                        Toast.makeText(UploadStudyMaterial.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else {
            Toast.makeText(this, "No URI", Toast.LENGTH_SHORT).show();
        }
    }
}
