package com.android.mfcolak.nuevoproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailHolder> {
    private Context context;
    private List<Body> bodyList;

    public DetailAdapter(Context context , List<Body> bodies){
        this.context = context;
        bodyList = bodies;
    }
    @NonNull
    @Override
    public DetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_detail , parent , false);
        return new DetailHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailHolder holder, int position) {

        Body poster = bodyList.get(position);
        holder.body.setText(poster.getBody());


    }

    @Override
    public int getItemCount() {
        return bodyList.size();
    }

    public class DetailHolder extends RecyclerView.ViewHolder{

        TextView body;
        ConstraintLayout constraintLayout;

        public DetailHolder(@NonNull View itemView) {
            super(itemView);

            body = itemView.findViewById(R.id.boddy_id);
            constraintLayout = itemView.findViewById(R.id.detail_layout);
        }
    }
}
