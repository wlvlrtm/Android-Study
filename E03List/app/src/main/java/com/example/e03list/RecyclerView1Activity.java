package com.example.e03list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class RecyclerView1Activity extends AppCompatActivity {
    RecyclerView1Adapter recyclerView1Adapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view1);

        /////

        arrayList = new ArrayList<String>();

        arrayList.add("One");
        arrayList.add("Two");


        recyclerView1Adapter = new RecyclerView1Adapter(this, arrayList);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.addItemDecoration
                (new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(recyclerView1Adapter);

        Button button = findViewById(R.id.button_OK);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = findViewById(R.id.editText);
                String str = editText.getText().toString();

                editText.setText("");

                arrayList.add(str);
                recyclerView1Adapter.notifyDataSetChanged();
            }
        });
    }
}