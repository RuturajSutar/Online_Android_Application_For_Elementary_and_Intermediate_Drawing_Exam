package com.starkindustriesruturajsutar.elementaryandintermediatebynitinpandit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StudentRegistration extends AppCompatActivity {


    Spinner standardspinner;
    public String mystandard;
    TextView backtologin;
    Button btn1 , register;
    CheckBox showpassword;
    LinearLayout ll;
    EditText name , mobile , password ;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(StudentRegistration.this , StudentLoginPage.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);


        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        standardspinner = (Spinner)findViewById(R.id.spinner2);

        List<String> list=new ArrayList<String>();
        list.add("Select_Standard");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        list.add("11");
        list.add("12");

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        standardspinner.setAdapter(arrayAdapter);

        standardspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                standardspinner.setSelection(position);
                mystandard=standardspinner.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        backtologin = (TextView)findViewById(R.id.textView2);
        btn1 = (Button)findViewById(R.id.button1);
        register = (Button)findViewById(R.id.button2);
        ll=(LinearLayout)findViewById(R.id.ll);


        name = (EditText)findViewById(R.id.editText1);
        mobile = (EditText)findViewById(R.id.editText2);
        password = (EditText)findViewById(R.id.editText3);


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


        ll.setTranslationX(-1500);
        btn1.setTranslationX(1500);
        register.setTranslationX(-1500);

        ll.animate().translationXBy(1500).setDuration(1000);
        btn1.animate().translationXBy(-1500).setDuration(1000);
        register.animate().translationXBy(1500).setDuration(1000);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String myname=name.getText().toString();
                final String mymobile=mobile.getText().toString();
                final String mypassword=password.getText().toString();



                TextView errorText = (TextView)standardspinner.getSelectedView();

                if (myname.isEmpty()){
                    name.setError("Please Enter Full Name");
                    name.requestFocus();
                    return;
                }
                else if (mymobile.isEmpty()){
                    mobile.setError("Please Enter Mobile Number");
                    mobile.requestFocus();
                    return;
                }
                else if (mymobile.length() != 10){
                    mobile.setError("Please Enter Valid Mobile Number");
                    mobile.requestFocus();
                    return;
                }
                else if (mystandard.equals("Select_Standard")){
                    errorText.setError("Please Select Standard");
                    errorText.requestFocus();
                }
                else if (mypassword.isEmpty()){
                    password.setError("Please Enter Password");
                    password.requestFocus();
                    return;
                }
                else if (myname.isEmpty() && mymobile.isEmpty() && mymobile.length() != 10 && mystandard.equals("Select_Standard") &&  mypassword.isEmpty()){
                    name.setError("Please Fill All The Information");
                    name.requestFocus();
                    return;
                }
                else if (!(myname.isEmpty() && mymobile.isEmpty() && mymobile.length() != 10 && mystandard.equals("Select_Standard") &&  mypassword.isEmpty())){

                    Intent intent=new Intent(StudentRegistration.this,StudentRegistrationLoadingPage.class);
                    intent.putExtra("a",myname);
                    intent.putExtra("b",mymobile);
                    intent.putExtra("c",mystandard);
                    intent.putExtra("d",mypassword);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(StudentRegistration.this, "Please fill all the information", Toast.LENGTH_SHORT).show();
                }

            }
        });

        backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(StudentRegistration.this , StudentLoginPage.class);
                startActivity(intent2);
                finish();
            }
        });
    }
}
