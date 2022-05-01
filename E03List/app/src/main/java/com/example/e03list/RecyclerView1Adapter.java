package com.example.e03list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerView1Adapter extends RecyclerView.Adapter<RecyclerView1Adapter.ViewHolder> {
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder (View view) {
            super(view);
            textView = view.findViewById(R.id.textView);
        }
    }

    LayoutInflater inflater;
    ArrayList<String> arrayList;

    // Constructor //
    public RecyclerView1Adapter(Context context, ArrayList<String> arrayList) {
        this.inflater = LayoutInflater.from(context);   // Context -> LayoutInflater
        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(R.layout.memo1, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(arrayList.get(position));
    }
}
