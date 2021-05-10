package com.starkindustriesruturajsutar.elementaryandintermediatebynitinpandit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ImageView my_image;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_image = (ImageView)findViewById(R.id.imageView);
        my_image.animate().rotation(720).alpha(1).setDuration(3000);

        final User user=new User(MainActivity.this);
        final Admin admin = new Admin(MainActivity.this);


        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {


                if (user.getName() != ""){
                    Intent intent=new Intent(MainActivity.this,StudentDashboard.class);
                    intent.putExtra("a",user.getName());
                    startActivity(intent);
                    finish();
                }
                else if (admin.getAname() != ""){
                    Intent intent20 = new Intent(MainActivity.this,AdminDashboard.class);
                    intent20.putExtra("a",admin.getAname());
                    startActivity(intent20);
                    finish();
                }
                else {
                    Intent intent=new Intent(MainActivity.this,AllLoginButtons.class);
                    startActivity(intent);
                    finish();
                }

            }
        },4000);
    }
}
