package com.example.fetchchallenge.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.fetchchallenge.FetchApp;
import com.example.fetchchallenge.db.AppDatabase;
import com.example.fetchchallenge.db.ItemEntity;
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
    AppDatabase db;


    //constructor that init
    public ItemRepository(){
        MutableLiveData<List<Item>> data = new MutableLiveData<>();
        //retrofit allows us to convert api response to Java objects
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //creating the room db
        db = Room.databaseBuilder(FetchApp.getAppContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();

        jsonConverter = retrofit.create(JSONConverter.class);
    }

    public static ItemRepository getInstance(){
        if(instance==null){
            return new ItemRepository();
        }
        return instance;
    }

    // retrieving data from the API, returns the data as MutableLiveData
    public MutableLiveData<List<Item>> getItems(){
        Call<List<Item>> call = jsonConverter.getItems();
        MutableLiveData<List<Item>> data = new MutableLiveData<>();

        //enqueue asynchronously retrieves information from our API
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if(!response.isSuccessful()){
                    //Error message
                    Log.d("OnRespError" , ""+response.code());
                    return;
                }
                List<Item> items = response.body();

                List<ItemEntity> itemEntities = new ArrayList<>();

                //converting each Item to an ItemEntity so that it can be stored in the room db
                for(Item item: items){
                    itemEntities.add(new ItemEntity(item.getId(),item.getListId(),item.getName()));
                }

                //delete existing db
                db.itemDao().deleteAll();
                //insert the items
                db.itemDao().insertItems(itemEntities);
                //removing null/empty string entries
                db.itemDao().deleteNull();
                //sorting the entries by list ID and name
                itemEntities = db.itemDao().sortItems();

                items.clear();

                //converting the entries from the database to a mutable list of data
                for(ItemEntity itemEntity: itemEntities){
                    items.add(new Item(itemEntity.id, itemEntity.listId, itemEntity.name));
                }

                data.setValue(items);

            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.d("Error ",t.getMessage());
            }
        });
        return data;
    }
}
