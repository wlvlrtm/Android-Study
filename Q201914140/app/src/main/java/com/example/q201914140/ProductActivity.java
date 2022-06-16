package com.example.q201914140;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);


    }

    public void button_clicked(View view) {
        EditText editText_Title = findViewById(R.id.editText_Title);
        EditText editText_Count = findViewById(R.id.editText_Count);
        EditText editText_Price = findViewById(R.id.editText_Price);

        String title = editText_Title.getText().toString();
        String __count = editText_Count.getText().toString();
        String __price = editText_Price.getText().toString();

        int price = Integer.parseInt(__price);
        int count = Integer.parseInt(__count);

        Product product = new Product(title, price, count);

        Intent intent = new Intent();
        intent.putExtra("PRODUCT", product);

        setResult(RESULT_OK, intent);
        finish();
    }

}