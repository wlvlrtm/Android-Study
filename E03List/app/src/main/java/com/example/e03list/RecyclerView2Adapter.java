package com.example.e03list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerView2Adapter extends RecyclerView.Adapter<RecyclerView2Adapter.ViewHolder> {
    LayoutInflater layoutInflater;
    ArrayList<Memo2> arrayList;


    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
        TextView textView_title;
        TextView textView_date;
        CheckBox checkBox;

        public ViewHolder(View view) {
            super(view);

            textView_title = view.findViewById(R.id.textView_title);
            textView_date = view.findViewById(R.id.textView_date);
            checkBox = view.findViewById(R.id.checkBox);

            textView_title.setOnClickListener(this);    // onClick()
            textView_date.setOnClickListener(this);     // onClick()
            checkBox.setOnCheckedChangeListener(this);  // onCheckedChanged()
        }

        public void setData(int index) {
            Memo2 memo2 = arrayList.get(index);

            textView_title.setText(memo2.getTitle());
            textView_date.setText(memo2.getDataFormatted());
            checkBox.setChecked(memo2.isChecked());
        }

        @Override   // onClickListener
        public void onClick(View view) {
            int index = super.getAdapterPosition();
            Memo2 memo = arrayList.get(index);
            String str = String.format("index: %d, title: %s", index, memo.getTitle());

            Toast.makeText(view.getContext(), str, Toast.LENGTH_SHORT).show();
        }

        @Override   // onCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            int index = super.getAdapterPosition();
            Memo2 memo = arrayList.get(index);

            memo.setChecked(isChecked);
        }
    }


    // Constructor
    public RecyclerView2Adapter(Context context, ArrayList<Memo2> arrayList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @NonNull
    @Override   // Create a view Obj -> Return ViewHolder
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.memo2, parent, false);
        return new ViewHolder(view);
    }

    @Override   // Data Set
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(position);
    }
}
