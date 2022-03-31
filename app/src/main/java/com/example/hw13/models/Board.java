package com.example.hw13.models;

import com.airbnb.lottie.LottieAnimationView;

public class Board {
    private String title;
    private String desc;
    private int image;
    private String lottie;

    public Board(String title) {
        this.title=title;
    }
    public Board(String title , String desc , int image) {
        this.title=title;
        this.desc=desc;
        this.image=image;
    }
    public Board(String title , String desc ,String lottie){
        this.title=title;
        this.desc=desc;
        this.lottie=lottie;
    }


    public Board (String title , String desc ) {
        this.title=title;
        this.desc=desc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getLottie() {
        return lottie;
    }

    public void setLottie(String lottie) {
        this.lottie = lottie;
    }

    public String getTitle() {
        return title;
    }
}
