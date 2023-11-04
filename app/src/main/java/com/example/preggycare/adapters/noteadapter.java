package com.example.preggycare.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.preggycare.R;
import com.example.preggycare.listners.NoteListner;
import com.example.preggycare.modelclass.usernote;

import java.util.List;

public class noteadapter extends RecyclerView.Adapter<noteadapter.noteviewholder> {

    List<usernote> notelist;
    NoteListner noteListner;

    public noteadapter(List<usernote> notes, NoteListner noteListener) {
        this.notelist = notes;
        this.noteListner = noteListener;
    }

    @NonNull
    @Override
    public noteadapter.noteviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new noteviewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.noteitem,null,false));
    }

    @Override
    public void onBindViewHolder(@NonNull noteadapter.noteviewholder holder, @SuppressLint("RecyclerView") int position) {

        holder.title.setText(notelist.get(position).getTitle());
        holder.descrip.setText(notelist.get(position).getNotes());
        final int itemposition = position;
        holder.relativelay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    noteListner.onNoteClicked(notelist.get(itemposition),itemposition);
            }
        });

    }

    @Override
    public int getItemCount() {
        return notelist.size();
    }

    public class noteviewholder extends RecyclerView.ViewHolder{

        EditText title,descrip;
       CardView relativelay;
        public noteviewholder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.texttitle);
            descrip = itemView.findViewById(R.id.textnote);
            relativelay = itemView.findViewById(R.id.relativelay);

        }
    }


}
