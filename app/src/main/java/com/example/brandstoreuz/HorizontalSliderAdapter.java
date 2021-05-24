package com.example.brandstoreuz;


import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HorizontalSliderAdapter extends RecyclerView.Adapter<HorizontalSliderAdapter.SliderViewHolder> {

    private final List<Drawable> sliderItemsList;

    public HorizontalSliderAdapter(List<Drawable> sliderItemsList) {
        this.sliderItemsList = sliderItemsList;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.sliderresource, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SliderViewHolder holder, int position) {
        holder.imageview.setImageDrawable(sliderItemsList.get(position));
    }


    @Override
    public int getItemCount() {
        return sliderItemsList.size();
    }

    static class SliderViewHolder extends RecyclerView.ViewHolder{
        private final ImageView imageview;

        public SliderViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageview = itemView.findViewById(R.id.itemimage);
        }
    }

}