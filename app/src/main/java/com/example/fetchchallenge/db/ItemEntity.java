package com.example.fetchchallenge.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "items")
public class ItemEntity {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "listId")
    public int listId;

    @ColumnInfo(name = "name")
    public String name;
}
