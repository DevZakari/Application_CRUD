package com.example.app_base_sqlite;

import android.content.Context;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {


    ArrayList<String> person_id,first_name,last_name;
    Context context;

    public PersonAdapter(Context context,ArrayList<String> person_id,ArrayList<String> first_name,ArrayList<String> last_name ){
        this.person_id = person_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView number,tv_rc_nom,tv_rc_prenom;
        LinearLayout row_list;
        FloatingActionButton float_delete;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            number = itemView.findViewById(R.id.number);
            tv_rc_nom = itemView.findViewById(R.id.tv_rc_nom);
            tv_rc_prenom = itemView.findViewById(R.id.tv_rc_prenom);
            row_list = itemView.findViewById(R.id.row_list);
            float_delete = itemView.findViewById(R.id.delete_btn);
        }
    }
    @NonNull
    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_items,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.ViewHolder viewHolder,  int ii) {
        viewHolder.number.setText(String.valueOf(person_id.get(ii)));
        viewHolder.tv_rc_nom.setText(String.valueOf(last_name.get(ii)));
        viewHolder.tv_rc_prenom.setText(String.valueOf(first_name.get(ii)));
        viewHolder.row_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(context,UpdateActivity.class);
                in.putExtra("id",String.valueOf(person_id.get(ii)));
                in.putExtra("first_name",String.valueOf(first_name.get(ii)));
                in.putExtra("last_name",String.valueOf(last_name.get(ii)));
                context.startActivity(in);
            }
        });
        viewHolder.float_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dialolg : are you shure about that ?
                // if not : then nothing
                // else :
                MyDatabase db=new MyDatabase(context);
                db.DeleteEntry(viewHolder.number.getText().toString());
                Intent intent = new Intent(context,MainActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return person_id.size();
    }
}

