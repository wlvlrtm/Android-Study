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

public class RecyclerView3Adapter extends RecyclerView.Adapter<RecyclerView3Adapter.ViewHolder> {
    LayoutInflater inflater;
    ArrayList<Memo3> arrayList;

    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
        TextView textView_title;
        TextView textView_date;
        CheckBox checkBox;

        // Constructor
        public ViewHolder(View view) {
            super(view);

            textView_title = view.findViewById(R.id.textView_title);
            textView_date = view.findViewById(R.id.textView_date);
            checkBox = view.findViewById(R.id.checkBox);

            textView_title.setOnClickListener(this);
            textView_date.setOnClickListener(this);
            checkBox.setOnCheckedChangeListener(this);
        }

        public void setData(int index) {
            Memo3 memo = arrayList.get(index);

            textView_title.setText(memo.getTitle());
            textView_date.setText((memo.getDateFormatted()));

            checkBox.setChecked(memo.isChecked());
        }

        @Override   // Click Listener
        public void onClick(View view) {
            int index = super.getAdapterPosition();
            RecyclerView3Activity activity = (RecyclerView3Activity) textView_title.getContext();
            activity.onMemoClicked(index);
        }

        @Override   // Check Change
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            int index = super.getAdapterPosition();
            Memo3 memo = arrayList.get(index);

            memo.setChecked(isChecked);
        }
    }


    // Constructor
    public RecyclerView3Adapter(Context context, ArrayList<Memo3> arrayList) {
        this.inflater = LayoutInflater.from(context);
        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.memo2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(position);
    }
}
