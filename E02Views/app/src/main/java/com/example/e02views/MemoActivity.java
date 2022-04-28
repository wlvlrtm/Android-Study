package com.example.e02views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MemoActivity extends AppCompatActivity {
    private boolean isGoodToGo = true;
    private static boolean isOneshot = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        /////

        Button button_Save = findViewById(R.id.button_Save);
        EditText editText_Title = findViewById(R.id.editText_Title);
        EditText editText_Content = findViewById(R.id.editText_Content);

        String defaultTitle = "공지사항입니다.";
        String defaultContent = "4주차 또는 5주차에 구현 시험을 봅니다.";

        if (!isOneshot) {
            editText_Title.setText(defaultTitle);
            editText_Content.setText(defaultContent);

            isOneshot = true;
        }

        button_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemoActivity.this.isGoodToGo = true;

                String title = editText_Title.getText().toString();
                String content = editText_Content.getText().toString();

                if (EmptyStringChecker.isEmptyOrWhiteSpace(title)) {
                    editText_Title.setError("제목을 입력해주세요.");
                    MemoActivity.this.isGoodToGo = false;
                }
                else if (EmptyStringChecker.isEmptyOrWhiteSpace(content)) {
                    editText_Content.setError("내용을 입력해주세요.");
                    MemoActivity.this.isGoodToGo = false;
                }

                // USER DATA 2 SERVER //

                if (MemoActivity.this.isGoodToGo) {
                    String strMS = ("저장 성공:" + " " + title);
                    Toast.makeText(MemoActivity.this, strMS, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}