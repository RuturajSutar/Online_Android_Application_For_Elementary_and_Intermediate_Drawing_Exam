package com.starkindustriesruturajsutar.elementaryandintermediatebynitinpandit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminStudentRegisterStandardList extends AppCompatActivity {


    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    public String myemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_student_register_standard_list);


        listView=(ListView)findViewById(R.id.listView);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference().child("Student");

        Intent intent = getIntent();
        myemail = intent.getStringExtra("a");


        list=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.student_standard_list,R.id.studentstandardlist,list);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()){

                    String dates = ds.getKey().toString();
                    list.add(dates);
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String mystandard = (String)listView.getItemAtPosition(position);
                Intent intent = new Intent(AdminStudentRegisterStandardList.this,AdminStudentListStandardwise.class);
                intent.putExtra("a",mystandard);
                intent.putExtra("b",myemail);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(AdminStudentRegisterStandardList.this , AdminDashboard.class);
        intent1.putExtra("a",myemail);
        startActivity(intent1);
        finish();
    }
}
