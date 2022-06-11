package com.example.e05photo;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileRecyclerView2Adapter extends RecyclerView.Adapter<FileRecyclerView2Adapter.ViewHolder>  {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView1, textView2, textView3;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            textView1 = view.findViewById(R.id.textView1);
            textView2 = view.findViewById(R.id.textView2);
            textView3 = view.findViewById(R.id.textView3);
            imageView = view.findViewById(R.id.imageView);

            view.setOnClickListener(this::onClick);
        }

        public void setData(int index) {
            File file = files[index];
            textView1.setText(file.getName());
            textView2.setText(dateFormat.format(file.lastModified()));

            String s = String.format("%,d bytes", file.length());
            textView3.setText(s);
            imageView.setImageURI(Uri.fromFile(file));
        }

        @Override
        public void onClick(View view) {
            int index = super.getAdapterPosition();

            Photo3Activity photo3Activity = (Photo3Activity) imageView.getContext();
            photo3Activity.onItemClicked(index);
        }
    }


    LayoutInflater layoutInflater;
    File[] files;

    public FileRecyclerView2Adapter(Context context, File[] files) {
        this.layoutInflater = LayoutInflater.from(context);
        this.files = files;
    }

    @Override
    public int getItemCount() {
        return files.length;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.photo_file, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int index) {
        viewHolder.setData(index);
    }
}
