package com.starkindustriesruturajsutar.elementaryandintermediatebynitinpandit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentAccount extends AppCompatActivity {


    public String mymobile;
    TextView one , two , three , four , five , six ;
    DatabaseReference mRef , mRef1;
    LottieAnimationView lottieAnimationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_account);

        Intent intent = getIntent();
        mymobile = intent.getStringExtra("a");

        one = (TextView)findViewById(R.id.textView1);
        two = (TextView)findViewById(R.id.textView2);
        three = (TextView)findViewById(R.id.textView3);
        four = (TextView)findViewById(R.id.textView4);
        five = (TextView)findViewById(R.id.textView5);
        six = (TextView)findViewById(R.id.textView6);

        lottieAnimationView=(LottieAnimationView)findViewById(R.id.lottie);
        lottieAnimationView.setVisibility(View.VISIBLE);


        mRef= FirebaseDatabase.getInstance().getReference().child("All_Student").child(mymobile);
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String my_name=dataSnapshot.child("Name").getValue().toString();
                String my_mobile=dataSnapshot.child("Mobile").getValue().toString();
                String my_standard=dataSnapshot.child("Standard").getValue().toString();

                two.setText(my_name);
                four.setText(my_mobile);
                six.setText(my_standard);

                one.setVisibility(View.VISIBLE);
                two.setVisibility(View.VISIBLE);
                three.setVisibility(View.VISIBLE);
                four.setVisibility(View.VISIBLE);
                five.setVisibility(View.VISIBLE);
                six.setVisibility(View.VISIBLE);


                lottieAnimationView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(StudentAccount.this, "Error Occured", Toast.LENGTH_SHORT).show();
                lottieAnimationView.setVisibility(View.INVISIBLE);
            }
        });



    }

    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(StudentAccount.this , StudentDashboard.class);
        intent1.putExtra("a",mymobile);
        startActivity(intent1);
        finish();
    }
}
