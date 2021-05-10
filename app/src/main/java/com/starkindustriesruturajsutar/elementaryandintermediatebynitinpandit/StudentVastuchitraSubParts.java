package com.starkindustriesruturajsutar.elementaryandintermediatebynitinpandit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentVastuchitraSubParts extends AppCompatActivity {

    Button samangi , vishamangi ;
    public String mymobile;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(StudentVastuchitraSubParts.this , StudentDrawingTypesElementary.class);
        intent.putExtra("a",mymobile);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_vastuchitra_sub_parts);

        Intent intent11 = getIntent();
        mymobile = intent11.getStringExtra("a");


        samangi=(Button)findViewById(R.id.button1);
        vishamangi=(Button)findViewById(R.id.button2);

        samangi.setTranslationX(-1500);
        vishamangi.setTranslationX(1500);


        samangi.animate().translationXBy(1500).setDuration(500);
        vishamangi.animate().translationXBy(-1500).setDuration(500);

        samangi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(StudentVastuchitraSubParts.this , StudentElementaryVastuchitraSamangiList.class);
                intent1.putExtra("a",mymobile);
                startActivity(intent1);
                finish();
            }
        });

        vishamangi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(StudentVastuchitraSubParts.this , StudentElementaryVastuchitraVishamangiList.class);
                intent2.putExtra("a",mymobile);
                startActivity(intent2);
                finish();
            }
        });
    }
}
