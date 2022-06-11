package com.example.e05photo;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.example.e05photo.databinding.ActivityPhoto3Binding;

import java.io.File;

public class Photo3Activity extends AppCompatActivity {

    private ActivityPhoto3Binding binding;

    File directory;
    File[] files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create View Objects Using Layout Resource; content_scrolling.xml
        binding = ActivityPhoto3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = binding.btnTakePhoto;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /////

        directory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        files = directory.listFiles();

        FileRecyclerView2Adapter fileRecyclerView2Adapter = new FileRecyclerView2Adapter(this, files);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(fileRecyclerView2Adapter);
    }

    public void onItemClicked(int index) {
        Toast.makeText(this, "index: " + index, Toast.LENGTH_SHORT).show();
    }
}