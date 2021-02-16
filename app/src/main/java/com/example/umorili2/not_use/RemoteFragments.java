package com.example.umorili2.not_use;

import androidx.lifecycle.ViewModelProvider;

import com.example.umorili2.ui.fragments.RecyclerFragment;
import com.example.umorili2.ui.recycler_view_provision.PostAdapter;
import com.example.umorili2.viewmodel.RecyclerFragmentViewModel;
import com.example.umorili2.viewmodel.ViewModelFectory;

public class RemoteFragments extends RecyclerFragment {

    private RecyclerFragmentViewModel recyclerFragmentViewModel;

    private ViewModelFectory viewModelFectory;

    private PostAdapter adapter;

@Override
public void InitGetData(){}
//    viewModelFectory = new ViewModelFectory();
//
//    // подписываемся на ViewModel
//    RecyclerFragmentViewModel model = new ViewModelProvider(getActivity(),viewModelFectory).get(RecyclerFragmentViewModel.class);
//    //getActivity()).get(RecyclerFragmentViewModel.class);
//
//
//    // забираем через Observable
//        model.gatList().observe(this, postModelList -> adapter.setPosts(postModelList));
//        model.getRecordingModel();
//
//    // забираем через LiveData
//    //model.makeQuery().observe(this, postModelList -> adapter.setPosts(postModelList));

}
