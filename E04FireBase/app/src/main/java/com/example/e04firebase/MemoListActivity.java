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
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

public class MemoListActivity extends AppCompatActivity {
    MemoAdapter memoAdapter;
    ArrayList<Memo> memoArrayList;
    ArrayList<String> keyArrayList;
    ActivityResultLauncher<Intent> activityResultLauncher;
    DatabaseReference item02;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_list);

        /////

        this.memoArrayList = new ArrayList<Memo>();     // Save Memos
        this.keyArrayList = new ArrayList<String>();    // Save Keys
        this.memoAdapter = new MemoAdapter(this, this.memoArrayList);

        this.item02 = FirebaseDatabase.getInstance().getReference("item02");
        this.item02.addChildEventListener(new ChildEventListener() {
            @Override   // Memo Add
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String key = snapshot.getKey();
                Memo memo = snapshot.getValue(Memo.class);

                memoArrayList.add(memo);
                keyArrayList.add(key);

                memoAdapter.notifyItemInserted(memoArrayList.size() - 1);
            }

            @Override   // Memo Edit
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String key = snapshot.getKey();
                Memo memo = snapshot.getValue(Memo.class);
                int index = Collections.binarySearch(keyArrayList, key);

                memoArrayList.set(index, memo);

                memoAdapter.notifyItemChanged(index);
            }

            @Override   // Memo Del
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                String key = snapshot.getKey();
                int index = Collections.binarySearch(keyArrayList, key);

                memoArrayList.remove(index);
                keyArrayList.remove(index);

                memoAdapter.notifyItemRemoved(index);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(memoAdapter);

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK) {
                            Intent intent = result.getData();
                            Memo memo = (Memo)intent.getSerializableExtra("MEMO");
                            Integer index = (Integer)intent.getSerializableExtra("INDEX");

                            if (index == null) {    // New Memo Add
                                String key = item02.push().getKey();
                                item02.child(key).setValue(memo);
                            }
                            else {
                                String key = keyArrayList.get(index);
                                item02.child(key).setValue(memo);
                            }
                            //memoAdapter.notifyDataSetChanged();
                        }
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_memo_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_Create) {
            Intent intent = new Intent(this, MemoActivity.class);
            activityResultLauncher.launch(intent);
        }
        else if (id == R.id.action_Remove) {
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
            public void onClick(DialogInterface dialogInterface, int index) {
                for (int i = 0; i < memoArrayList.size(); ++i) {
                    if(memoArrayList.get(i).isChecked()) {
                        item02.child(keyArrayList.get(i)).removeValue();
                    }
                }
            }
        });
        builder.setNegativeButton(R.string.no, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onMemoClicked(int index) {
        Memo memo = this.memoArrayList.get(index);

        Intent intent = new Intent(this, MemoActivity.class);
        intent.putExtra("MEMO", memo);
        intent.putExtra("INDEX", index);

        activityResultLauncher.launch(intent);
    }
}