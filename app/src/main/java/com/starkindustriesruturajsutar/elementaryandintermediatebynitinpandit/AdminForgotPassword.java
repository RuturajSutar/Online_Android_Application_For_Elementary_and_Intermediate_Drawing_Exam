package com.starkindustriesruturajsutar.elementaryandintermediatebynitinpandit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminForgotPassword extends AppCompatActivity {

    Button number;
    EditText email;
    DatabaseReference mRef;
    LottieAnimationView lottieAnimationView;
    public String removedot , removeat;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AdminForgotPassword.this , AdminLoginPage.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_forgot_password);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        lottieAnimationView=(LottieAnimationView)findViewById(R.id.lottie);
        email=(EditText)findViewById(R.id.editText);
        number=(Button)findViewById(R.id.button2);

        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lottieAnimationView.setVisibility(View.VISIBLE);
                final String myemail=email.getText().toString();

                removedot = myemail.replace("." , "");
                removeat = removedot.replace("@" , "");

                if (myemail.isEmpty()){
                    email.setError("Please Enter Email Address");
                    email.requestFocus();
                    lottieAnimationView.setVisibility(View.INVISIBLE);
                    return;
                }

                else if (!(myemail.isEmpty())){



                    mRef= FirebaseDatabase.getInstance().getReference().child("Admin").child(removeat);
                    mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                            try {
                                String mob=dataSnapshot.child("Mobile").getValue().toString();
                                String pass=dataSnapshot.child("Password").getValue().toString();

                                Intent intent=new Intent(AdminForgotPassword.this,AdminFP_OTP.class);
                                intent.putExtra("a",mob);
                                intent.putExtra("b",myemail);
                                intent.putExtra("c",pass);
                                startActivity(intent);
                                lottieAnimationView.setVisibility(View.INVISIBLE);
                                finish();
                                Toast.makeText(AdminForgotPassword.this, "OTP is sent to your registered mobile number", Toast.LENGTH_LONG).show();

                            }
                            catch (Exception e){
                                e.printStackTrace();
                                Toast.makeText(AdminForgotPassword.this, "Please Enter Registered Email Address", Toast.LENGTH_LONG).show();
                                lottieAnimationView.setVisibility(View.INVISIBLE);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(AdminForgotPassword.this, "Please Enter Registered Email Address", Toast.LENGTH_LONG).show();
                            lottieAnimationView.setVisibility(View.INVISIBLE);
                        }
                    });



                }
                else {
                    Toast.makeText(AdminForgotPassword.this, "Please Enter Registered Email Address", Toast.LENGTH_SHORT).show();
                    lottieAnimationView.setVisibility(View.INVISIBLE);
                }

            }
        });

    }
}
