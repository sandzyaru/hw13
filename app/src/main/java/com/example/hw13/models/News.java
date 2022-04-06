package com.example.hw13.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class News implements Serializable {
    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    private long createdAt;
    public int getId() {
        return id;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public News(String title, long createdAt) {
        this.title = title;
        this.createdAt = createdAt;
    }

    public News() {
    }
}
