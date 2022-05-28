package com.example.k201914140;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test1Activity extends AppCompatActivity {
    final static SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
    Date date;
    String resultDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_test1);

        /////

        date = new Date();
        resultDate = format.format(date);

        Intent intent = new Intent();
        intent.putExtra("NOW", resultDate);
        setResult(RESULT_OK, intent);
        finish();
    }
}