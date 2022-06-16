package com.example.q201914140;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /////


    }

    public void button_clicked(View view) {
        Class classObj = null;

        switch (view.getId()) {
            case R.id.button_exam1: classObj = Exam1Activity.class; break;
            case R.id.button_exam2: classObj = Exam2Activity.class; break;
            case R.id.button_exam3: classObj = Exam3Activity.class; break;
        }

        Intent intent = new Intent(this, classObj);
        startActivity(intent);
    }
}