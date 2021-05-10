package com.starkindustriesruturajsutar.elementaryandintermediatebynitinpandit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminDrawingTypesElementary extends AppCompatActivity {

    Button vastuchitra , smranchitra , sankalpchitra , bhumiti;
    public String myemail;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AdminDrawingTypesElementary.this , AdminDashboard.class);
        intent.putExtra("a",myemail);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_drawing_types_elementary);

        Intent intent11 = getIntent();
        myemail = intent11.getStringExtra("a");


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
                Intent intent1 = new Intent(AdminDrawingTypesElementary.this , AdminVastuchitraSubParts.class);
                intent1.putExtra("a",myemail);
                startActivity(intent1);
                finish();
            }
        });

        smranchitra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(AdminDrawingTypesElementary.this , AdminElementarySmranchitraUpload.class);
                intent2.putExtra("a",myemail);
                startActivity(intent2);
                finish();

            }
        });

        sankalpchitra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(AdminDrawingTypesElementary.this , AdminElementarySankalpchitraUpload.class);
                intent3.putExtra("a",myemail);
                startActivity(intent3);
                finish();

            }
        });

        bhumiti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(AdminDrawingTypesElementary.this , AdminElementaryBhumitiUpload.class);
                intent4.putExtra("a",myemail);
                startActivity(intent4);
                finish();
            }
        });
    }
}
