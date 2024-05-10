package com.example.lista.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lista.R;
import com.example.lista.activity.MainActivity;
import com.example.lista.model.MyItem;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {
    MainActivity mainActivity;
    List<MyItem> itens;
    public MyAdapter(MainActivity mainActivity, List<MyItem> itens){
        this.mainActivity = mainActivity;
        this.itens=itens;
    }
    @Override
    public void onBindVIewHolder(@NonNull RecyclerView.ViewHolder holder, int position){
        MyItem myItem = itens.get(position);
        View v = holder.itemView;
        ImageView imvfoto = v.findViewById(R.id.imvPhoto);
        imvfoto.setImageURI(myItem.foto);

        TextView tvTitle = v.findViewById(R.id.tvTitle);
        tvTitle.setText(myItem.title);

        TextView tvdesc = v.findViewById(R.id.tvDesc);
        tvdesc.setText(myItem.description);
    }

    @Override
    public int getItemCount(){
        return itens.size();
    }
}
