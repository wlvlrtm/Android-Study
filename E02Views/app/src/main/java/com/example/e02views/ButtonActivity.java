package com.example.e02views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class ButtonActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        /////

        ImageButton imageButton1 = findViewById(R.id.imageButton1);
        ImageButton imageButton2 = findViewById(R.id.imageButton2);
        ImageButton imageButton3 = findViewById(R.id.imageButton3);
        ImageButton imageButton4 = findViewById(R.id.imageButton4);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        imageButton1.setOnClickListener(this::onClick);
        imageButton2.setOnClickListener(this::onClick);
        imageButton3.setOnClickListener(this::onClick);
        imageButton4.setOnClickListener(this::onClick);

        button1.setOnClickListener(this::onClick);
        button2.setOnClickListener(this::onClick);
        button3.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        String msg;
        int buttonID = view.getId();

        switch (buttonID) {
            case R.id.button1:
                msg = "1번째 버튼 클릭";
                break;
            case R.id.button2:
                msg = "2번째 버튼 클릭";
                break;
            case R.id.button3:
                msg = "3번째 버튼 클릭";
                break;
            case R.id.imageButton1:
                msg = "1번째 이미지 버튼 클릭";
                break;
            case R.id.imageButton2:
                msg = "2번째 이미지 버튼 클릭";
                break;
            case R.id.imageButton3:
                msg = "3번째 이미지 버튼 클릭";
                break;
            case R.id.imageButton4:
                msg = "4번째 이미지 버튼 클릭";
                break;
            default:
                msg = "알 수 없는 버튼 클릭";
                break;
        }

        Toast.makeText(ButtonActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}