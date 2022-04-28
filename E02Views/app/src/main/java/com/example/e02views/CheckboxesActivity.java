package com.example.e02views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class CheckboxesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkboxes);

        /////

        final CheckBox checkBox1 = findViewById(R.id.checkBox1);
        final Switch switch1 = findViewById(R.id.switch1);

        CompoundButton.OnCheckedChangeListener listener1 =
                new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                String str = String.format("%s : %b", compoundButton.getText(), isChecked);

                Toast.makeText(CheckboxesActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        };

        checkBox1.setOnCheckedChangeListener(listener1);
        switch1.setOnCheckedChangeListener(listener1);

        RadioGroup.OnCheckedChangeListener listener2 =
                new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                RadioButton radioButton = findViewById(id);
                String str = radioButton.getText().toString();

                Toast.makeText(CheckboxesActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        };

        final RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(listener2);

        RadioGroup.OnCheckedChangeListener listener3 =
                new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                int drawableId = 0;

                switch (id) {
                    case R.id.radioButton_cat:
                        drawableId = R.drawable.animal_cat_large;
                        break;
                    case R.id.radioButton_dog:
                        drawableId = R.drawable.animal_dog_large;
                        break;
                    case R.id.radioButton_owl:
                        drawableId = R.drawable.animal_owl_large;
                        break;
                }
                ImageView imageView = findViewById(R.id.imageView);
                imageView.setImageResource(drawableId);
            }
        };

        final RadioGroup radioGroup2 = findViewById(R.id.radioGroup2);
        radioGroup2.setOnCheckedChangeListener(listener3);

        Button button_save = findViewById(R.id.button_save);
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = "checkBox1=" + checkBox1.isChecked() + " ";
                s += "switch1=" + switch1.isChecked() + " ";
                s += "radioGroup1=";
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.radioButton_Red: s += "red "; break;
                    case R.id.radioButton_Green: s += "green "; break;
                    case R.id.radioButton_Blue: s += "blue "; break;
                }
                switch (radioGroup2.getCheckedRadioButtonId()) {
                    case R.id.radioButton_cat: s += "cat"; break;
                    case R.id.radioButton_dog: s += "dog"; break;
                    case R.id.radioButton_owl: s += "owl"; break;
                }
                Toast.makeText(CheckboxesActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}