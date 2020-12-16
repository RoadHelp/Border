package com.roadhelp.border;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
        holder.pizzaImageView.setImageResource(borderPunktItem.getImageRosource());
        holder.textView_title.setText(borderPunktItem.getTitle());
//        holder.textView_description.setText(borderPunktItem.getDescription());

    }


    @Override
    public int getItemCount() {
        return borderPunktItems.size();
    }

    class BorderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView pizzaImageView;
        public TextView textView_title;
        public TextView textView_description;

        public BorderViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            pizzaImageView = itemView.findViewById(R.id.imageBallView);
            textView_title = itemView.findViewById(R.id.titleImageView);
//            textView_description = itemView.findViewById(R.id.descriptionImageView);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            BorderPunktItem borderPunktItem = borderPunktItems.get(position);

            Intent intent = new Intent(context, PunktActivity.class);
            intent.putExtra("imageResource", borderPunktItem.getImageRosource());
            intent.putExtra("title", borderPunktItem.getTitle());
            intent.putExtra("descr", borderPunktItem.getDescription());
            intent.putExtra("recipe", borderPunktItem.getRecipe());
            context.startActivity(intent);
        }
    }


}
