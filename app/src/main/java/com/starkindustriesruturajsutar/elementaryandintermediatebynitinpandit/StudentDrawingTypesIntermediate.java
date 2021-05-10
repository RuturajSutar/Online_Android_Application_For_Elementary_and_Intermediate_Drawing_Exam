package com.starkindustriesruturajsutar.elementaryandintermediatebynitinpandit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentDrawingTypesIntermediate extends AppCompatActivity {


    Button sthirchitra , smranchitra , sankalpchitra , bhumiti;
    public String mymobile;


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(StudentDrawingTypesIntermediate.this , StudentDashboard.class);
        intent.putExtra("a",mymobile);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_drawing_types_intermediate);

        Intent intent11 = getIntent();
        mymobile = intent11.getStringExtra("a");


        sthirchitra=(Button)findViewById(R.id.button1);
        smranchitra=(Button)findViewById(R.id.button2);
        sankalpchitra=(Button)findViewById(R.id.button3);
        bhumiti=(Button)findViewById(R.id.button4);

        sthirchitra.setTranslationX(-1500);
        smranchitra.setTranslationX(1500);
        sankalpchitra.setTranslationX(-1500);
        bhumiti.setTranslationX(1500);

        sthirchitra.animate().translationXBy(1500).setDuration(300);
        smranchitra.animate().translationXBy(-1500).setDuration(500);
        sankalpchitra.animate().translationXBy(1500).setDuration(700);
        bhumiti.animate().translationXBy(-1500).setDuration(900);

        sthirchitra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(StudentDrawingTypesIntermediate.this , StudentIntermediateSthirchitraList.class);
                intent5.putExtra("a",mymobile);
                startActivity(intent5);
                finish();
            }
        });

        smranchitra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(StudentDrawingTypesIntermediate.this , StudentIntermediateSmranchitraList.class);
                intent2.putExtra("a",mymobile);
                startActivity(intent2);
                finish();
            }
        });

        sankalpchitra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(StudentDrawingTypesIntermediate.this , StudentIntermediateSankalpchitraList.class);
                intent3.putExtra("a",mymobile);
                startActivity(intent3);
                finish();
            }
        });

        bhumiti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(StudentDrawingTypesIntermediate.this , StudentIntermediateBhumitiList.class);
                intent4.putExtra("a",mymobile);
                startActivity(intent4);
                finish();
            }
        });


    }
}
