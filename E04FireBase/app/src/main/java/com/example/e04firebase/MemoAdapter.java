package com.example.e04firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {
    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
        TextView textView_Title;    // Memo Title
        TextView textView_Date;     // Memo Date
        CheckBox checkBox;          // Memo Checkbox


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.textView_Title = itemView.findViewById(R.id.textView_Title);
            this.textView_Date = itemView.findViewById(R.id.textView_Date);
            this.checkBox = itemView.findViewById(R.id.checkBox);

            this.textView_Title.setOnClickListener(this);
            this.textView_Date.setOnClickListener(this);
            this.checkBox.setOnCheckedChangeListener(this);
        }

        public void setData(int index) {
            Memo memo = MemoAdapter.this.memoArrayList.get(index);
            textView_Title.setText(memo.getTitle());
            textView_Date.setText(memo.getDate().toString());
            checkBox.setChecked(memo.isChecked());
        }

        @Override   // ClickListener
        public void onClick(View view) {
            int index = super.getAdapterPosition(); // Clicked Item's index
            MemoListActivity memoListActivity = (MemoListActivity) textView_Title.getContext();
            memoListActivity.onMemoClicked(index);
        }

        @Override   // CheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            int index = super.getAdapterPosition();
            Memo memo = memoArrayList.get(index);
            memo.setChecked(b);
        }
    }


    ArrayList<Memo> memoArrayList;  // Memo List; MemoListActivity -> MemoAdapter
    LayoutInflater inflater;


    public MemoAdapter(Context context, ArrayList<Memo> memoArrayList) {
        this.inflater = LayoutInflater.from(context);
        this.memoArrayList = memoArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // View Create; Layout = memo.xml
        View view = inflater.inflate(R.layout.memo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return memoArrayList.size();
    }
}
