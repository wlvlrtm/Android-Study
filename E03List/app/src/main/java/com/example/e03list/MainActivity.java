package com.example.e03list;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /////

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent intent = result.getData();
                            Memo3 memo = (Memo3)intent.getSerializableExtra("MEMO");
                            String str = String.format(
                                    "<h1>%s</h1><p>%s</p><p style='color: blue'>%s</p>",
                                    memo.getTitle(), memo.getDateFormatted(), memo.getContent()
                            );
                            TextView textView = findViewById(R.id.textView);
                            textView.setText(Html.fromHtml(str));
                        }
                    }
                }
        );
    }

    public void btnListViewClicked(View button) {
        Intent intent = new Intent(this, ListViewActivity.class);
        startActivity(intent);
    }

    public void btnRecyclerView1Clicked(View button) {
        Intent intent = new Intent(this, RecyclerView1Activity.class);
        startActivity(intent);
    }

    public void btnRecyclerView2Clicked(View button) {
        Intent intent = new Intent(this, RecyclerView2Activity.class);
        startActivity(intent);
    }

    public void btnRecyclerView3Clicked(View button) {
        Intent intent = new Intent(this, RecyclerView3Activity.class);
        startActivity(intent);
    }

    public void btnMemo3Clicked(View button) {
        Intent intent = new Intent(this, Memo3Activity.class);
        activityResultLauncher.launch(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            Memo3 memo3 = (Memo3)data.getSerializableExtra("MEMO");
            String str = String.format(
                    "<h1>%s</h1><p>%s</p><p style='color: blue'>%s</p>",
                    memo3.getTitle(), memo3.getDateFormatted(), memo3.getContent()
            );
            TextView textView = findViewById(R.id.textView);
            textView.setText(Html.fromHtml(str));
        }
    }
}