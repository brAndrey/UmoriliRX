package com.example.umorili2.ui.fragments;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.umorili2.api.App;
import com.example.umorili2.model.PostModel;
import com.example.umorili2.repozitories.DataRepozitories;
import com.example.umorili2.utils.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class RecyclerFragmentViewModel extends ViewModel {

    private final MutableLiveData<String> selected = new MutableLiveData<String>();
    private final MutableLiveData<List<PostModel>> selectedList = new MutableLiveData<List<PostModel>>();

    DataRepozitories dataRepozitories;

    public RecyclerFragmentViewModel(){
        dataRepozitories = DataRepozitories.getInstance();
    }

    public LiveData<String> gatData() {
        // получаем данные из репозитория , без учёта жизненного цикла observeForever
        dataRepozitories.getData().observeForever(intText -> {
            Log.e(this.getClass().getSimpleName(), " " + new Throwable().getStackTrace()[0].getMethodName() + "  " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            Log.e(this.getClass().getSimpleName()," "+intText);
            selected.setValue(intText);

            // запуск собственного LIveData
            selected.setValue(intText);
        });
        return selected;
    }

    public LiveData<List<PostModel>> gatList(){
        dataRepozitories.getListPostModel()
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

    public LiveData<List<PostModel>> makeQuery(){

        return dataRepozitories.makeReactiveQuery();
    }

}
