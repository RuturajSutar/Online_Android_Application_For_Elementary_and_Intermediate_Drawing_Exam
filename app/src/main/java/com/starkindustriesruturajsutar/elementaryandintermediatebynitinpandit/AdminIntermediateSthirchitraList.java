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

public class AdminIntermediateSthirchitraList extends AppCompatActivity {

    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    FirebaseDatabase database;
    DatabaseReference ref;
    public String myemail;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AdminIntermediateSthirchitraList.this , AdminDashboard.class);
        intent.putExtra("a",myemail);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_intermediate_sthirchitra_list);

        Intent intent = getIntent();
        myemail = intent.getStringExtra("a");


        listView=(ListView)findViewById(R.id.listView);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference().child("Images").child("21");


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
                Intent intent = new Intent(AdminIntermediateSthirchitraList.this,AdminIntermediateSthirchitraShower.class);
                intent.putExtra("a",image_id);
                intent.putExtra("b",myemail);
                startActivity(intent);
                finish();
            }
        });
    }
}
