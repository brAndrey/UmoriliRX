package com.example.umorili2.local;

import android.app.Application;

import androidx.room.Room;

public class AppLoc extends Application {
    public static AppLoc instanse;

    private AppDataBase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instanse=this;
        database= Room.databaseBuilder(this, AppDataBase.class,"database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

    }

    public static AppLoc getInstance(){
        return instanse;
    }

    public AppDataBase getDatabase(){
        return database;
    }
}
