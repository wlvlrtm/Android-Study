package com.example.e04firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Firebase1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase1);

        /////

        // FireBase DB "Item01"
        DatabaseReference item01 = FirebaseDatabase.getInstance().getReference("item01");

        // EventListener
        item01.addValueEventListener(new ValueEventListener() {
            @Override   // IF, Data Received
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                TextView textView = findViewById(R.id.textView);

                textView.setText(value);
                Log.d("My Tag", "Received Data: " + value);
            }

            @Override   // IF, Error occurs
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("My Tag", "Server Error: ", error.toException());
            }
        });

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = findViewById(R.id.editText);
                String str = editText.getText().toString();

                item01.setValue(str);   // To DB
            }
        });
    }
}