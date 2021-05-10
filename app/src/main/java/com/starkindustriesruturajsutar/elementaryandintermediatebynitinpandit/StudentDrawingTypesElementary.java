package com.starkindustriesruturajsutar.elementaryandintermediatebynitinpandit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentDrawingTypesElementary extends AppCompatActivity {

    Button vastuchitra , smranchitra , sankalpchitra , bhumiti;
    public String mymobile;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(StudentDrawingTypesElementary.this , StudentDashboard.class);
        intent.putExtra("a",mymobile);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_drawing_types_elementary);

        Intent intent11 = getIntent();
        mymobile = intent11.getStringExtra("a");

        vastuchitra=(Button)findViewById(R.id.button1);
        smranchitra=(Button)findViewById(R.id.button2);
        sankalpchitra=(Button)findViewById(R.id.button3);
        bhumiti=(Button)findViewById(R.id.button4);

        vastuchitra.setTranslationX(-1500);
        smranchitra.setTranslationX(1500);
        sankalpchitra.setTranslationX(-1500);
        bhumiti.setTranslationX(1500);

        vastuchitra.animate().translationXBy(1500).setDuration(300);
        smranchitra.animate().translationXBy(-1500).setDuration(500);
        sankalpchitra.animate().translationXBy(1500).setDuration(700);
        bhumiti.animate().translationXBy(-1500).setDuration(900);

        vastuchitra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(StudentDrawingTypesElementary.this , StudentVastuchitraSubParts.class);
                intent1.putExtra("a",mymobile);
                startActivity(intent1);
                finish();
            }
        });

        smranchitra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(StudentDrawingTypesElementary.this , StudentElementarySmranchitraList.class);
                intent2.putExtra("a",mymobile);
                startActivity(intent2);
                finish();
            }
        });

        sankalpchitra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(StudentDrawingTypesElementary.this , StudentElementarySankalpchitraList.class);
                intent3.putExtra("a",mymobile);
                startActivity(intent3);
                finish();
            }
        });

        bhumiti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(StudentDrawingTypesElementary.this , StudentElementaryBhumitiList.class);
                intent4.putExtra("a",mymobile);
                startActivity(intent4);
                finish();
            }
        });
    }
}
