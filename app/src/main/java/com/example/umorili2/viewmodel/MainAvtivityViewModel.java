package com.example.umorili2.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.umorili2.repozitories.ManagerRepositoryImpl;
import com.example.umorili2.repozitories.RemoteRepozitories;

public class MainAvtivityViewModel extends ViewModel {
    ManagerRepositoryImpl managerRepositoryImpl;

    MainAvtivityViewModel(ManagerRepositoryImpl managerRepositoryImpl){
        this.managerRepositoryImpl = managerRepositoryImpl;
    }

    public void ClearBase() {
        managerRepositoryImpl.ClearBase();
    }


    // TODO: Implement the ViewModel

}