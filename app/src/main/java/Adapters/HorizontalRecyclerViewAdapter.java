package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brandstoreuz.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import Adapters.Models.HorizontalModel;

public class HorizontalRecyclerViewAdapter  extends RecyclerView.Adapter<HorizontalRecyclerViewAdapter.HorizontalRVViewHolder> {

    Context context;
    ArrayList<HorizontalModel> arrayList;

    public HorizontalRecyclerViewAdapter(Context context, ArrayList<HorizontalModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @NotNull
    @Override
    public HorizontalRVViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal,parent,false);
        return new HorizontalRVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HorizontalRecyclerViewAdapter.HorizontalRVViewHolder holder, int position) {
        HorizontalModel horizontalModel = arrayList.get(position);
        holder.icon.setImageDrawable(horizontalModel.getIcon());
        holder.description.setText(horizontalModel.getDescription());
        holder.installment.setText(horizontalModel.getInstallment());
        holder.price.setText(horizontalModel.getPrice());

        holder.itemView.setOnClickListener(v -> Toast.makeText(context,"Item " + horizontalModel.getChildTitle(),Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class HorizontalRVViewHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView description;
        TextView installment;
        TextView price;

        public HorizontalRVViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            description = itemView.findViewById(R.id.description);
            installment= itemView.findViewById(R.id.installment);
            price = itemView.findViewById(R.id.price);
        }
    }
}
