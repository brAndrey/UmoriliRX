package com.example.umorili2.repozitories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;

import com.example.umorili2.local.AppLoc;
import com.example.umorili2.local.AppDataBase;
import com.example.umorili2.model.RecordingModel;
import com.example.umorili2.utils.Functionss;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ManagerRepositoryImpl implements ManagerRepository{

    RemoteRepozitoriesImpl remoteRepozitories;
    LocalRepositoryImpl localRepository;

    private final MutableLiveData<List<RecordingModel>> selectedList = new MutableLiveData<List<RecordingModel>>();

    // инициируем синглтон
    private static ManagerRepositoryImpl INSTANCE;

    public static ManagerRepositoryImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (ManagerRepositoryImpl.class) {
                INSTANCE = new ManagerRepositoryImpl();
            }
        }
        return INSTANCE;
    }

    ManagerRepositoryImpl() {
        remoteRepozitories = RemoteRepozitoriesImpl.getInstance();

        // AppLoc -обязательно прописать в манифест
        AppDataBase appDataBase = AppLoc.getInstance().getDatabase();
        Executor executor = Executors.newSingleThreadExecutor();

        localRepository = new LocalRepositoryImpl(appDataBase,executor);
    }

    //**************


    // путь из обычного Observeble
//    @Override
//    public LiveData<List<RecordingModel>> getListRecordingModel() {
//        Log.e(this.getClass().getSimpleName()," "+ new Throwable().getStackTrace()[0].getMethodName() +"  "+ Thread.currentThread().getName()+" "+System.currentTimeMillis());
//        remoteRepozitories.getListRecordingModel()
//                .subscribe(recordingModelList -> selectedList.setValue(recordingModelList));
//        return selectedList ;
//    }



    public LiveData<List<RecordingModel>> getListRecordingModel() {

        //getLocalRepozitories();
        //setOne();
        getCouponsFromService();

        Log.e(this.getClass().getSimpleName()," "+ new Throwable().getStackTrace()[0].getMethodName() +"  "+ Thread.currentThread().getName()+" "+System.currentTimeMillis());
        // данный конвертер работает только тогда когда в него приходит Floable, с Observabler не работает
        //return LiveDataReactiveStreams.fromPublisher(remoteRepozitories.getListRecordingModel());
        return LiveDataReactiveStreams.fromPublisher(localRepository.getCoupons());
    }
    //***********************

    public void getCouponsFromService(){
        //add observable to CompositeDisposable so that it can be dispose when ViewModel is ready to be destroyed
        //Call retrofit client on background thread and update database with response from service using Room
        //добавьте observable в CompositeDisposable, чтобы его можно было утилизировать, когда ViewModel будет готов к уничтожению
        //Вызов клиента retrofit в фоновом потоке и обновление базы данных с ответом от службы с использованием Room

        io.reactivex.Observable.just(1)
                .subscribeOn(Schedulers.computation())
                .flatMap(i -> { return remoteRepozitories.getListRecordingModelObs();})
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<RecordingModel>>() {
                    @Override
                    public void accept(List<RecordingModel> recordingModelList) throws Exception {
                        //Functionss.PrintRecordingList(recordingModelList);
                        EnumerationRecordingModelList(recordingModelList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("MainActivity", "exception getting coupons", throwable);
                    }
                });

    }

    private void EnumerationRecordingModelList(List<RecordingModel> modelList) {
        for (RecordingModel rm : modelList) {

            localRepository.getSingleByElementPureHtml(rm.getElementPureHtml())
                    .subscribeOn(Schedulers.io())
                    .filter(new Predicate<RecordingModel>() {
                        @Override
                        public boolean test(@NotNull RecordingModel recordingModel) throws Exception {
                            if (recordingModel.getElementPureHtml().contains("цитат,")) {
                                return false;
                            }
                            return true;
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableMaybeObserver<RecordingModel>() {
                                   @Override
                                   public void onSuccess(@NotNull RecordingModel recordingModel) {

                                   }

                                   @Override
                                   public void onError(@NotNull Throwable e) {
                                       rm.setSite(Functionss.TimeFormat());
                                       //Log.e("contains ",rm.getElementPureHtml()+" "+rm.getElementPureHtml().contains("цитат,"));
                                       if(!rm.getElementPureHtml().contains("цитат,")) {
                                           localRepository.insertRecordingModel(rm);
                                       }else Log.e("contains ",rm.getElementPureHtml());
                                   }

                                   @Override
                                   public void onComplete() {

                                   }
                               }
                    );
            //database update
            //localRepository.getMaybeByElementPureHtml(rm.getElementPureHtml());


        }
    }

    public void getLocalRepozitories(){
        try {
            localRepository.getCoupons()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(recordingModelList -> Functionss.PrintRecordingList(recordingModelList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOne(){
        RecordingModel rm = new RecordingModel();
        rm.setSite("one");
        rm.setDesc("one");
        rm.setElementPureHtml("one");
        Log.e("setOne",""+ rm.toString());
        try {
            localRepository.insertRecordingModel(rm);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    @Override
    public void onDestroy() {

    }

    @Override
    public void ClearBase() {
        localRepository.deleteAllRecordingModel();
    }

}
