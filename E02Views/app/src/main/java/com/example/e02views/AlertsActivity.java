package com.example.e02views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class AlertsActivity extends AppCompatActivity {

    int selectedAnimalIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts);
    }


    public void buttonAD1Clicked(View button) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.saveSuccess);
        builder.setNeutralButton(R.string.close, null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void buttonAD2Clicked(View button) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.confirm);
        builder.setMessage(R.string.doYouWantToDelete);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int index) {
                Toast.makeText(AlertsActivity.this, "삭제됨", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(R.string.no, null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void buttonAD3Clicked(View button) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.selectAnimal);
        builder.setSingleChoiceItems(R.array.animals, selectedAnimalIndex, null);
        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int index) {
                ListView listView = ((AlertDialog)dialogInterface).getListView();
                selectedAnimalIndex = listView.getCheckedItemPosition();
                String msg = selectedAnimalIndex + "번째 항목 선택";

                Toast.makeText(AlertsActivity.this, msg, Toast.LENGTH_SHORT).show();

                int drawableID = 0;
                switch (selectedAnimalIndex) {
                    case 0:
                        drawableID = R.drawable.animal_cat_large;
                        break;
                    case 1:
                        drawableID = R.drawable.animal_dog_large;
                        break;
                    case 2:
                        drawableID = R.drawable.animal_owl_large;
                        break;
                }

                ImageView imageView = findViewById(R.id.imageView);
                imageView.setImageResource(drawableID);
            }
        });
        builder.setNegativeButton(R.string.cancel, null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }


}