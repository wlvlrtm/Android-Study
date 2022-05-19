package com.example.e03list;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
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
    ActivityResultLauncher<Intent> activityResultLauncher;
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
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerView3Adapter);

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent intent = result.getData();
                            Memo3 memo = (Memo3)intent.getSerializableExtra("MEMO");
                            Integer index = (Integer)intent.getSerializableExtra("INDEX");
                            if(index == null) {
                                arrayList.add(memo);
                            }
                            else {
                                arrayList.set(index, memo);
                            }

                            recyclerView3Adapter.notifyDataSetChanged();
                        }
                    }
                }
        );
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
            activityResultLauncher.launch(intent);
        }
        else if (id == R.id.action_remove) {
            removeMemos();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onMemoClicked(int index) {
        Intent intent = new Intent(this, Memo3Activity.class);
        Memo3 memo = arrayList.get(index);
        intent.putExtra("MEMO", memo);
        intent.putExtra("INDEX", index);
        activityResultLauncher.launch(intent);
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