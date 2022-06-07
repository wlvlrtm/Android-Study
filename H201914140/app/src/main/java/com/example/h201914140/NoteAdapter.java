package com.example.h201914140;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView_Header;
        TextView textView_body;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.textView_Header = itemView.findViewById(R.id.textView_Header);
            this.textView_body = itemView.findViewById(R.id.textView_Body);

            this.textView_Header.setOnClickListener(this::onClick);
            this.textView_body.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View view) {
            int index = super.getAdapterPosition();

            MainActivity mainActivity = (MainActivity) textView_Header.getContext();
            mainActivity.onNoteClicked(index);
        }

        public void setData(int position) {
            Note note = noteArrayList.get(position);
            textView_Header.setText(note.getHeader());
            textView_body.setText(note.getBody());
        }
    }


    ArrayList<Note> noteArrayList;
    LayoutInflater inflater;


    // Constructor
    public NoteAdapter(Context context, ArrayList<Note> noteArrayList) {
        this.noteArrayList = noteArrayList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return this.noteArrayList.size();
    }
}
