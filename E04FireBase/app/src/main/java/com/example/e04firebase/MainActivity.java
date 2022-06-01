package com.example.e04firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.button_Firebase1Activity:
                intent = new Intent(this, Firebase1Activity.class);
                startActivity(intent);
                break;
            case R.id.button_MemoListActivity:
                intent = new Intent(this, MemoListActivity.class);
                startActivity(intent);
                break;
        }
    }
}