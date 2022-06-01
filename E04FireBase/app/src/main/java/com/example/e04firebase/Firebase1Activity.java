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
    DatabaseReference item01;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase1);

        /////

        this.item01 = FirebaseDatabase.getInstance().getReference("item01");
        this.item01.addValueEventListener(new ValueEventListener() {
            @Override   // item01 Data Change
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                TextView textView = findViewById(R.id.textView);

                textView.setText(value);
                Log.d("My Tag", "Received Data: " + value);
            }

            @Override   // item01 Server Error
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("My Tag", "Server Error: " + error.toException());
            }
        });
    }

    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.button_Save:
                EditText editText = findViewById(R.id.editText);
                String text = editText.getText().toString();
                this.item01.setValue(text);  // onDataChange() Call
                break;
        }
    }
}