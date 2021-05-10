package com.starkindustriesruturajsutar.elementaryandintermediatebynitinpandit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminVastuchitraSubParts extends AppCompatActivity {

    Button samangi , vishamangi ;
    public String myemail;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AdminVastuchitraSubParts.this , AdminDrawingTypesElementary.class);
        intent.putExtra("a",myemail);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_vastuchitra_sub_parts);

        Intent intent11 = getIntent();
        myemail = intent11.getStringExtra("a");

        samangi=(Button)findViewById(R.id.button1);
        vishamangi=(Button)findViewById(R.id.button2);

        samangi.setTranslationX(-1500);
        vishamangi.setTranslationX(1500);


        samangi.animate().translationXBy(1500).setDuration(500);
        vishamangi.animate().translationXBy(-1500).setDuration(500);

        samangi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(AdminVastuchitraSubParts.this , AdminElementaryVastuchitraSamangiUpload.class);
                intent1.putExtra("a",myemail);
                startActivity(intent1);
                finish();
            }
        });

        vishamangi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(AdminVastuchitraSubParts.this , AdminElementaryVastuchitraVishamangiUpload.class);
                intent2.putExtra("a",myemail);
                startActivity(intent2);
                finish();
            }
        });
    }
}
