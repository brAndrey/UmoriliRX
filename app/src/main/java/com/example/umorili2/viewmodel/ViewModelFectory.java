package com.example.umorili2.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.umorili2.repozitories.RemoteRepozitoriesImpl;

import org.jetbrains.annotations.NotNull;

public class ViewModelFectory implements ViewModelProvider.Factory {

    RemoteRepozitoriesImpl remoteRepozitoriesImpl;

    public ViewModelFectory(){
        remoteRepozitoriesImpl = RemoteRepozitoriesImpl.getInstance();
    }

    @NonNull
    @NotNull
    @Override
    public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
        //return null;
        if (modelClass.isAssignableFrom(RecyclerFragmentViewModel.class)){
            return (T) new RecyclerFragmentViewModel(remoteRepozitoriesImpl);
        }
        throw new IllegalArgumentException("Wrong ViewModel class");
    }
}
