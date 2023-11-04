package com.example.preggycare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.preggycare.R;
import com.example.preggycare.Recyclemodel;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    ArrayList<Recyclemodel> list;
    Context context;

    public Adapter(ArrayList<Recyclemodel> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.faqlist,null,false);
        return new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        holder.faqtitle.setText(list.get(position).title);
        holder.faqdescription.setText(list.get(position).description);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView faqtitle;
        TextView faqdescription;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            faqtitle = itemView.findViewById(R.id.faqtitle);
            faqdescription = itemView.findViewById(R.id.faqdescription);




        }
    }



}