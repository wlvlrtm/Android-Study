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

public class Firebase1Activity extends AppCompatActivity implements ValueEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase1);

        /////

        // FireBase DB "Item01"
        DatabaseReference item01 = FirebaseDatabase.getInstance().getReference("item01");

        // EventListener
        item01.addValueEventListener(this);
        EditText editText = findViewById(R.id.editText);
        Button btnSave = findViewById(R.id.btnSave);
        // APP -> DB
        btnSave.setOnClickListener((view) -> item01.setValue(editText.getText().toString()));
    }

    @Override   // IF, Data Change; DB -> APP
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
}