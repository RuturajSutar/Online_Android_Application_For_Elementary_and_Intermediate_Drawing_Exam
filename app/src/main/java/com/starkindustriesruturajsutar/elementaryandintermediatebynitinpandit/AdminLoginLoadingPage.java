package com.starkindustriesruturajsutar.elementaryandintermediatebynitinpandit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminLoginLoadingPage extends AppCompatActivity {


    DatabaseReference mRef;
    FirebaseAuth mFirebaseAuth;
    String myemail,mypassword,myremoveat;

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AdminLoginLoadingPage.this);

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
        setContentView(R.layout.activity_admin_login_loading_page);


        final Intent intent = getIntent();
        myemail=intent.getStringExtra("Email");
        mypassword=intent.getStringExtra("Password");
        myremoveat=intent.getStringExtra("removeat");



        mFirebaseAuth=FirebaseAuth.getInstance();

        mFirebaseAuth.signInWithEmailAndPassword(myemail,mypassword).addOnCompleteListener(AdminLoginLoadingPage.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){



                    mRef= FirebaseDatabase.getInstance().getReference().child("Admin").child(myremoveat);
                    mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                            try {
                                String mail=dataSnapshot.child("Email").getValue().toString();
                                String pass=dataSnapshot.child("Password").getValue().toString();


                                if (myemail.equals(mail) && mypassword.equals(pass)){
                                    Admin admin = new Admin(AdminLoginLoadingPage.this);
                                    admin.setAname(myemail);
                                    Intent intent=new Intent(AdminLoginLoadingPage.this,AdminDashboard.class);
                                    intent.putExtra("a",myemail);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(AdminLoginLoadingPage.this, "Login Successful", Toast.LENGTH_LONG).show();


                                }
                                else{
                                    Toast.makeText(AdminLoginLoadingPage.this, "Please Enter Valid Email or Password", Toast.LENGTH_LONG).show();
                                    Intent intent1=new Intent(AdminLoginLoadingPage.this,AdminLoginPage.class);
                                    startActivity(intent1);
                                    finish();
                                }
                            }
                            catch (Exception e){
                                e.printStackTrace();
                                Toast.makeText(AdminLoginLoadingPage.this, "Please Enter Valid Email Address And Password", Toast.LENGTH_LONG).show();
                                Intent intent2=new Intent(AdminLoginLoadingPage.this,AdminLoginPage.class);
                                startActivity(intent2);
                                finish();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(AdminLoginLoadingPage.this, "Check Email or Password", Toast.LENGTH_LONG).show();
                            Intent intent3=new Intent(AdminLoginLoadingPage.this,AdminLoginPage.class);
                            startActivity(intent3);
                            finish();
                        }
                    });



                }
                else{
                    Toast.makeText(AdminLoginLoadingPage.this, "Authentication Failed, Please Check Your Email and Password", Toast.LENGTH_LONG).show();
                    Intent intent5=new Intent(AdminLoginLoadingPage.this,AdminLoginPage.class);
                    startActivity(intent5);
                    finish();
                }

            }
        });



    }
}
