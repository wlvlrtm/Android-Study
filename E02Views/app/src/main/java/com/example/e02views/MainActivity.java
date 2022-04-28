package com.example.e02views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override   // Action Bar Create
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);  // menu_main.xml, menu View Obj
        return true;
    }


    @Override   // if Menu is Clicked
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_signUp) {
            startActivity(new Intent(this, SignUpActivity.class));
            return true;
        }
        else if (id == R.id.action_memo) {
            startActivity(new Intent(this, MemoActivity.class));
            return true;
        }
        else if (id == R.id.action_button) {
            startActivity(new Intent(this, ButtonActivity.class));
            return true;
        }
        else if (id == R.id.action_checkboxes) {
            startActivity(new Intent(this, CheckboxesActivity.class));
            return true;
        }
        else if (id == R.id.action_spinners) {
            startActivity(new Intent(this, SpinnersActivity.class));
            return true;
        }
        else if (id == R.id.action_alerts) {
            startActivity(new Intent(this, AlertsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}