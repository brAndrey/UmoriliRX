package com.example.umorili2.repozitories;

import androidx.lifecycle.LiveData;

import com.example.umorili2.local.AppLoc;
import com.example.umorili2.local.AppDataBase;
import com.example.umorili2.model.RecordingModel;
import com.example.umorili2.utils.Functionss;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LocalRepositoryImpl implements LocalRepository{

    private LiveData<List<RecordingModel>> mAllRecordingModel;
    private Flowable<List<RecordingModel>> mFlRecordingModel;
    private Maybe<RecordingModel> maybeElementPureHtml;

    AppDataBase appDataBase;

    Executor executor;

    public LocalRepositoryImpl( AppDataBase appDataBase, Executor exec) {
        if (appDataBase == null){
            this.appDataBase = AppLoc.getInstance().getDatabase();
        }else {
            this.appDataBase = appDataBase;
        }

        if (exec==null){
            executor = Executors.newSingleThreadExecutor();
        }else {
        executor = exec;}

        mAllRecordingModel= appDataBase.recordingModelDao().getAllLD();
        mFlRecordingModel = appDataBase.recordingModelDao().getModels();

    }


    @Override
    public LiveData<List<RecordingModel>> getLiveData() {
        return mAllRecordingModel;
    }

    @Override
    public Flowable<List<RecordingModel>> getCoupons() {
        return mFlRecordingModel;
    }

    @Override
    public Maybe<RecordingModel> getMaybeByElementPureHtml(String elementPureHtml) {
        maybeElementPureHtml = appDataBase.recordingModelDao().getCouponByElementPureHtml(elementPureHtml);
        maybeElementPureHtml.subscribe(recordingModel -> Functionss.PrintRecordingM(recordingModel));
        return null;
    }

//    Room может возвращать наблюдаемые типы Flowable, Maybe и Single RxJava2.
//    Flowable выдает данные, когда есть данные в базе данных и каждый раз, когда данные обновляются в базе данных.
//    Наблюдаемые Maybe и Single не пересылают данные, когда вызывается изменение данных в базе данных после завершения.
//    Single выдает одну строку из базы данных и выдает ошибку, если в базе данных нет записи.
//    Может быть, выдает одну строку, если запись существует в базе данных.

            // https://www.zoftino.com/android-persistency-room-rxjava


    public Single<RecordingModel> getSingleByElementPureHtml(String elementPureHtml) {
        Single<RecordingModel> SingleElementPureHtml = appDataBase.recordingModelDao().getOneCoupon(elementPureHtml);
//        SingleElementPureHtml.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DisposableSingleObserver<RecordingModel>() {
//                               @Override
//                               public void onSuccess(@NotNull RecordingModel recordingModel) {
//                                   Functionss.PrintRecordingS(recordingModel);
//                               }
//
//                               @Override
//                               public void onError(@NotNull Throwable e) {
//                                   RecordingModel recordingModel = new RecordingModel();
//                                   recordingModel.setSite("В базе нет");
//                                   recordingModel.setElementPureHtml(elementPureHtml);
//                                   Functionss.PrintRecordingS(recordingModel);
//                               }
//                           }
//                );
        return SingleElementPureHtml;
    }

    @Override
    public Single<RecordingModel> getOneCoupon() {
        return null;
    }

    @Override
    public void insertRecordingModel(RecordingModel recordingModel) {

        try {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    appDataBase.recordingModelDao().insert(recordingModel);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void insertListRecordingModel(List<RecordingModel> recordingModelList) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                appDataBase.recordingModelDao().insertList(recordingModelList);
            }
        });
    }

    @Override
    public void updateListCoupon(List<RecordingModel> recordingModelList) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                appDataBase.recordingModelDao().updateList(recordingModelList);
            }
        });
    }

    @Override
    public void deleteAllRecordingModel() {
        appDataBase.recordingModelDao().deleteRecordingModel();
    }
}
