package com.example.hw13.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.hw13.models.News;

@Database(entities = {News.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract NewsDao newsDao();
}
