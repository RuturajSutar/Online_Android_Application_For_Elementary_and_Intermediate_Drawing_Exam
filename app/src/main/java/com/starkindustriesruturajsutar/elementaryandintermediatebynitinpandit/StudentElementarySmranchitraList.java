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

public class StudentElementarySmranchitraList extends AppCompatActivity {


    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    FirebaseDatabase database;
    DatabaseReference ref;
    public String mymobile;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(StudentElementarySmranchitraList.this , StudentDrawingTypesElementary.class);
        intent.putExtra("a",mymobile);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_elementary_smranchitra_list);

        Intent intent11 = getIntent();
        mymobile = intent11.getStringExtra("a");


        listView=(ListView)findViewById(R.id.listView);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference().child("Images").child("12");



        list=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.mylistitem,R.id.my_list_item,list);


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
                String image_id = (String)listView.getItemAtPosition(position);
                Intent intent = new Intent(StudentElementarySmranchitraList.this,StudentElementarySmranchitraShower.class);
                intent.putExtra("a",image_id);
                intent.putExtra("b",mymobile);
                startActivity(intent);
                finish();
            }
        });
    }
}
