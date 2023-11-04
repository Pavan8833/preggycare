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
import com.example.preggycare.listners.medicinelistner;
import com.example.preggycare.modelclass.usermed;

import java.util.List;

public class medadapter extends RecyclerView.Adapter<medadapter.medviewholder> {

    List<usermed> listmed;

    medicinelistner medListner;

    public medadapter(List<usermed> listmed, medicinelistner medListner) {
        this.listmed = listmed;
        this.medListner = medListner;
    }

    @NonNull
    @Override
    public medadapter.medviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new medviewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.meditem, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull medadapter.medviewholder holder, @SuppressLint("RecyclerView") int position) {
            holder.mednote.setText(listmed.get(position).medname);
            holder.medlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        medListner.onitemClicked(listmed.get(position),position);
                }
            });
            
    }

    @Override
    public int getItemCount() {
        return listmed.size();
    }

    public int getItemViewType(int position){
        return position;
    }

    public class medviewholder extends RecyclerView.ViewHolder{

        EditText mednote;
        LinearLayout medlay;
        public medviewholder(@NonNull View itemView) {
            super(itemView);
            mednote = itemView.findViewById(R.id.mednote);
            medlay = itemView.findViewById(R.id.medlay);
        }
    }
}
