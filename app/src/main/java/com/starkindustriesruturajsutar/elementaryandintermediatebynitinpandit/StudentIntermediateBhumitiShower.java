package com.starkindustriesruturajsutar.elementaryandintermediatebynitinpandit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class StudentIntermediateBhumitiShower extends AppCompatActivity {

    ImageView selectedImage;
    public String image_id;

    DatabaseReference mRef;
    private ProgressDialog mProgress;
    StorageReference storageReference;

    public String name;

    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(StudentIntermediateBhumitiShower.this , StudentIntermediateBhumitiList.class);
        intent1.putExtra("a",name);
        startActivity(intent1);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_intermediate_bhumiti_shower);

        selectedImage = (ImageView)findViewById(R.id.imageView);

        Intent intent = getIntent();
        image_id = intent.getStringExtra("a");
        name = intent.getStringExtra("b");

        mProgress=new ProgressDialog(this);
        storageReference = FirebaseStorage.getInstance().getReference();

        mRef= FirebaseDatabase.getInstance().getReference().child("Images").child("24").child(image_id);
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mProgress.setMessage("Downloading Image ....");
                mProgress.show();

                String filename = dataSnapshot.child("FileName").getValue().toString();

                final StorageReference image = storageReference.child("Images").child("24/"+filename);
                image.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(selectedImage);
                        selectedImage.setVisibility(View.VISIBLE);
                        mProgress.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(StudentIntermediateBhumitiShower.this, "Error in Loading Files", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
