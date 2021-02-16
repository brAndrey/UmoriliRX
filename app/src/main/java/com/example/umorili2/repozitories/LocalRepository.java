package com.example.umorili2.repozitories;

import androidx.lifecycle.LiveData;

import com.example.umorili2.model.RecordingModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public interface LocalRepository {

    public LiveData<List<RecordingModel>> getLiveData();

    public Flowable<List<RecordingModel>> getCoupons();

    public Maybe<RecordingModel> getMaybeByElementPureHtml(String elementPureHtml);

    public Single<RecordingModel> getOneCoupon();

    public void insertRecordingModel(RecordingModel recordingModel);

    public void insertListRecordingModel(List<RecordingModel> recordingModelList);

    public void updateListCoupon(List<RecordingModel> recordingModelList);

    public void deleteAllRecordingModel();


}
