package com.example.e04firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class MemoActivity extends AppCompatActivity {
    EditText editText_Title;
    EditText editText_content;

    Memo memo;
    Integer index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        /////

        this.editText_Title = findViewById(R.id.editText_title);
        this.editText_content = findViewById(R.id.editText_content);

        this.memo = (Memo)getIntent().getSerializableExtra("MEMO");
        this.index = (Integer)getIntent().getSerializableExtra("INDEX");

        if (this.memo != null) {    // Memo RELOAD
            this.editText_Title.setText(this.memo.getTitle());
            this.editText_content.setText(this.memo.getContent());
        }
    }

    public void onButtonClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.button_Save :
                String title = this.editText_Title.getText().toString();
                String content = this.editText_content.getText().toString();

                if (TextUtils.isEmpty(title)) {
                    editText_Title.setError("제목을 입력하세요");
                }

                if (TextUtils.isEmpty(content)) {
                    editText_content.setError("본문을 입력하세요");
                }

                Memo memo = new Memo(title, content, new Date());
                Intent intent = new Intent();
                intent.putExtra("MEMO", memo);
                intent.putExtra("INDEX", index);
                setResult(RESULT_OK, intent);
                finish();
        }
    }

}