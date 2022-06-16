package com.example.q201914140;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Exam3Adapter extends RecyclerView.Adapter<Exam3Adapter.ViewHolder> {
    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

        TextView textView_Title;
        TextView textView_Content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_Title = itemView.findViewById(R.id.textView_Title);
            textView_Content = itemView.findViewById(R.id.textView_Content);

            itemView.setOnClickListener(this);
        }

        public void setData(int index) {
            Product product = Exam3Adapter.this.productArrayList.get(index);
            textView_Title.setText(product.getTitle());
            textView_Content.setText(product.getContent());
        }

        @Override
        public void onClick(View view) {
            int index = super.getAdapterPosition(); // Clicked Item's index
            Exam3Activity exam3Activity = (Exam3Activity) textView_Title.getContext();
            //Exam3Activity.onItemClicked(index);
        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        }
    }

    ArrayList<Product> productArrayList;
    LayoutInflater inflater;

    public Exam3Adapter(Context context, ArrayList<Product> productArrayList) {
        this.inflater = LayoutInflater.from(context);
        this.productArrayList = productArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.title, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

}
