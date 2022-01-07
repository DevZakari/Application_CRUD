package com.example.app_base_sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton float_btn;
    RecyclerView recyclerView;
    RecyclerView.Adapter personAdapter;
    RecyclerView.LayoutManager layoutManager;
    MyDatabase mydb;
    ArrayList<String> person_id,first_name,last_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        float_btn = findViewById(R.id.delete_btn);
        float_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this,AddActivity.class);
                startActivity(in);
            }
        });
        mydb = new MyDatabase(MainActivity.this);
        person_id = new ArrayList<String>();
        first_name = new ArrayList<String>();
        last_name = new ArrayList<String>();

        recyclerView = findViewById(R.id.recycle_v);
        recyclerView.setHasFixedSize(true);
        displayData();
        personAdapter = new PersonAdapter(MainActivity.this,person_id,first_name,last_name);
        recyclerView.setAdapter(personAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


    }
    void displayData() {
        Cursor c = mydb.readAllData();
        if (c.getCount() == 0) {
            Toast.makeText(MainActivity.this, "No Data. ", Toast.LENGTH_SHORT).show();
        } else {
            while (c.moveToNext()) {

                person_id.add(c.getString(0));
                first_name.add(c.getString(1));
                last_name.add(c.getString(2));
            }
        }
    }
}