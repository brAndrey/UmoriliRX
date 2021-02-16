package com.example.umorili2.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.umorili2.model.PostModel;
import com.example.umorili2.model.RecordingModel;
//import com.example.umorili2.repozitories.RemoteRepozitoriesImpl;
import com.example.umorili2.repozitories.ManagerRepositoryImpl;
import com.example.umorili2.repozitories.RemoteRepozitories;

import com.example.umorili2.utils.Functionss;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RecyclerFragmentViewModel extends ViewModel {

    private final MutableLiveData<String> selected = new MutableLiveData<String>();
    private final MutableLiveData<List<PostModel>> selectedList = new MutableLiveData<List<PostModel>>();
    private final MutableLiveData<List<RecordingModel>> selectedListR = new MutableLiveData<List<RecordingModel>>();


    ManagerRepositoryImpl managerRepository;
    CompositeDisposable compositeDisposable;

    public RecyclerFragmentViewModel(ManagerRepositoryImpl managerRepositoryImpl,CompositeDisposable compositeDisposable){

        this.managerRepository = managerRepositoryImpl;
        this.compositeDisposable=compositeDisposable;
    }

    // ретранслируем LiveData
    public LiveData<List<RecordingModel>> getRecordingModel() {
        Log.e(this.getClass().getSimpleName(), " " + new Throwable().getStackTrace()[0].getMethodName() + "  " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
        return managerRepository.getListRecordingModel();
    }

    @Override
    protected void onCleared() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
        super.onCleared();
    }

    //    public LiveData<String> gatData() {
//        // получаем данные из репозитория , без учёта жизненного цикла observeForever
//        remoteRepozitoriesImpl.getData().observeForever(intText -> {
//            Log.e(this.getClass().getSimpleName(), " " + new Throwable().getStackTrace()[0].getMethodName() + "  " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
//            Log.e(this.getClass().getSimpleName()," "+intText);
//            selected.setValue(intText);
//
//            // запуск собственного LIveData
//            selected.setValue(intText);
//        });
//        return selected;
//    }

//    public LiveData<List<PostModel>> gatList(){
//        remoteRepozitoriesImpl.getListPostModel()
//        //getListPostsObservable()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<List<PostModel>>() {
//                    @Override
//                    public void onSubscribe(@NotNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(@NotNull List<PostModel> postModels) {
//                        selectedList.setValue(postModels);
//                    }
//
//                    @Override
//                    public void onError(@NotNull Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//        return selectedList;
//    }
}
