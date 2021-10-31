package com.example.fetchchallenge;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.fetchchallenge.adapter.RecyclerAdapter;
import com.example.fetchchallenge.model.Item;
import com.example.fetchchallenge.repository.JSONConverter;
import com.example.fetchchallenge.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recycler_view);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.init();

        //When a change to the items list occurs, the obserer will call the onChanged method.
        mainViewModel.getItems().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(@Nullable List<Item> items) {
                recyclerAdapter.notifyDataSetChanged();
                initRecyclerView();
            }
        });

        initRecyclerView();

    }
    //Initializes a RecyclerView
    private void initRecyclerView(){
        recyclerAdapter = new RecyclerAdapter(this, (ArrayList<Item>) mainViewModel.getItems().getValue());
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);
    }
}