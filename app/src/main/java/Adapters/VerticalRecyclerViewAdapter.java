package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brandstoreuz.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import Adapters.Models.HorizontalModel;
import Adapters.Models.VerticalModel;

public class VerticalRecyclerViewAdapter extends RecyclerView.Adapter<VerticalRecyclerViewAdapter.VerticalRVViewHolder> {
    Context context;
    ArrayList<VerticalModel> arrayList;

    public VerticalRecyclerViewAdapter(Context context, ArrayList<VerticalModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @NotNull
    @Override
    public VerticalRVViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical,parent,false);
        return new VerticalRVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull VerticalRecyclerViewAdapter.VerticalRVViewHolder holder, int position) {
        VerticalModel verticalModel = arrayList.get(position);
        String title  = verticalModel.getTitle();
        ArrayList<HorizontalModel> singleItem = verticalModel.getArrayList();

        holder.textViewTitle.setText(title);
        HorizontalRecyclerViewAdapter horizontalRecyclerViewAdapter = new HorizontalRecyclerViewAdapter(context,singleItem);

        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

        holder.recyclerView.setAdapter(horizontalRecyclerViewAdapter);

        holder.btnMore.setOnClickListener(v -> Toast.makeText(context,verticalModel.getTitle(),Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class VerticalRVViewHolder extends RecyclerView.ViewHolder{

        RecyclerView recyclerView;
        TextView textViewTitle;
        ImageView btnMore;

        public VerticalRVViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            recyclerView = (RecyclerView)itemView.findViewById(R.id.recyclerview1);
            textViewTitle = (TextView) itemView.findViewById(R.id.categories);
            btnMore = (ImageView) itemView.findViewById(R.id.moreButton);
        }
    }
}
