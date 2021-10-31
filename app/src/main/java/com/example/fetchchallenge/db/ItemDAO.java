package com.example.fetchchallenge.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import java.util.List;

@Dao
public interface ItemDAO {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertItems(List<ItemEntity> itemEntities);

        @Query("SELECT * FROM items")
        List<ItemEntity> getAll();

        @Query("SELECT * FROM items ORDER BY listId ASC, id ASC")
        List<ItemEntity> sortItems();

        @Query("DELETE FROM items WHERE name IS NULL OR name =''")
        abstract void deleteNull();

        @Query("DELETE FROM items")
        abstract void deleteAll();
}
