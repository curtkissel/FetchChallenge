package com.example.fetchchallenge.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fetchchallenge.model.Item;
import com.example.fetchchallenge.repository.ItemRepository;

import java.util.List;
//MainViewModel contains all information necessary to create our user interface in the Main Activity
public class MainViewModel extends ViewModel {

    private MutableLiveData<List<Item>> items;
    private ItemRepository repo;

    //Allows our ViewModel to access the items list from the ItemRepository
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

}
