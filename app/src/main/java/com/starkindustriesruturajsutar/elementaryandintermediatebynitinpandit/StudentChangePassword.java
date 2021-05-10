package com.starkindustriesruturajsutar.elementaryandintermediatebynitinpandit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class StudentChangePassword extends AppCompatActivity {


    EditText oldPassword;
    Button changePassword;
    CheckBox showpassword;
    EditText newpassword;
    EditText newpasswordagain;
    DatabaseReference mRef1,mRef2;
    DatabaseReference mRef;
    LottieAnimationView lottieAnimationView;
    String mymobile;
    public String opass,ostandard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_change_password);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Intent intent = getIntent();
        mymobile = intent.getStringExtra("a");

        oldPassword=(EditText)findViewById(R.id.editText1);
        newpassword=(EditText)findViewById(R.id.editText2);
        newpasswordagain=(EditText)findViewById(R.id.editText3);

        lottieAnimationView=(LottieAnimationView)findViewById(R.id.lottie);

        showpassword=(CheckBox)findViewById(R.id.showpassword);
        showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    oldPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    newpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    newpasswordagain.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    oldPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    newpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    newpasswordagain.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });





        changePassword=(Button)findViewById(R.id.button2);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lottieAnimationView.setVisibility(View.VISIBLE);
                final String oldpass=oldPassword.getText().toString();
                final String newpass=newpassword.getText().toString();
                String newpassagain=newpasswordagain.getText().toString();

                if (oldpass.isEmpty()){
                    oldPassword.setError("Please Enter Old Password");
                    oldPassword.requestFocus();
                    lottieAnimationView.setVisibility(View.INVISIBLE);
                    return;
                }
                else if (newpass.isEmpty()){
                    newpassword.setError("Please Enter New Password");
                    newpassword.requestFocus();
                    lottieAnimationView.setVisibility(View.INVISIBLE);
                    return;
                }
                else if (newpassagain.isEmpty()){
                    newpasswordagain.setError("Please Enter New Password Again");
                    newpasswordagain.requestFocus();
                    lottieAnimationView.setVisibility(View.INVISIBLE);
                    return;
                }
                else if(!(newpass.equals(newpassagain))){
                    newpassword.setError("Both Passwords must be Same");
                    newpassword.requestFocus();
                    lottieAnimationView.setVisibility(View.INVISIBLE);
                    return;
                }
                else if (newpass.equals(newpassagain)){


                    mRef1= FirebaseDatabase.getInstance().getReference().child("All_Student").child(mymobile);
                    mRef1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            opass=dataSnapshot.child("Password").getValue().toString();
                            ostandard=dataSnapshot.child("Standard").getValue().toString();

                            if (oldpass.equals(opass)){

                                mRef= FirebaseDatabase.getInstance().getReference().child("All_Student").child(mymobile);
                                Map<String,Object> updateValue=new HashMap<>();
                                updateValue.put("/Password",newpass);
                                mRef.updateChildren(updateValue);

                                mRef2= FirebaseDatabase.getInstance().getReference().child("Student").child(ostandard).child(mymobile);
                                Map<String,Object> updateValue1=new HashMap<>();
                                updateValue1.put("/Password",newpass);
                                mRef2.updateChildren(updateValue1);

                                Intent intent=new Intent(StudentChangePassword.this,StudentDashboard.class);
                                lottieAnimationView.setVisibility(View.INVISIBLE);
                                intent.putExtra("a",mymobile);
                                startActivity(intent);
                                finish();
                                Toast.makeText(StudentChangePassword.this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();

                            }
                            else {
                                oldPassword.setError("Please Enter Correct Old Password");
                                oldPassword.requestFocus();
                                lottieAnimationView.setVisibility(View.INVISIBLE);
                                return;
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                            Toast.makeText(StudentChangePassword.this, "Error in Changing Password", Toast.LENGTH_SHORT).show();
                            lottieAnimationView.setVisibility(View.INVISIBLE);
                        }
                    });


                }
                else {
                    Toast.makeText(StudentChangePassword.this, "Cannot change Password", Toast.LENGTH_SHORT).show();
                    lottieAnimationView.setVisibility(View.INVISIBLE);
                }


            }
        });




    }

    @Override
    public void onBackPressed() {
        Intent intent111 = new Intent(StudentChangePassword.this , StudentDashboard.class);
        intent111.putExtra("a",mymobile);
        startActivity(intent111);
        finish();
    }
}
