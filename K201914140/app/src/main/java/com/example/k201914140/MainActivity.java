package com.example.k201914140;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /////

        setActivityResultLauncher("NOW");
    }

    public void btn_Clicked(View view) {
        Class classObj = null;

        switch(view.getId()) {
            case R.id.btn_Test1:
                classObj = Test1Activity.class;
                break;
            case R.id.btn_Test2:
                classObj = Test2Activity.class;
                break;
        }

        Intent intent = new Intent(this, classObj);
        activityResultLauncher.launch(intent);
    }

    public void setActivityResultLauncher(String extraName) {
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            TextView textView = findViewById(R.id.textView);
                            Intent intent = result.getData();
                            String date = (String)intent.getSerializableExtra(extraName);

                            textView.setText(date);
                        }
                    }
                }
        );
    }
}