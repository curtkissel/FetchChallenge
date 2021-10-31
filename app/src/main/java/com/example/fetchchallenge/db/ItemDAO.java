package com.example.fetchchallenge.db;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDAO {

        @Query("SELECT * FROM items")
        List<ItemEntity> getAll();

        @Query("SELECT id,listId,name  FROM items ORDER BY id ASC,name ASC")
        List<ItemEntity> sortItems();

        @Query("DELETE FROM items WHERE name IS NULL OR name =''")
        abstract void deleteNull();
}
