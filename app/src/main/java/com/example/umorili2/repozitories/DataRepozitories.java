package com.example.umorili2.repozitories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;

import com.example.umorili2.api.App;
import com.example.umorili2.model.PostModel;
import com.example.umorili2.utils.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class DataRepozitories {


    private final MutableLiveData<String> selected = new MutableLiveData<String>();

    private CompositeDisposable disposables = new CompositeDisposable();

private AppTakeObservable appTakeObservable;

    // инициируем синглтон
    private static DataRepozitories INSTANCE;

    private DataRepozitories() {
    }

    public static DataRepozitories getInstance() {
        if (INSTANCE == null) {
            synchronized (DataRepozitories.class) {
                INSTANCE = new DataRepozitories();
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
       


//   appTakeObservable.getListPostsObservable()
//                 .subscribeOn(Schedulers.io())
//                 .observeOn(AndroidSchedulers.mainThread())
//              .flatMap(new Function<List<PostModel>, ObservableSource<List<PostModel>>>() {
//                  @Override
//                  public ObservableSource<List<PostModel>> apply(List<PostModel> postModelList) throws Exception {
//                      return Observable.fromIterable(postModelList)
//                              .subscribeOn(Schedulers.io());
//                  }
//              });
//
//              .flatMap(new Function<List<PostModel>, ObservableSource<PostModel>>() {
//                  @Override
//                  public ObservableSource<PostModel> apply(List<PostModel> postModels) throws Exception {
//                      //adapter.setPosts(postModels); // fill UI - наполняем UI
//                      return Observable.fromIterable(postModels)
//                              .subscribeOn(Schedulers.io());
//                  }
//              });

                 //.subscribe(postModelList -> Observable.just(postModelList))




    }

  public   LiveData<List<PostModel>> makeReactiveQuery(){
        // при помощи fromPublisher превращаем Observeble в LiveData
        return LiveDataReactiveStreams.fromPublisher(
                App.getRequestApi()
                        .getPostModelLive(Constants.RESOURSENAME,Constants.COINT ) // from RequestApi.java
                        .subscribeOn(Schedulers.io())); // убираем в поток ввода вывода
    }

}
