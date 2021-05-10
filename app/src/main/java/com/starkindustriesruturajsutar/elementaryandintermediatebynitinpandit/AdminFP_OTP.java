package com.starkindustriesruturajsutar.elementaryandintermediatebynitinpandit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;

import java.util.concurrent.TimeUnit;

public class AdminFP_OTP extends AppCompatActivity {

    Button verify;
    private String verificationId;
    EditText otp;
    LottieAnimationView lottieAnimationView;
    FirebaseAuth firebaseAuth;
    DatabaseReference mRef;
    String mymobile,phone,myemail,mypass;

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AdminFP_OTP.this);

        builder.setMessage("Are you sure to cancel this operation..?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(AdminFP_OTP.this,AdminLoginPage.class);
                        startActivity(intent);
                        finish();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
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
        setContentView(R.layout.activity_admin_fp__otp);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        firebaseAuth=FirebaseAuth.getInstance();

        Intent intent = getIntent();
        mymobile=intent.getStringExtra("a");
        myemail=intent.getStringExtra("b");
        mypass=intent.getStringExtra("c");
        phone="+91"+mymobile;

        lottieAnimationView=(LottieAnimationView)findViewById(R.id.lottie);
        otp=(EditText)findViewById(R.id.editText);
        verify=(Button)findViewById(R.id.button2);

        sendVerificationCode(phone);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code=otp.getText().toString();

                if (code.isEmpty() || code.length()<6){
                    otp.setError("Enter Code ");
                    otp.requestFocus();
                    return;
                }

                verifyCode(code);

            }
        });
    }





    private void verifyCode(String code){
        PhoneAuthCredential credential =PhoneAuthProvider.getCredential(verificationId,code);
        signInWithCreadential(credential);
    }

    private void signInWithCreadential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent= new Intent(AdminFP_OTP.this,AdminCreateNewPassword_FP.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("a",myemail);
                    intent.putExtra("b",mypass);
                    startActivity(intent);
                    Toast.makeText(AdminFP_OTP.this, "OTP Verification Successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(AdminFP_OTP.this, "Please Enter Correct OTP, Verification Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void sendVerificationCode(String number){
        //Progress Bar show
        PhoneAuthProvider.getInstance().verifyPhoneNumber(number,60, TimeUnit.SECONDS, TaskExecutors.MAIN_THREAD,mCallBack);

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            verificationId=s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code=phoneAuthCredential.getSmsCode();
            if (code != null){
                otp.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(AdminFP_OTP.this,"Please Enter Correct OTP, Verification Failed" , Toast.LENGTH_SHORT).show();
        }
    };



}
