package com.starkindustriesruturajsutar.elementaryandintermediatebynitinpandit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentLoginLoadingPage extends AppCompatActivity {

    DatabaseReference mRef;
    String mymobile,mypassword;


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(StudentLoginLoadingPage.this);

        builder.setMessage("Wait while the operation is Done ....")
                .setCancelable(false)
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login_loading_page);

        final Intent intent = getIntent();
        mymobile=intent.getStringExtra("Mobile");
        mypassword=intent.getStringExtra("Password");

        mRef= FirebaseDatabase.getInstance().getReference().child("All_Student").child(mymobile);


        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                try {
                    String mobile=dataSnapshot.child("Mobile").getValue().toString();
                    String pass=dataSnapshot.child("Password").getValue().toString();


                    if (mymobile.equals(mobile) && mypassword.equals(pass)){
                        User user=new User(StudentLoginLoadingPage.this);
                        user.setName(mymobile);
                        Intent intent=new Intent(StudentLoginLoadingPage.this,StudentDashboard.class);
                        intent.putExtra("a",mymobile);
                        startActivity(intent);
                        finish();
                        Toast.makeText(StudentLoginLoadingPage.this, "Login Successful", Toast.LENGTH_LONG).show();


                    }
                    else{
                        Toast.makeText(StudentLoginLoadingPage.this, "Please Enter Valid Mobile Number or Password", Toast.LENGTH_LONG).show();
                        Intent intent1=new Intent(StudentLoginLoadingPage.this,StudentLoginPage.class);
                        startActivity(intent1);
                        finish();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(StudentLoginLoadingPage.this, "Please Enter Valid Mobile Number And Password", Toast.LENGTH_LONG).show();
                    Intent intent2=new Intent(StudentLoginLoadingPage.this,StudentLoginPage.class);
                    startActivity(intent2);
                    finish();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(StudentLoginLoadingPage.this, "Check Mobile or Password", Toast.LENGTH_LONG).show();
                Intent intent3=new Intent(StudentLoginLoadingPage.this,StudentLoginPage.class);
                startActivity(intent3);
                finish();
            }
        });

    }
}
