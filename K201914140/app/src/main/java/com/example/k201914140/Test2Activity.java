package com.example.k201914140;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Test2Activity extends AppCompatActivity {
    final static SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
    Date date;
    Test2Adapter test2Adapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        /////

        arrayList = new ArrayList<String>();
        test2Adapter = new Test2Adapter(this, arrayList);

        test2Adapter.setItemClick(new Test2Adapter.ItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(Test2Activity.this, "POS : " + position, Toast.LENGTH_SHORT).show();
                arrayList.remove(position);
                test2Adapter.notifyDataSetChanged();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(test2Adapter);

        Button b = findViewById(R.id.btn_add);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                date = new Date();
                String s = format.format(date);

                arrayList.add(s);
                test2Adapter.notifyDataSetChanged();
            }
        });
    }
}