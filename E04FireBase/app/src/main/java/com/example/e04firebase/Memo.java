package com.example.e04firebase;

import android.widget.CheckBox;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Memo implements Serializable {
    final static SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
    String title;       // Memo Title
    String content;     // Memo Content
    Date date;          // Memo Date
    CheckBox checkBox;  // Memo del Checkbox
    boolean checked;    // Is checkBox Checked?


    // Default Constructor
    public Memo() {
    }

    // Constructor
    public Memo(String title, String content, Date date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public String getDateFormatted() {
        return dataFormat.format(date);
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}