package com.example.q201914140;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class Exam3Activity extends AppCompatActivity {
    Exam3Adapter exam3Adapter;
    ArrayList<Product> productArrayList;
    ActivityResultLauncher<Intent> activityResultLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam3);

        /////


        this.productArrayList = new ArrayList<Product>();     // Save Memos
        this.exam3Adapter = new Exam3Adapter(this, this.productArrayList);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(exam3Adapter);

        this.productArrayList.add(new Product("우유", 2000, 3));
        this.productArrayList.add(new Product("콜라", 1000, 4));

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK) {
                            Intent intent = result.getData();
                            Product product = (Product)intent.getSerializableExtra("PRODUCT");

                            productArrayList.add(product);

                            exam3Adapter.notifyDataSetChanged();
                        }
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_exam3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add) {
            Intent intent = new Intent(this, ProductActivity.class);
            activityResultLauncher.launch(intent);
        }
        else if (id == R.id.delete) {
            remove();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void remove() {
        if(productArrayList.size() >= 1) {
            productArrayList.remove(productArrayList.size()-1);
            exam3Adapter.notifyDataSetChanged();
        }
    }

    /*
    public void onItemClicked(int index) {
        Product product = productArrayList.get(index);

        Intent intent = new Intent(this, Exam3Activity.class);
        intent.putExtra("PRODUCT", product);

        activityResultLauncher.launch(intent);
    }
    */
}