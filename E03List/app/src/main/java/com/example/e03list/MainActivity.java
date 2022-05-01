package com.example.e03list;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        startActivityForResult(intent, 0);
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