package com.omnitracs.tprunner.activities.main;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

import com.omnitracs.tprunner.entities.RetrofitInstance;
import com.omnitracs.tprunner.entities.Store;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainViewModel extends ViewModel {

    private MutableLiveData<List<Store>> mStoreLiveData = new MutableLiveData<>();

    interface IStoreDataService {
        @GET("stores.json")
        Call<List<Store>> getStoreData();
    }

    public void start() {
        refresh();
    }

    public void refresh() {
        IStoreDataService service = RetrofitInstance.getInstance().create(IStoreDataService.class); // TODO Use FOR CLASS.JAVA EXPLANATION
        service.getStoreData().enqueue(new Callback<List<Store>>() {
            @Override
            public void onResponse(@NotNull Call<List<Store>> call, @NonNull Response<List<Store>> response) {
                if (response.body() != null) {
                    mStoreLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Store>> call, @NonNull Throwable t) {
                Log.i("MainViewModel", "Woops", t); // TODO could use for string interpolation
            }
        });
    }

    public LiveData<List<Store>> getStoreLiveData() {
        return mStoreLiveData;
    }

}
