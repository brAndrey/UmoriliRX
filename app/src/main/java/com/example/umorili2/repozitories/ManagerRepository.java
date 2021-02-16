package com.example.umorili2.repozitories;

import androidx.lifecycle.LiveData;

import com.example.umorili2.model.RecordingModel;

import java.util.List;

public interface ManagerRepository {

    public LiveData<List<RecordingModel>> getListRecordingModel();

    void onDestroy();

    void ClearBase();
}
