package com.omnitracs.tprunner.activities.store;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.omnitracs.tprunner.R;
import com.omnitracs.tprunner.entities.TPInventoryItem;

import java.util.List;

public class TPInventoryAdapter extends RecyclerView.Adapter<TPInventoryAdapter.TPInventoryViewHolder> {

    private final List<TPInventoryItem> mTPInventoryItems;

    TPInventoryAdapter(List<TPInventoryItem> stores) {
        mTPInventoryItems = stores;
    }

    @NonNull
    @Override
    public TPInventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_tp_inventory_list_item, parent, false);
        return new TPInventoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TPInventoryViewHolder holder, int position) {
        holder.bindData(mTPInventoryItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mTPInventoryItems.size();
    }

    class TPInventoryViewHolder extends RecyclerView.ViewHolder {
        TPInventoryViewHolder(View view) {
            super(view);
        }

        void bindData(TPInventoryItem inventoryItem) {
            final TextView description = itemView.findViewById(R.id.tp_description);
            final TextView packs = itemView.findViewById(R.id.packs);
            final TextView rollCount = itemView.findViewById(R.id.roll_count);
            final TextView plyCount = itemView.findViewById(R.id.ply_count);
            final RatingBar qualityRating = itemView.findViewById(R.id.quality_rating);

            Context context = itemView.getContext();
            String brandName = context.getString(inventoryItem.getTPInfo().getBrand().getBrandName());

            description.setText(String.format("%s %s", brandName, inventoryItem.getTPInfo().getName()));
            packs.setText(context.getString(R.string.available_packs_format, inventoryItem.getNumInStock()));
            rollCount.setText(context.getString(R.string.roll_count_format, inventoryItem.getRollCount()));
            plyCount.setText(context.getString(R.string.ply_count_format, inventoryItem.getTPInfo().getPlyCount()));
            qualityRating.setRating(inventoryItem.getTPInfo().getQualityRating());

        }
    }
}
