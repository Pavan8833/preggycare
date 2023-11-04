package com.example.preggycare.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.preggycare.listners.PdfItemClickListener;
import com.example.preggycare.R;
import com.example.preggycare.modelclass.userpdf;

import java.util.List;

public class pdfadapter extends RecyclerView.Adapter<pdfadapter.pdfViewholder> {
    List<userpdf> pdflist;
    LayoutInflater inflater;
    PdfItemClickListener itemClickListener;

    public pdfadapter(Context context,PdfItemClickListener itemClickListener) {
        inflater = LayoutInflater.from(context);
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public pdfadapter.pdfViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.pdfitem,parent,false);
        return new pdfViewholder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull pdfadapter.pdfViewholder holder, int position) {
        if(pdflist != null){
            userpdf userpdf = pdflist.get(position);
            holder.pdftxtview.setText(userpdf.getFilename());
            holder.itemView.setOnClickListener(v ->itemClickListener .onPdfItemClick(userpdf));
        }else{
            holder.pdftxtview.setText("no pdfs availble");
        }

    }

    @Override
    public int getItemCount() {
        if (pdflist != null) {
            return pdflist.size();
        } else {
            return 0; // Handle null case gracefully
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setpdfFiles(List<userpdf> pdflist){
        this.pdflist = pdflist;
        notifyDataSetChanged();

    }

    public static class pdfViewholder extends RecyclerView.ViewHolder {

        TextView pdftxtview;

        public pdfViewholder(@NonNull View itemView) {
            super(itemView);
            pdftxtview = itemView.findViewById(R.id.pdffilename);
        }
    }
}
