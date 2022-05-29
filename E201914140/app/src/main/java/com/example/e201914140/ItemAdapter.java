package com.example.e201914140;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView textViewName;
        TextView textViewNum;

        public ViewHolder(View view) {
            super(view);

            textViewName = view.findViewById(R.id.textView_Name);
            textViewNum = view.findViewById(R.id.textView_Num);

            textViewName.setOnClickListener(this::onClick);
            textViewNum.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View view) {
            if(itemClick != null) {
                int index = super.getAdapterPosition();
                itemClick.onItemClick(view, index);
            }
//            int index = super.getAdapterPosition();
//            itemArrayList.remove(index);
//            ItemAdapter.this.notifyDataSetChanged();
        }

        public void setData(int position) {
            Item item = itemArrayList.get(position);

            this.textViewName.setText(item.getName());
            this.textViewNum.setText(item.getNum());
        }
    }

    ItemClick itemClick;
    ArrayList<Item> itemArrayList;
    LayoutInflater inflater;

    interface ItemClick {
        void onItemClick(View view, int position);
    }


    public ItemAdapter(Context context, ArrayList<Item> itemArrayList) {
        this.inflater = LayoutInflater.from(context);
        this.itemArrayList = itemArrayList;
    }

    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }
}
