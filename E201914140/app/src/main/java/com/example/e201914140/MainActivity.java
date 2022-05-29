package com.example.e201914140;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    EditText editTextNum;
    EditText editTextName;

    ItemAdapter itemAdapter;
    ArrayList<Item> itemArrayList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /////

        this.editTextName = findViewById(R.id.editText_Name);
        this.editTextNum = findViewById(R.id.editText_Num);
        this.recyclerView = findViewById(R.id.recyclerView);

        this.itemArrayList = new ArrayList<Item>();
        this.itemAdapter = new ItemAdapter(this, itemArrayList);

        this.itemAdapter.setItemClick(new ItemAdapter.ItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                itemArrayList.remove(position);
                itemAdapter.notifyDataSetChanged();
            }
        });

        this.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setAdapter(itemAdapter);
    }

    public void onButtonClick(View view) {
        String name;
        String num;

        name = editTextName.getText().toString();
        num = editTextNum.getText().toString();

        this.itemArrayList.add(new Item(name, num));
        itemAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.item_Del) {
            itemArrayList.clear();
            itemAdapter.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}