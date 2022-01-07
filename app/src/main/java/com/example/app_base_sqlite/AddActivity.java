package com.example.app_base_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    Button btn_insert;
    EditText tv_nom;
    EditText tv_prenom;
    MyDatabase myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        btn_insert = findViewById(R.id.btn_Insert);
        tv_nom = findViewById(R.id.tv_nom);
        tv_prenom = findViewById(R.id.tv_prenom);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv_nom.getText().toString().isEmpty() || tv_prenom.getText().toString().isEmpty())
                {
                    Toast.makeText(AddActivity.this, "Enter the fields !!", Toast.LENGTH_SHORT).show();

                } else {
                    try {
                        String Prenom = tv_prenom.getText().toString().trim();
                        String Nom = tv_nom.getText().toString().trim();
                        MyDatabase myDB = new MyDatabase(AddActivity.this);
                        myDB.addPerson(Prenom,Nom);
                        tv_prenom.setText("");
                        tv_nom.setText("");
                    }
                    catch (SQLException e)
                    {
                        Toast.makeText(AddActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }
}