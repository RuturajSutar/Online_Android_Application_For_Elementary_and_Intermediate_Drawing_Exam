package com.starkindustriesruturajsutar.elementaryandintermediatebynitinpandit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminStudentListStandardwise extends AppCompatActivity {

    String mystandard;
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Student student;
    public String myemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_student_list_standardwise);

        Intent intent = getIntent();
        mystandard = intent.getStringExtra("a");
        myemail = intent.getStringExtra("b");

        student = new Student();

        listView=(ListView)findViewById(R.id.listView);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference().child("Student").child(mystandard);


        list=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.student_info,R.id.studentinfo,list);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()){

                    student = ds.getValue(Student.class);
                    list.add("Name :"+student.getName().toString() +"\n\n"+"Mobile :"+student.getMobile().toString()+"\n\n"+
                            "" +
                            "Standard :"+student.getStandard().toString());
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(AdminStudentListStandardwise.this , AdminStudentRegisterStandardList.class);
        intent1.putExtra("a",myemail);
        startActivity(intent1);
        finish();
    }
}
