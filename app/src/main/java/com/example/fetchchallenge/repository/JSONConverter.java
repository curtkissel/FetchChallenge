package com.example.fetchchallenge.repository;

import com.example.fetchchallenge.model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONConverter {

    @GET("hiring.json")
    Call <List<Item>> getItems();
}
