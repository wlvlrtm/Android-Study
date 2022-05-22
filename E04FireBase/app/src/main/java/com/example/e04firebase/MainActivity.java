package com.example.e04firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Btn_Firebase1Activity(View view) {
        Intent intent = new Intent(this, Firebase1Activity.class);
        startActivity(intent);
    }

    public void Btn_Clicked(View view) {
        Class classObj = null;

        switch (view.getId()) {
            case R.id.button:
                classObj = Firebase1Activity.class;
                break;
            case R.id.button2:
                classObj = MemoListActivity.class;
                break;
        }
        Intent intent = new Intent(this, classObj);
        startActivity(intent);
    }
}