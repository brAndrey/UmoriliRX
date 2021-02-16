package com.example.umorili2.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.umorili2.model.RecordingModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface RecordingModelDao {

    @Query("SELECT *FROM recordingModel")
    List<RecordingModel> getAll();

    @Query("SELECT *FROM recordingModel")
    LiveData<List<RecordingModel>> getAllLD();

    @Query("SELECT * FROM recordingmodel")
    Flowable<List<RecordingModel>> getModels();

    @Query("SELECT * FROM RecordingModel WHERE elementPureHtml = :elementPureHtml ")
    Maybe<RecordingModel> getCouponByElementPureHtml(String elementPureHtml);

    @Query("SELECT *FROM recordingmodel WHERE elementPureHtml = :elementPureHtml")
    Observable<RecordingModel> getByElementPureHtml (String elementPureHtml);

    @Query("SELECT * FROM recordingmodel WHERE elementPureHtml = :elementPureHtml")
    Single<RecordingModel> getOneCoupon(String elementPureHtml);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RecordingModel recordingModel);

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<RecordingModel> recordingModelList);

    @Update
    void update(RecordingModel recordingModel);

    @Update
    void updateList(List<RecordingModel> recordingModelList);

    @Delete
    void delete(RecordingModel recordingModel);

    @Query("DELETE FROM RecordingModel")
    void deleteRecordingModel();
}
