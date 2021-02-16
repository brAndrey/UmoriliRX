package com.example.umorili2.repozitories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;

import com.example.umorili2.remote.App;
import com.example.umorili2.application.AppTakeObservable;
import com.example.umorili2.model.PostModel;
import com.example.umorili2.model.RecordingModel;
import com.example.umorili2.utils.Constants;
import com.example.umorili2.utils.Functionss;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.functions.Function;

public class RemoteRepozitoriesImpl implements RemoteRepozitories{


    private final MutableLiveData<String> selected = new MutableLiveData<String>();

    private CompositeDisposable disposables = new CompositeDisposable();

private AppTakeObservable appTakeObservable;

    // инициируем синглтон
    private static RemoteRepozitoriesImpl INSTANCE;

    private RemoteRepozitoriesImpl() {
    }

    public static RemoteRepozitoriesImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (RemoteRepozitoriesImpl.class) {
                INSTANCE = new RemoteRepozitoriesImpl();
            }
        }
        return INSTANCE;
    }
    //--------------------------

    // принытие данных
    public void setData(String st){
        // изменяем состояние MutableLiveData чем запускаем LiveData <> gatData
        selected.setValue(st);
        Log.e(this.getClass().getSimpleName()," "+ new Throwable().getStackTrace()[0].getMethodName() +"  "+ Thread.currentThread().getName()+" "+System.currentTimeMillis());
        Log.e(this.getClass().getSimpleName(), "  " + st);
    }

    // механизм возврата
    public LiveData<String> getData() {
        return selected;
    }

    //Observer<String> observer = new Observer<String>() {

    public Observable<List<PostModel>> getListPostModel() {

      return   App.getRequestApi()
                .getPostModel(Constants.RESOURSENAME,Constants.COINT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

  public   LiveData<List<PostModel>> makeReactiveQuery(){
        // при помощи fromPublisher превращаем Observeble в LiveData
        return LiveDataReactiveStreams.fromPublisher(
                App.getRequestApi()
                        .getPostModelLive(Constants.RESOURSENAME,Constants.COINT ) // from RequestApi.java
                        .subscribeOn(Schedulers.io())); // убираем в поток ввода вывода
    }


    public Observable<List<RecordingModel>> getListRecordingModelObs() {
        return App.getRequestApi()
                .getPostModel(Constants.RESOURSENAME, Constants.COINT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<List<PostModel>, List<RecordingModel>>() {
                         @Override
                         public List<RecordingModel> apply(@NotNull List<PostModel> postModels) throws Exception {
                             List<RecordingModel> recordingModel = Functionss.ConvertPostToRecordingList(postModels);
                             return recordingModel;
                         }
                     }
                );
    }

    public Flowable<List<RecordingModel>> getListRecordingModel() {
        return App.getRequestApi()
                .getPostModelLive(Constants.RESOURSENAME, Constants.COINT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<List<PostModel>, List<RecordingModel>>() {
                         @Override
                         public List<RecordingModel> apply(@NotNull List<PostModel> postModels) throws Exception {
                             List<RecordingModel> recordingModel = Functionss.ConvertPostToRecordingList(postModels);
                             return recordingModel;
                         }
                     }
                );
    }

}
