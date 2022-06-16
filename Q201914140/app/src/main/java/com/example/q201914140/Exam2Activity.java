package com.example.q201914140;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Exam2Activity extends AppCompatActivity {
    EditText editText;
    TextView textView;

    String str;

    DatabaseReference item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam2);

        /////

        item = FirebaseDatabase.getInstance().getReference("item");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                TextView textView1 = findViewById(R.id.textView);
                textView1.setText(snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        item.addValueEventListener(valueEventListener);

    }

    public void button_clicked(View view) {
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        str = textView.getText().toString() + "\r\n" + editText.getText().toString();
        editText.setText("");
        item.setValue(str);
    }
}