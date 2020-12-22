package com.roadhelp.border;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BorderPunktAdapter extends RecyclerView.Adapter<BorderPunktAdapter.BorderViewHolder> {

    ArrayList<BorderPunktItem> borderPunktItems;
    Context context;
    public BorderPunktAdapter(ArrayList<BorderPunktItem> borderPunktItems, Context context){
        this.borderPunktItems = borderPunktItems;
        this.context = context;
    }

    @NonNull
    @Override
    public BorderPunktAdapter.BorderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.border_punkt_item, parent, false);
        BorderViewHolder borderViewHolder = new BorderViewHolder(view);
        return borderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BorderViewHolder holder, int position) {
        BorderPunktItem borderPunktItem = borderPunktItems.get(position);
        holder.punktImageView.setImageResource(borderPunktItem.getImageRosource());
        holder.textView_title.setText(borderPunktItem.getTitle());
//        holder.textView_description.setText(borderPunktItem.getDescription());

    }


    @Override
    public int getItemCount() {
        return borderPunktItems.size();
    }

    class BorderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView punktImageView;
        public TextView textView_title;
        public TextView textView_description;

        public BorderViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            punktImageView = itemView.findViewById(R.id.imagePunktView);
            textView_title = itemView.findViewById(R.id.titleImageView);
//            textView_description = itemView.findViewById(R.id.descriptionImageView);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            BorderPunktItem borderPunktItem = borderPunktItems.get(position);

            Intent intent = new Intent(context, TabbedPunktActivity.class);

            //intent.putExtra("imageResource", borderPunktItem.getImageRosource());
            intent.putExtra("title", borderPunktItem.getTitle());       //передаём title в интент в TabbedPunktActivity
            intent.putExtra("descr", borderPunktItem.getDescription());
            intent.putExtra("cameraUrl", borderPunktItem.getCameraUrl());
            context.startActivity(intent);
        }
    }


}
