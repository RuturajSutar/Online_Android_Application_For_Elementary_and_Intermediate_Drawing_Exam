package com.starkindustriesruturajsutar.elementaryandintermediatebynitinpandit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class StudentRegistrationLoadingPage extends AppCompatActivity {

    DatabaseReference mRef , mRef1;
    String myname,mymobile,mystandard,mypassword;

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(StudentRegistrationLoadingPage.this);

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
        setContentView(R.layout.activity_student_registration_loading_page);

        final Intent intent = getIntent();
        myname=intent.getStringExtra("a");
        mymobile=intent.getStringExtra("b");
        mystandard=intent.getStringExtra("c");
        mypassword=intent.getStringExtra("d");

        mRef= FirebaseDatabase.getInstance().getReference().child("Student").child(mystandard).child(mymobile);
        Map<String,Object> insertValues=new HashMap<>();

        insertValues.put("Name",myname);
        insertValues.put("Mobile",mymobile);
        insertValues.put("Standard",mystandard);
        insertValues.put("Password",mypassword);
        mRef.setValue(insertValues);

        mRef= FirebaseDatabase.getInstance().getReference().child("All_Student").child(mymobile);
        Map<String,Object> insertValues1=new HashMap<>();

        insertValues1.put("Name",myname);
        insertValues1.put("Mobile",mymobile);
        insertValues1.put("Standard",mystandard);
        insertValues1.put("Password",mypassword);
        mRef.setValue(insertValues1);

        Intent intent2 = new Intent(StudentRegistrationLoadingPage.this , StudentLoginPage.class);
        startActivity(intent2);
        finish();
        Toast.makeText(this, "Registration Successful", Toast.LENGTH_LONG).show();

    }
}
