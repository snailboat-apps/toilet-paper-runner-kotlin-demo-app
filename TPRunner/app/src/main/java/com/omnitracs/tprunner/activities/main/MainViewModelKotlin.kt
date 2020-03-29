package com.omnitracs.tprunner.activities.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omnitracs.tprunner.entities.RetrofitInstance
import com.omnitracs.tprunner.entities.Store
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

class MainViewModelKotlin : ViewModel() {

    val storeLiveData =  MutableLiveData<List<Store>>()

    interface IStoreDataService {
        @GET("stores.json")
        fun getStoreData() : Call<List<Store>>
    }

    fun start() {
        val service = RetrofitInstance.getInstance().create(IStoreDataService::class.java);
        service.getStoreData().enqueue(object : Callback<List<Store>> {
            override fun onFailure(call: Call<List<Store>>, t: Throwable) {
                val bean = "bean"
                Log.i("MainViewModel", "Woops ${t.message} $bean");
            }

            override fun onResponse(call: Call<List<Store>>, response: Response<List<Store>>) {
                if (response.body() != null) {
                    storeLiveData.value = response.body()
                }
            }
        })
    }
}