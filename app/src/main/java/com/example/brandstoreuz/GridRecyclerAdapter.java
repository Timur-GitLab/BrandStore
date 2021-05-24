package com.example.brandstoreuz;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GridRecyclerAdapter extends RecyclerView.Adapter<GridRecyclerAdapter.MyViewHolder> {

    List<Drawable> icons;
    List<String> texts;

    public GridRecyclerAdapter(List<Drawable> icons, List<String> texts) {
        this.icons = icons;
        this.texts = texts;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popularcategorygrid,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        holder.imageView.setImageDrawable(icons.get(position));
        holder.textView.setText(texts.get(position));
    }

    @Override
    public int getItemCount() {
        return icons.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iconpopct);
            textView = itemView.findViewById(R.id.textpopct);
        }
    }
}
