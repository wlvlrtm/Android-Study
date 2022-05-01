package com.example.e03list;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        /////

        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, arrayList);

        arrayList.add("One");
        arrayList.add("Two");

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(arrayAdapter);

        Button button_OK = findViewById(R.id.button_OK);
        button_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = findViewById(R.id.editText);
                String str = editText.getText().toString();

                editText.setText("");

                arrayList.add(str);
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }
}