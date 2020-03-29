package com.omnitracs.tprunner.activities.store;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.omnitracs.tprunner.R;
import com.omnitracs.tprunner.entities.Store;

public class StoreActivity extends AppCompatActivity {

    public static final String EXTRA_STORE = "EXTRA_STORE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_store_details);

        final RecyclerView tpInventoryList = findViewById(R.id.tp_inventory_list);
        final TextView storeName = findViewById(R.id.name);
        final TextView storeAddress = findViewById(R.id.address);

        DividerItemDecoration dividerDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        Drawable divider = ContextCompat.getDrawable(this, R.drawable.list_item_spacer);
        if (divider != null) {
            dividerDecoration.setDrawable(divider);
            tpInventoryList.addItemDecoration(dividerDecoration);
        }

        Store store = getIntent().getParcelableExtra(EXTRA_STORE);

        storeName.setText(store.getName());
        storeAddress.setText(store.getAddress());

        TPInventoryAdapter adapter = new TPInventoryAdapter(store.getTPInventory());
        tpInventoryList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        tpInventoryList.setAdapter(adapter);
    }
}
