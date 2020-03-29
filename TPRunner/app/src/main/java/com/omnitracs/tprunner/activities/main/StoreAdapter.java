package com.omnitracs.tprunner.activities.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.omnitracs.tprunner.R;
import com.omnitracs.tprunner.entities.Store;

import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {

    private final IStoreClickedListener mClickListener;
    private final List<Store> mStores;

    public interface IStoreClickedListener {
        void onStoreClicked(Store store);
    }

    StoreAdapter(List<Store> stores, IStoreClickedListener clickedListener) {
        mClickListener = clickedListener;
        mStores = stores;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_store_list_item, parent, false);
        return new StoreViewHolder(view, mClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        holder.bindData(mStores.get(position));
    }

    @Override
    public int getItemCount() {
        return mStores.size();
    }

    class StoreViewHolder extends RecyclerView.ViewHolder {
        private Store mStore;
        private IStoreClickedListener mClickListener;

        StoreViewHolder(View view, IStoreClickedListener listener) {
            super(view);
            mClickListener = listener;
        }

        void bindData(Store store) {
            mStore = store;

            itemView.setOnClickListener(view -> {
                mClickListener.onStoreClicked(mStore);
            });

            final TextView address = itemView.findViewById(R.id.address);
            final TextView name = itemView.findViewById(R.id.name);
            final TextView totalPacks = itemView.findViewById(R.id.totalPacks);
            final TextView totalBrands = itemView.findViewById(R.id.totalBrands);

            address.setText(store.getAddress());
            name.setText(store.getName());
            totalPacks.setText(
                    itemView.getContext().getString(R.string.total_packs_format, store.getTotalPacks()));
            totalBrands.setText(
                    itemView.getContext().getString(R.string.total_brands_format, store.getTotalBrands()));
        }
    }
}
