package com.example.brandstoreuz.fragments.home.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brandstoreuz.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GridBrandsRecyclerAdapter extends RecyclerView.Adapter<GridBrandsRecyclerAdapter.BViewHolder> {

    List<Drawable> brandIcons;
    Context context;

    public GridBrandsRecyclerAdapter(Context context, List<Drawable> brandIcons) {
        this.brandIcons = brandIcons;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public BViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brands,parent,false);
        return new BViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BViewHolder holder, int position) {
        holder.imageViewBrand.setImageDrawable(brandIcons.get(position));

        holder.itemView.setOnClickListener(v -> Toast.makeText(context,"Brand: " + position,Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return brandIcons.size();
    }

    public static class BViewHolder extends RecyclerView.ViewHolder{

        ImageView imageViewBrand;

        public BViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageViewBrand = itemView.findViewById(R.id.brandIcon);
        }
    }
}
