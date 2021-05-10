package com.starkindustriesruturajsutar.elementaryandintermediatebynitinpandit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminDrawingTypesIntermediate extends AppCompatActivity {

    Button sthirchitra , smranchitra , sankalpchitra , bhumiti;
    public String myemail;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AdminDrawingTypesIntermediate.this , AdminDashboard.class);
        intent.putExtra("a",myemail);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_drawing_types_intermediate);

        Intent intent11 = getIntent();
        myemail = intent11.getStringExtra("a");

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
                Intent intent5 = new Intent(AdminDrawingTypesIntermediate.this , AdminIntermediateSthirchitraUpload.class);
                intent5.putExtra("a",myemail);
                startActivity(intent5);
                finish();
            }
        });

        smranchitra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(AdminDrawingTypesIntermediate.this , AdminIntermediateSmranchitraUpload.class);
                intent2.putExtra("a",myemail);
                startActivity(intent2);
                finish();
            }
        });

        sankalpchitra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(AdminDrawingTypesIntermediate.this , AdminIntermediateSankalpchitraUpload.class);
                intent3.putExtra("a",myemail);
                startActivity(intent3);
                finish();
            }
        });

        bhumiti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(AdminDrawingTypesIntermediate.this , AdminIntermediateBhumitiUpload.class);
                intent4.putExtra("a",myemail);
                startActivity(intent4);
                finish();
            }
        });


    }
}
