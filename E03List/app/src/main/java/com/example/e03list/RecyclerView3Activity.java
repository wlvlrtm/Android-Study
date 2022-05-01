package com.example.e03list;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;

public class RecyclerView3Activity extends AppCompatActivity {

    RecyclerView3Adapter recyclerView3Adapter;
    ArrayList<Memo3> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view3);

        /////

        arrayList = new ArrayList<Memo3>();
        recyclerView3Adapter = new RecyclerView3Adapter(this, arrayList);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(recyclerView3Adapter);
    }

    @Override   // Action Bar Create
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recycler_view3, menu);
        return true;
    }

    @Override   // Action Bar Item Click
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_create) {
            Intent intent = new Intent(this, Memo3Activity.class);
            startActivityForResult(intent, 0);
        }
        else if (id == R.id.action_remove) {
            removeMemos();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override   // WHEN '저장' btn Clicked -> CALL
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK) {
            Memo3 memo = (Memo3)intent.getSerializableExtra("MEMO");
            arrayList.add(memo);

            recyclerView3Adapter.notifyDataSetChanged();
        }
    }

    private void removeMemos() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.confirm);
        builder.setMessage(R.string.doYouWantToDelete);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int index) {
                ListIterator<Memo3> iterator = arrayList.listIterator();
                while (iterator.hasNext()) {
                    if (iterator.next().isChecked()) {
                        iterator.remove();
                    }
                }
                recyclerView3Adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton(R.string.no, null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}