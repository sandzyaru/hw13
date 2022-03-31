package com.example.hw13;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import androidx.activity.result.ActivityResultCallback;

public class Prefs {
    private SharedPreferences preferences;

    public Prefs(Context context) {
        preferences = context.getSharedPreferences("settings",Context.MODE_PRIVATE);

    }




    public  void  saveBoardState(){
        preferences.edit().putBoolean("isBoardShown",true).apply();
    }
    public  boolean isBoardShown () {
        return preferences.getBoolean("isBoardShown", false);
    }
    public void saveImage(Uri uri) {
        preferences.edit().putString("saveImage", uri.toString()).apply();

    }
    public String getImage() {
        return preferences.getString("saveImage", "");
    }
    public void saveName (String name){
        preferences.edit().putString("saveText",name).apply();
    }
    public String getName() {
        return preferences.getString("saveText", "");
    }
    public void spClear (){
        preferences.edit().clear().apply();
    }

}
