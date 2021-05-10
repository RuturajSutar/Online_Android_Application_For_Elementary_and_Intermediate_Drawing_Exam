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

public class StudentForgotPassword extends AppCompatActivity {

    Button number;
    EditText mobile;
    DatabaseReference mRef;
    LottieAnimationView lottieAnimationView;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(StudentForgotPassword.this , StudentLoginPage.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_forgot_password);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        lottieAnimationView=(LottieAnimationView)findViewById(R.id.lottie);
        mobile=(EditText)findViewById(R.id.editText);
        number=(Button)findViewById(R.id.button2);

        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                lottieAnimationView.setVisibility(View.VISIBLE);
                String mymobile=mobile.getText().toString();

                if (mymobile.isEmpty()){
                    mobile.setError("Please Enter Mobile Number");
                    mobile.requestFocus();
                    lottieAnimationView.setVisibility(View.INVISIBLE);
                    return;
                }
                else if (mymobile.length() != 10){
                    mobile.setError("Mobile number must contains 10 numbers");
                    mobile.requestFocus();
                    lottieAnimationView.setVisibility(View.INVISIBLE);
                    return;
                }
                else if (!(mymobile.isEmpty())){



                    mRef= FirebaseDatabase.getInstance().getReference().child("All_Student").child(mymobile);
                    mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                            try {
                                String mob=dataSnapshot.child("Mobile").getValue().toString();
                                String std=dataSnapshot.child("Standard").getValue().toString();
                                Intent intent=new Intent(StudentForgotPassword.this,StudentFP_OTP.class);
                                intent.putExtra("a",mob);
                                intent.putExtra("b",std);
                                startActivity(intent);
                                lottieAnimationView.setVisibility(View.INVISIBLE);
                                finish();
                                Toast.makeText(StudentForgotPassword.this, "Correct phone number", Toast.LENGTH_LONG).show();

                            }
                            catch (Exception e){
                                e.printStackTrace();
                                Toast.makeText(StudentForgotPassword.this, "Please Enter Registered Mobile Number", Toast.LENGTH_LONG).show();
                                lottieAnimationView.setVisibility(View.INVISIBLE);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(StudentForgotPassword.this, "Please Enter Registered Mobile Number", Toast.LENGTH_LONG).show();
                            lottieAnimationView.setVisibility(View.INVISIBLE);
                        }
                    });



                }
                else {
                    Toast.makeText(StudentForgotPassword.this, "Please Enter Registered Mobile Number", Toast.LENGTH_SHORT).show();
                    lottieAnimationView.setVisibility(View.INVISIBLE);
                }


            }
        });
    }
}
