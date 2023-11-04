package com.example.preggycare.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.preggycare.R;
import com.example.preggycare.listners.doclistener;
import com.example.preggycare.modelclass.docterlist;

import java.util.List;

public class docadapter extends RecyclerView.Adapter<docadapter.docviewholder> {


List<docterlist> list;

doclistener doclistner;

    public docadapter( List<docterlist> list,doclistener doclistner) {

        this.list = list;
        this.doclistner = doclistner;
    }

    @NonNull
    @Override
    public docadapter.docviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new docviewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.docitem,null,false));
    }

    @Override
    public void onBindViewHolder(@NonNull docadapter.docviewholder holder, @SuppressLint("RecyclerView") int position) {


        holder.setnotes(list.get(position));

        holder.dialoglay.setOnClickListener(new View.OnClickListener() {
//            int position = holder.getBindingAdapterPosition();
            @Override
            public void onClick(View v) {
                doclistner.onclick(list.get(position),position);
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public int getItemViewType(int position){
        return position;
    }

    public class docviewholder extends RecyclerView.ViewHolder {

        TextView nametxt,pntxt;

        LinearLayout dialoglay;


        public docviewholder(@NonNull View v) {
            super(v);
            nametxt = v.findViewById(R.id.nametxt);
            pntxt = v.findViewById(R.id.sptxt);
            dialoglay = v.findViewById(R.id.dialoglay);


        }

        public void setnotes(docterlist docterlist) {
           nametxt.setText(docterlist.getName());
           pntxt.setText(docterlist.getSpecification());
        }
    }


}
