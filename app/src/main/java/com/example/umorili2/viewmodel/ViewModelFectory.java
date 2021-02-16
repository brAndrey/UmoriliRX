package com.example.umorili2.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.umorili2.repozitories.ManagerRepositoryImpl;
import com.example.umorili2.repozitories.RemoteRepozitoriesImpl;

import org.jetbrains.annotations.NotNull;

import io.reactivex.disposables.CompositeDisposable;

public class ViewModelFectory implements ViewModelProvider.Factory {

    RemoteRepozitoriesImpl remoteRepozitoriesImpl;
    ManagerRepositoryImpl managerRepositoryImpl;
    CompositeDisposable compositeDisposable;


    public ViewModelFectory(){

        remoteRepozitoriesImpl = RemoteRepozitoriesImpl.getInstance();
        managerRepositoryImpl = ManagerRepositoryImpl.getInstance();
    }

    @NonNull
    @NotNull
    @Override
    public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
        //return null;
        if (modelClass.isAssignableFrom(RecyclerFragmentViewModel.class)){
            return (T) new RecyclerFragmentViewModel(managerRepositoryImpl,compositeDisposable);
        }
        if (modelClass.isAssignableFrom(MainAvtivityViewModel.class)){
            return (T) new MainAvtivityViewModel(managerRepositoryImpl);
        }
        throw new IllegalArgumentException("Wrong ViewModel class");
    }
}
