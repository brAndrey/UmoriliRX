package com.example.umorili2.ui.fragments;

import androidx.lifecycle.ViewModelProvider;

//import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.umorili2.R;
import com.example.umorili2.api.App;
import com.example.umorili2.model.PostModel;
import com.example.umorili2.ui.activity.MainAvtivityViewModel;
import com.example.umorili2.ui.recycler_view_provision.PostAdapter;
import com.example.umorili2.utils.Constants;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class RecyclerFragment extends Fragment {

    private RecyclerFragmentViewModel recyclerFragmentViewModel;

    private static final String TAG = "ObserverableActivity";

    //ui
    private RecyclerView recyclerView;

    // vars
    private CompositeDisposable disposables = new CompositeDisposable();
    private PostAdapter adapter;
    List<PostModel> posts;



    public static RecyclerFragment newInstance() {
        return new RecyclerFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.main_fragment, container, false);
        recyclerView = view.findViewById(R.id.posts_recycle_view);

        initRecyclerView();

        // подписываемся на ViewModel
        RecyclerFragmentViewModel model = new ViewModelProvider(getActivity()).get(RecyclerFragmentViewModel.class);
        //getActivity()).get(RecyclerFragmentViewModel.class);

        // забираем через Observable
         model.gatList().observe(this, postModelList -> adapter.setPosts(postModelList));

        // забираем через LiveData
         //model.makeQuery().observe(this, postModelList -> adapter.setPosts(postModelList));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //recyclerFragmentViewModel = new ViewModelProvider(this).get(RecyclerFragmentViewModel.class);


        // TODO: Use the ViewModel
    }


    private void initRecyclerView() {

        adapter = new PostAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);
    }

}