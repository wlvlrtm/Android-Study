package com.example.e04firebase;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Memo implements Serializable {
    final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String title;       // memo.textView1
    String content;     // activity_memo.editText_content
    Date date;          // memo.textView2
    boolean checked;    // memo.checkBox

    public Memo() {
    }

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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getDateFormatted() {
        return format.format(date);
    }
}
