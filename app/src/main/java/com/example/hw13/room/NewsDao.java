package com.example.hw13.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.hw13.models.News;

import java.util.List;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news")
    List<News> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(News news);

    @Query("SELECT * FROM news ORDER BY createdAt DESC")
    List<News> sortAll();

    @Delete
    void deleteTask(News news);

    @Query("SELECT * FROM news WHERE title LIKE '%' || :search || '%'")
    List<News> getSearch(String search);

    @Query("SELECT * FROM news ORDER BY title ASC")
    List<News> sort();
}
