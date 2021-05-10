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

public class AdminDashboard extends AppCompatActivity {


    Button elementary,intermediate;
    public String myemail;
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
        setContentView(R.layout.activity_admin_dashboard);



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
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#E26940"));
        actionBar.setBackgroundDrawable(colorDrawable);

        elementary=(Button)findViewById(R.id.button1);
        intermediate=(Button)findViewById(R.id.button2);

        Intent intent11 = getIntent();
        myemail = intent11.getStringExtra("a");

        elementary.setTranslationX(-1500);
        intermediate.setTranslationX(1500);


        elementary.animate().translationXBy(1500).setDuration(500);
        intermediate.animate().translationXBy(-1500).setDuration(500);

        elementary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(AdminDashboard.this , AdminDrawingTypesElementary.class);
                intent1.putExtra("a",myemail);
                startActivity(intent1);
                finish();
            }
        });

        intermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(AdminDashboard.this , AdminDrawingTypesIntermediate.class);
                intent2.putExtra("a",myemail);
                startActivity(intent2);
                finish();
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.admin_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Intent intent=new Intent(AdminDashboard.this,AdminAccount.class);
                intent.putExtra("a",myemail);
                startActivity(intent);
                finish();
                return true;
            case R.id.item2:
                Intent intent2=new Intent(AdminDashboard.this,AdminStudentRegisterStandardList.class);
                intent2.putExtra("a",myemail);
                startActivity(intent2);
                finish();
                return true;
            case R.id.item3:
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminDashboard.this);

                builder.setMessage("Are you sure to logout..?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                new Admin(AdminDashboard.this).removeUser();
                                Intent intent19 = new Intent(AdminDashboard.this,AdminLoginPage.class);
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

            case R.id.item4:
                Intent intent5=new Intent(AdminDashboard.this,AdminChangePassword.class);
                intent5.putExtra("a",myemail);
                startActivity(intent5);
                finish();
                return true;

            case R.id.item51:
                Intent intent6=new Intent(AdminDashboard.this,AdminElementaryVastuchitraSamangiList.class);
                intent6.putExtra("a",myemail);
                startActivity(intent6);
                finish();
                return true;
            case R.id.item52:
                Intent intent7=new Intent(AdminDashboard.this,AdminElementaryVastuchitraVishamangiList.class);
                intent7.putExtra("a",myemail);
                startActivity(intent7);
                finish();
                return true;
            case R.id.item53:
                Intent intent8=new Intent(AdminDashboard.this,AdminElementarySmranchitraList.class);
                intent8.putExtra("a",myemail);
                startActivity(intent8);
                finish();
                return true;
            case R.id.item54:
                Intent intent9=new Intent(AdminDashboard.this,AdminElementarySankalpchitraList.class);
                intent9.putExtra("a",myemail);
                startActivity(intent9);
                finish();
                return true;
            case R.id.item55:
                Intent intent10=new Intent(AdminDashboard.this,AdminElementaryBhumitiList.class);
                intent10.putExtra("a",myemail);
                startActivity(intent10);
                finish();
                return true;

            case R.id.item61:
                Intent intent11=new Intent(AdminDashboard.this,AdminIntermediateSthirchitraList.class);
                intent11.putExtra("a",myemail);
                startActivity(intent11);
                finish();
                return true;
            case R.id.item62:
                Intent intent12=new Intent(AdminDashboard.this,AdminIntermediateSmranchitraList.class);
                intent12.putExtra("a",myemail);
                startActivity(intent12);
                finish();
                return true;
            case R.id.item63:
                Intent intent13=new Intent(AdminDashboard.this,AdminIntermediateSankalpchitrachitraList.class);
                intent13.putExtra("a",myemail);
                startActivity(intent13);
                finish();
                return true;
            case R.id.item64:
                Intent intent14=new Intent(AdminDashboard.this,AdminIntermediateBhumitiList.class);
                intent14.putExtra("a",myemail);
                startActivity(intent14);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
