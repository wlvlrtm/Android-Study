package com.example.q201914140;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class Exam1Activity extends AppCompatActivity {
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam1);

        /////

    }


    public void button_clicked(View view) {
        editText = findViewById(R.id.editText);
        Snackbar.make(view, editText.getText().toString(), Snackbar.LENGTH_LONG).show();
    }
}