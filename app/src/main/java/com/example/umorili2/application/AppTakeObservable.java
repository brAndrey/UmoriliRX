package com.example.umorili2.application;

import com.example.umorili2.remote.App;
import com.example.umorili2.model.PostModel;
import com.example.umorili2.utils.Constants;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AppTakeObservable  {

    public Observable<PostModel> getPostsObservable() {
        return App.getRequestApi()

                .getPostModel(Constants.RESOURSENAME,Constants.COINT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<List<PostModel>, ObservableSource<PostModel>>() {
                    @Override
                    public ObservableSource<PostModel> apply(List<PostModel> postModels) throws Exception {
                        //adapter.setPosts(postModels); // fill UI - наполняем UI
                        return Observable.fromIterable(postModels)
                                .subscribeOn(Schedulers.io());
                    }
                });

    }

    public Observable<List<PostModel>> getListPostsObservable() {
        return App.getRequestApi()
                .getPostModel(Constants.RESOURSENAME,Constants.COINT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
