package com.example.e03list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class RecyclerView2Activity extends AppCompatActivity {
    RecyclerView2Adapter recyclerView2Adapter;
    ArrayList<Memo2> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view2);

        /////

        arrayList = new ArrayList<Memo2>();
        arrayList.add(new Memo2("one", new Date()));
        arrayList.add(new Memo2("two", new Date()));

        recyclerView2Adapter = new RecyclerView2Adapter(this, arrayList);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(recyclerView2Adapter);

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                EditText editText = (EditText) findViewById(R.id.editText);
                String str = editText.getText().toString();

                editText.setText("");

                arrayList.add(new Memo2(str, new Date()));

                recyclerView2Adapter.notifyDataSetChanged();
            }
        });
    }


    @Override   // Action Bar Create
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recycler_view2, menu);
        return true;
    }


    @Override   // remove Btn Click
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_remove) {
            removeItem();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void removeItem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.confirm);
        builder.setMessage(R.string.doYouWantToDelete);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Iterator<Memo2> iterator = arrayList.iterator();
                while(iterator.hasNext()) {
                    if(iterator.next().isChecked()) {
                        iterator.remove();
                    }
                }
                recyclerView2Adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton(R.string.no, null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}