package com.starkindustriesruturajsutar.elementaryandintermediatebynitinpandit;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class StudentLoginPage extends AppCompatActivity {


    TextView registerStudent;
    TextView forgotPassword;
    Button loginButton;
    LinearLayout ll;
    Button btn1;
    EditText mobile,password;
    CheckBox showpassword;

    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(StudentLoginPage.this , AllLoginButtons.class);
        startActivity(intent1);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login_page);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        mobile=(EditText)findViewById(R.id.editText1);
        password=(EditText)findViewById(R.id.editText2);

        showpassword=(CheckBox)findViewById(R.id.showpassword);
        showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


        ll=(LinearLayout)findViewById(R.id.ll);
        btn1=(Button)findViewById(R.id.button1);
        loginButton=(Button)findViewById(R.id.button2);

        ll.setTranslationX(-1500);
        btn1.setTranslationX(1500);
        loginButton.setTranslationX(-1500);


        ll.animate().translationXBy(1500).setDuration(1000);
        btn1.animate().translationXBy(-1500).setDuration(1000);
        loginButton.animate().translationXBy(1500).setDuration(1000);


        registerStudent = (TextView)findViewById(R.id.textView3);
        forgotPassword = (TextView)findViewById(R.id.textView2);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String mymobile=mobile.getText().toString();
                final String mypassword=password.getText().toString();




                if (mymobile.isEmpty()){
                    mobile.setError("Please Enter Mobile Number");
                    mobile.requestFocus();
                    return;
                }
                else if (mymobile.length() != 10){
                    mobile.setError("Please Enter Valid Mobile Number");
                    mobile.requestFocus();
                    return;
                }
                else if (mypassword.isEmpty()){
                    password.setError("Please Enter Password");
                    password.requestFocus();
                    return;
                }
                else if (mymobile.isEmpty() && mymobile.length() != 10 && mypassword.isEmpty()){
                    mobile.setError("Please Fill All the Information");
                    mobile.requestFocus();
                    return;
                }
                else if (!(mymobile.isEmpty() && mymobile.length() != 10 && mypassword.isEmpty())){

                    Intent intent=new Intent(StudentLoginPage.this,StudentLoginLoadingPage.class);
                    intent.putExtra("Mobile",mymobile);
                    intent.putExtra("Password",mypassword);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(StudentLoginPage.this, "Inalid Mobile or Password", Toast.LENGTH_SHORT).show();
                }

            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(StudentLoginPage.this , StudentForgotPassword.class);
                startActivity(intent3);
                finish();
            }
        });

        registerStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(StudentLoginPage.this , StudentRegistration.class);
                startActivity(intent4);
                finish();
            }
        });


    }
}
