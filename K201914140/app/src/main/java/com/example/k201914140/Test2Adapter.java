package com.example.k201914140;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Test2Adapter extends RecyclerView.Adapter<Test2Adapter.ViewHolder> {
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.textView);
        }
    }

    ItemClick itemClick;

    interface ItemClick {
        void onItemClick (View view, int position);
    }

    public void setItemClick (ItemClick itemClick)
    {
        this.itemClick = itemClick;
    }


    LayoutInflater layoutInflater;
    ArrayList<String> arrayList;

    public Test2Adapter(Context context, ArrayList<String> arrayList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.time, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int index) {
        viewHolder.textView.setText(arrayList.get(index));

        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (itemClick != null)
                {
                    itemClick.onItemClick(view, index);
                }

            }
        });
    }
}
