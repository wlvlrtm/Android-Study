package com.example.h201914140;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    NoteAdapter noteAdapter;
    ArrayList<Note> noteArrayList;
    //ArrayList<String> keyArrayList;
    ActivityResultLauncher<Intent> activityResultLauncher;
    //DatabaseReference item03;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /////

        this.noteArrayList = new ArrayList<Note>();
        this.noteAdapter = new NoteAdapter(this, this.noteArrayList);
        this.recyclerView = findViewById(R.id.recyclerView);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(this.noteAdapter);


        this.noteArrayList.add(new Note("하나", "one"));
        this.noteArrayList.add(new Note("둘", "two"));
        this.noteArrayList.add(new Note("셋", "three"));


        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK) {
                            Intent intent = result.getData();
                            Note note = (Note) intent.getSerializableExtra("NOTE");

                            noteArrayList.add(note);

                            noteAdapter.notifyDataSetChanged();
                        }
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.item_Add:
                Intent intent = new Intent(this, NoteActivity.class);
                activityResultLauncher.launch(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onNoteClicked(int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("확인");
        builder.setMessage("삭제하시곘습니까?");
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                noteArrayList.remove(index);
                noteAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("아니오", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}