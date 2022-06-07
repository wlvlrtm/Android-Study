package com.example.h201914140;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NoteActivity extends AppCompatActivity {
    EditText editText_Header;
    EditText editText_Body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        /////

        this.editText_Header = findViewById(R.id.editText_Header);
        this.editText_Body = findViewById(R.id.editText_Body);
    }

    public void onButtonClicked(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.button_Ok:

                String header = editText_Header.getText().toString();
                String body = editText_Body.getText().toString();

                Note note = new Note(header, body);

                Intent intent = new Intent();
                intent.putExtra("NOTE", note);

                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}