package com.omnitracs.tprunner.activities.main;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.omnitracs.tprunner.R;
import com.omnitracs.tprunner.activities.store.StoreActivity;

public class MainActivity extends AppCompatActivity {

    private MainViewModelKotlin mViewModel; // Lateinit var explanation

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final RecyclerView storeListView = findViewById(R.id.store_list);
        DividerItemDecoration dividerDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        Drawable divider = ContextCompat.getDrawable(this, R.drawable.list_item_spacer);
        if (divider != null) {
            dividerDecoration.setDrawable(divider);
            storeListView.addItemDecoration(dividerDecoration);
        }

        mViewModel = ViewModelProviders.of(this).get(MainViewModelKotlin.class);
        mViewModel.getStoreLiveData().observe(this, storeList -> {
            StoreAdapter adapter = new StoreAdapter(storeList, storeClicked -> {
                Intent intent = new Intent(this, StoreActivity.class); // for store view page
                intent.putExtra(StoreActivity.EXTRA_STORE, storeClicked);
                startActivity(intent);
            });
            storeListView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            storeListView.setAdapter(adapter);
        });
        mViewModel.start();
    }
}
