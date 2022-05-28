package com.example.e04firebase;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;

public class MemoListActivity extends AppCompatActivity {

    MemoAdapter memoAdapter;
    ArrayList<Memo> arrayList = new ArrayList<>();
    ArrayList<String> keyList = new ArrayList<>();
    ActivityResultLauncher<Intent> activityResultLauncher;
    DatabaseReference item;

    ChildEventListener firebaseListener = new ChildEventListener() {
        private int findIndex(String key) {
            return Collections.binarySearch(keyList, key);
        }

        @Override   // Item Add
        public void onChildAdded(@NonNull DataSnapshot snapshot,
                                 @Nullable String previousChildName) {
            String key = snapshot.getKey();
            Memo memo = snapshot.getValue(Memo.class);

            arrayList.add(memo);
            keyList.add(key);

            memoAdapter.notifyItemInserted(arrayList.size()-1);
        }

        @Override   // Item Change
        public void onChildChanged(@NonNull DataSnapshot snapshot,
                                   @Nullable String previousChildName) {
            String key = snapshot.getKey();
            int index = findIndex(key);
            Memo memo = snapshot.getValue(Memo.class);

            arrayList.set(index, memo);

            memoAdapter.notifyItemChanged(index);
        }

        @Override   // Item Remove
        public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            String key = snapshot.getKey();
            int index = findIndex(key);

            arrayList.remove(index);
            keyList.remove(index);

            memoAdapter.notifyItemRemoved(index);
        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot snapshot,
                                 @Nullable String previousChildName) {
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_list);

        /////

        memoAdapter = new MemoAdapter(this, arrayList);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(memoAdapter);

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent intent = result.getData();
                            Memo memo = (Memo)intent.getSerializableExtra("MEMO");
                            Integer index = (Integer)intent.getSerializableExtra("INDEX");

                            if (index == null) {    // ADD
                                String key = item.push().getKey();
                                item.child(key).setValue(memo);
                            }
                            else {  // EDIT
                                String key = keyList.get(index);
                                item.child(key).setValue(memo);
                            }
                            memoAdapter.notifyDataSetChanged();
                        }
                    }
                }
        );
        this.item = FirebaseDatabase.getInstance().getReference("item02");
        this.item.addChildEventListener(firebaseListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_memo_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_create) {
            Intent intent = new Intent(this, MemoActivity.class);
            activityResultLauncher.launch(intent);
        }
        else if (id == R.id.action_remove) {
            removeMemos();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void removeMemos() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.confirm);
        builder.setMessage(R.string.doYouWantToDelete);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int index) {
                for (int i = 0; i < arrayList.size(); ++i) {
                    if (arrayList.get(i).isChecked()) {
                        item.child(keyList.get(i)).removeValue();
                    }
                }
            }
        });
        builder.setNegativeButton(R.string.no, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onMemoClicked(int index) {
        Intent intent = new Intent(this, MemoActivity.class);
        Memo memo = arrayList.get(index);
        intent.putExtra("MEMO", memo);
        intent.putExtra("INDEX", index);
        activityResultLauncher.launch(intent);
    }
}