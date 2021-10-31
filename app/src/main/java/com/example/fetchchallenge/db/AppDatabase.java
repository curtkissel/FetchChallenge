package com.example.fetchchallenge.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ItemEntity.class}, version = 1)
    public abstract class AppDatabase extends RoomDatabase {
        public abstract ItemDAO itemDao();
}
