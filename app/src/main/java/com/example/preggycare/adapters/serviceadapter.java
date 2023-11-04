package com.example.preggycare.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.preggycare.R;
import com.example.preggycare.listners.servicelistener;
import com.example.preggycare.modelclass.servicemodel;

import java.util.List;

public class serviceadapter extends RecyclerView.Adapter<serviceadapter.serviceviewholder> {

    List<servicemodel> serlist;

    com.example.preggycare.listners.servicelistener servicelistener;

    public serviceadapter(List<servicemodel> serlist, servicelistener servicelistener) {
        this.serlist = serlist;
        this.servicelistener = servicelistener;
    }

    @NonNull
    @Override
    public serviceadapter.serviceviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new serviceviewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.seritem,null,false));
    }

    @Override
    public void onBindViewHolder(@NonNull serviceadapter.serviceviewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.sertext.setText(serlist.get(position).getServices());

        holder.relay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicelistener.onitemclick(serlist.get(position),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return serlist.size();
    }
    public int getItemViewType(int position){
        return position;
    }

    public class serviceviewholder extends RecyclerView.ViewHolder{

        EditText sertext;

       LinearLayout relay;


        public serviceviewholder(@NonNull View itemView) {
            super(itemView);

            sertext = itemView.findViewById(R.id.sernote);
            relay = itemView.findViewById(R.id.serlay);

        }
    }
}
