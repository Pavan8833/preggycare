package com.example.preggycare.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.preggycare.R;
import com.example.preggycare.modelclass.patientmodel;

import java.util.List;

public class patientadapter extends RecyclerView.Adapter<patientadapter.patientviewholder> {

    List<patientmodel> patientList;

    public patientadapter(List<patientmodel> patientList) {
        this.patientList = patientList;
    }

    @NonNull
    @Override
    public patientadapter.patientviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new patientviewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.patientitem,null,false));
    }

    @Override
    public void onBindViewHolder(@NonNull patientadapter.patientviewholder holder, int position) {
//            holder.id.setText(patientList.get(position).getPatientid());
            holder.name.setText(patientList.get(position).getUsername());
            holder.num.setText(patientList.get(position).getNum());

    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    public class patientviewholder extends RecyclerView.ViewHolder {

        TextView id,name,num;

        public patientviewholder(@NonNull View itemView) {
            super(itemView);
//            id = itemView.findViewById(R.id.id);
            name =itemView.findViewById(R.id.name);
            num = itemView.findViewById(R.id.num);



        }
    }
}
