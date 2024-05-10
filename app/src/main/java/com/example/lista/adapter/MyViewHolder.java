package com.example.lista.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder{
    public MyViewHolder(@NonNull View itemView){
        super(itemView);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreatViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        View v = inflater.inflate(R.Layout.item_list,parent,false);
        return new MyViewHolder(v);
    }
}
