package com.example.e02views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    boolean isGoodToGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        /////

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUpActivity.this.isGoodToGo = true;

                EditText editText_ID = findViewById(R.id.editText_lD);
                EditText editText_PW = findViewById(R.id.editText_PW);
                EditText editText_PW2 = findViewById(R.id.editText_PW2);
                EditText editText_EM = findViewById(R.id.editText_EM);

                String id = editText_ID.getText().toString();
                if (EmptyStringChecker.isEmptyOrWhiteSpace(id)) {
                    editText_ID.setError("로그인 아이디를 입력하세요.");
                    SignUpActivity.this.isGoodToGo = false;
                }

                String pw = editText_PW.getText().toString();
                if (EmptyStringChecker.isEmptyOrWhiteSpace(pw)) {
                    editText_PW.setError("비밀번호를 입력하세요.");
                    SignUpActivity.this.isGoodToGo = false;
                }

                String pw2 = editText_PW2.getText().toString();
                if(!pw2.equals(pw)) {
                    editText_PW2.setError("비밀번호가 일치하지 않습니다.");
                    SignUpActivity.this.isGoodToGo = false;
                }

                String em = editText_EM.getText().toString();

                // USER DATA 2 SERVER //

                if (SignUpActivity.this.isGoodToGo) {
                    String clearMS = ("회원가입 성공!" + " " + id + " " + em);
                    Toast.makeText(SignUpActivity.this, clearMS, Toast.LENGTH_SHORT).show();

                    startActivity(
                            new Intent(SignUpActivity.this, MemoActivity.class)
                    );
                }



            }
        });
    }
}