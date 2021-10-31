package com.example.fetchchallenge.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fetchchallenge.model.Item;
import com.example.fetchchallenge.repository.ItemRepository;

import java.util.List;

public class MainViewModel extends ViewModel {

    private MutableLiveData<List<Item>> items;
    private ItemRepository repo;

    public void init(){
        if(items != null){
            return;
        }
        repo = ItemRepository.getInstance();
        items = repo.getItems();
    }

    public LiveData<List<Item>> getItems(){
        return items;
    }

    /*public LiveData<List<Item>> filterNull(){

    }

    public LiveData<List<Item>> sortByListId(){

    }

    public LiveData<List<Item>> sortByName(){

    }*/


}
