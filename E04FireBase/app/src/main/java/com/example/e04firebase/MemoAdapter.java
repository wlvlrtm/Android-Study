package com.example.e04firebase;

import android.content.Context;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {
    class ViewHolder extends RecyclerView.ViewHolder
                     implements View.OnClickListener,
                                CompoundButton.OnCheckedChangeListener {
        TextView textView1, textView2;
        CheckBox checkBox;

        public ViewHolder(View view) {
            super(view);

            textView1 = view.findViewById(R.id.textView1);
            textView2 = view.findViewById(R.id.textView2);

            textView1.setOnClickListener(this::onClick);
            textView2.setOnClickListener(this::onClick);

            checkBox = view.findViewById(R.id.checkBox);
            checkBox.setOnCheckedChangeListener(this::onCheckedChanged);
        }

        public void setData(int index) {
            Memo memo = arrayList.get(index);
            textView1.setText(memo.getTitle());
            textView2.setText(memo.getDateFormatted());
            checkBox.setChecked(memo.isChecked());
        }

        @Override
        public void onClick(View view) {
            int index = super.getAdapterPosition();

            MemoListActivity activity = (MemoListActivity)textView1.getContext();
            activity.onMemoClicked(index);
        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            int index = super.getAdapterPosition();

            Memo memo = arrayList.get(index);
            memo.setChecked(b);
        }
    }


    LayoutInflater inflater;
    ArrayList<Memo> arrayList;

    // Constructor
    public MemoAdapter(Context context, ArrayList<Memo> arrayList) {
        this.inflater = LayoutInflater.from(context);
        this.arrayList = arrayList;
    }

    @Override
     public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(R.layout.memo, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int index) {
        viewHolder.setData(index);
    }
}
