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
import com.example.preggycare.listners.testlistner;
import com.example.preggycare.modelclass.usertest;

import java.util.List;

public class testadapter extends RecyclerView.Adapter<testadapter.testviewholder> {

    List<usertest> testlist;

    testlistner testListner;

    public testadapter(List<usertest> testlist, testlistner testListner) {
        this.testlist = testlist;
        this.testListner = testListner;
    }

    @NonNull
    @Override
    public testviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new testviewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.testitem,null,false));
    }

    @Override
    public void onBindViewHolder(@NonNull testviewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.testnote.setText(testlist.get(position).getTestname());
        holder.testlay.setTag(position);
        holder.testlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int itemPosition = (int) v.getTag();
                testListner.onitemclicked(testlist.get(itemPosition),itemPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return testlist.size();
    }
    public int getItemViewType(int position){
        return position;
    }

    public class testviewholder extends RecyclerView.ViewHolder {

        EditText testnote;
        LinearLayout testlay;
        public testviewholder(@NonNull View itemView) {
            super(itemView);

            testlay = itemView.findViewById(R.id.testlay);
            testnote = itemView.findViewById(R.id.testnote);
        }
    }
}
