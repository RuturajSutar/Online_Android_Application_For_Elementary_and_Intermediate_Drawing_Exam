package com.starkindustriesruturajsutar.elementaryandintermediatebynitinpandit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AllLoginButtons extends AppCompatActivity {

    Button adminButton,studentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_login_buttons);



        if (!isConnected()){
            new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("No Internet Connection")
                    .setMessage("Please Check Your Internet Connection").setPositiveButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).show();

        }
        else {
            Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
        }


        adminButton=(Button)findViewById(R.id.button1);
        studentButton=(Button)findViewById(R.id.button2);

        adminButton.setTranslationX(1500);
        studentButton.setTranslationX(2000);

        adminButton.animate().translationXBy(-1500).setDuration(700);
        studentButton.animate().translationXBy(-2000).setDuration(1000);

        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(AllLoginButtons.this , AdminLoginPage.class);
                startActivity(intent1);
                finish();
            }
        });

        studentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(AllLoginButtons.this , StudentLoginPage.class);
                startActivity(intent2);
                finish();
            }
        });
    }
    private boolean isConnected(){
        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
