package com.example.umorili2.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.umorili2.model.RecordingModel;

@Database(entities = {RecordingModel.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract RecordingModelDao recordingModelDao();
}
