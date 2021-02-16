package com.example.umorili2.repozitories;

import androidx.lifecycle.LiveData;

import com.example.umorili2.model.PostModel;
import com.example.umorili2.model.RecordingModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface RemoteRepozitories {

    public void setData(String st);

    public LiveData<String> getData();

    public Observable<List<PostModel>> getListPostModel();

    public LiveData<List<PostModel>> makeReactiveQuery();

    public Flowable<List<RecordingModel>> getListRecordingModel();

    public Observable<List<RecordingModel>> getListRecordingModelObs();
}
