package com.example.fetchchallenge.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.fetchchallenge.db.AppDatabase;
import com.example.fetchchallenge.model.Item;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemRepository {

    private static ItemRepository instance;
    private ArrayList<Item> list = new ArrayList<>();
    JSONConverter jsonConverter;
    //AppDatabase db;


    public ItemRepository(){
        MutableLiveData<List<Item>> data = new MutableLiveData<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonConverter = retrofit.create(JSONConverter.class);
    }

    public static ItemRepository getInstance(){
        if(instance==null){
            return new ItemRepository();
        }
        return instance;
    }

    // retrieving data from the API
    public MutableLiveData<List<Item>> getItems(){
        Call<List<Item>> call = jsonConverter.getItems();
        MutableLiveData<List<Item>> data = new MutableLiveData<>();

        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if(!response.isSuccessful()){
                    Log.d("OnRespError" , ""+response.code());
                    return;
                }
                List<Item> items = response.body();
                data.setValue(items);

                for(Item item: items){
                    String content="";
                    content +=item.getId() +"\t";
                    content +=item.getListId() +"\t";
                    content +=item.getName();
                    Log.d("Item", content);
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.d("Error ",t.getMessage());
            }
        });
        return data;
    }
}
