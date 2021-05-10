package com.starkindustriesruturajsutar.elementaryandintermediatebynitinpandit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class StudentDashboard extends AppCompatActivity {

    Button elementary,intermediate;
    public String mymobile;
    private long backPressedTime;
    private Toast backToast;

    @Override
    public void onBackPressed() {

        if (backPressedTime+2000>System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }
        else {
            backToast=Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime=System.currentTimeMillis();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("MyNotifications" , "MyNotifications" , NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        FirebaseMessaging.getInstance().subscribeToTopic("general").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String meg = "Successfull";
                if(!task.isSuccessful()){
                    meg = "Failed";
                }
            }
        });

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#C372DA"));
        actionBar.setBackgroundDrawable(colorDrawable);

        elementary=(Button)findViewById(R.id.button1);
        intermediate=(Button)findViewById(R.id.button2);

        Intent intent11 = getIntent();
        mymobile = intent11.getStringExtra("a");

        elementary.setTranslationX(-1500);
        intermediate.setTranslationX(1500);


        elementary.animate().translationXBy(1500).setDuration(500);
        intermediate.animate().translationXBy(-1500).setDuration(500);

        elementary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(StudentDashboard.this , StudentDrawingTypesElementary.class);
                intent1.putExtra("a",mymobile);
                startActivity(intent1);
                finish();
            }
        });

        intermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(StudentDashboard.this , StudentDrawingTypesIntermediate.class);
                intent2.putExtra("a",mymobile);
                startActivity(intent2);
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.student_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Intent intent=new Intent(StudentDashboard.this,StudentAccount.class);
                intent.putExtra("a",mymobile);
                startActivity(intent);
                finish();
                return true;
            case R.id.item3:
                Intent intent2=new Intent(StudentDashboard.this,StudentChangePassword.class);
                intent2.putExtra("a",mymobile);
                startActivity(intent2);
                finish();
                return true;
            case R.id.item2:
                AlertDialog.Builder builder = new AlertDialog.Builder(StudentDashboard.this);

                builder.setMessage("Are you sure to logout..?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                new User(StudentDashboard.this).removeUser();
                                Intent intent19 = new Intent(StudentDashboard.this,StudentLoginPage.class);
                                startActivity(intent19);
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
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
