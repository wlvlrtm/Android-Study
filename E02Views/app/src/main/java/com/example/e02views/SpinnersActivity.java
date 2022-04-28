package com.example.e02views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SpinnersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinners);

        /////

        String[] stringArray = {"집전화", "직장 주소", "기타"};
        Spinner spinner_address = findViewById(R.id.spinner_addressType);

        ArrayAdapter<String> arrayAdapter
                = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, stringArray);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_address.setAdapter(arrayAdapter);

        Button button_save = findViewById(R.id.button_save);
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner spinner_phoneType = findViewById(R.id.spinner_phoneType);
                Spinner spinner_addressType = findViewById(R.id.spinner_addressType);

                int index_phoneType = spinner_phoneType.getSelectedItemPosition();
                int index_addressType = spinner_addressType.getSelectedItemPosition();

                String text_phoneType = spinner_phoneType.getSelectedItem().toString();
                String text_addressType = spinner_addressType.getSelectedItem().toString();

                String result =
                        String.format("전화:%s(%d)    주소:%s(%d)",
                                text_phoneType, index_phoneType,
                                text_addressType, index_addressType);
                Toast.makeText(SpinnersActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        });
    }
}