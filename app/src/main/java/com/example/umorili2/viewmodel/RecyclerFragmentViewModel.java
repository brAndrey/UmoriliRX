package com.example.umorili2.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.umorili2.model.PostModel;
import com.example.umorili2.model.RecordingModel;
import com.example.umorili2.repozitories.RemoteRepozitoriesImpl;
import com.example.umorili2.utils.Functionss;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RecyclerFragmentViewModel extends ViewModel {

    private final MutableLiveData<String> selected = new MutableLiveData<String>();
    private final MutableLiveData<List<PostModel>> selectedList = new MutableLiveData<List<PostModel>>();


    RemoteRepozitoriesImpl remoteRepozitoriesImpl;

    public RecyclerFragmentViewModel(RemoteRepozitoriesImpl remoteRepozitoriesImpl){
        this.remoteRepozitoriesImpl = remoteRepozitoriesImpl;
    }

    public LiveData<String> gatData() {
        // получаем данные из репозитория , без учёта жизненного цикла observeForever
        remoteRepozitoriesImpl.getData().observeForever(intText -> {
            Log.e(this.getClass().getSimpleName(), " " + new Throwable().getStackTrace()[0].getMethodName() + "  " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            Log.e(this.getClass().getSimpleName()," "+intText);
            selected.setValue(intText);

            // запуск собственного LIveData
            selected.setValue(intText);
        });
        return selected;
    }

    public LiveData<List<PostModel>> gatList(){
        remoteRepozitoriesImpl.getListPostModel()
        //getListPostsObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<PostModel>>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NotNull List<PostModel> postModels) {
                        selectedList.setValue(postModels);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return selectedList;
    }

    public void getRecordingModel(){
        Log.e(this.getClass().getSimpleName()," "+ new Throwable().getStackTrace()[0].getMethodName() +"  "+ Thread.currentThread().getName()+" "+System.currentTimeMillis());
        remoteRepozitoriesImpl.getListRecordingModel()
                .subscribe(new Observer<List<RecordingModel>>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NotNull List<RecordingModel> recordingModelList) {
                        Log.e(this.getClass().getSimpleName()," "+ new Throwable().getStackTrace()[0].getMethodName() +"  "+ Thread.currentThread().getName()+" "+System.currentTimeMillis());

                        Functionss.PrintRecordingList(recordingModelList);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    public LiveData<List<PostModel>> makeQuery(){
        return remoteRepozitoriesImpl.makeReactiveQuery();
    }

}
