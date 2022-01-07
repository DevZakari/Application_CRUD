package com.example.app_base_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText tv_nom_up,tv_prenom_up;
    Button btn_update;
    String id,first_name,last_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        tv_nom_up = findViewById(R.id.tv_nom_up);
        tv_prenom_up = findViewById(R.id.tv_prenom_up);
        btn_update = findViewById(R.id.btn_update);
        getIntentData();
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ls_name = tv_nom_up.getText().toString().trim();
                String fs_name = tv_prenom_up.getText().toString().trim();
                MyDatabase mydb = new MyDatabase(UpdateActivity.this);
                mydb.updateData(id,ls_name,fs_name);
            }
        });
    }
    void getIntentData(){
        if(getIntent().hasExtra("id") &&
        getIntent().hasExtra("first_name") &&
        getIntent().hasExtra("last_name")){
            id = getIntent().getStringExtra("id");
            first_name = getIntent().getStringExtra("first_name");
            last_name = getIntent().getStringExtra("last_name");

            // setting the data into the text :
            tv_prenom_up.setText(first_name);
            tv_nom_up.setText(last_name);
        }else {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }
}