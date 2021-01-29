package com.example.umorili2.not_use;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umorili2.R;
import com.example.umorili2.remote.App;
import com.example.umorili2.model.PostModel;
import com.example.umorili2.ui.recycler_view_provision.PostAdapter;
import com.example.umorili2.utils.Constants;
import com.example.umorili2.viewmodel.RecyclerFragmentViewModel;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentClassic  extends Fragment {

    private RecyclerFragmentViewModel recyclerFragmentViewModel;

    private static final String TAG = "ObserverableActivity";

    //ui
    private RecyclerView recyclerView;

    // vars
    private CompositeDisposable disposables = new CompositeDisposable();
    private PostAdapter adapter;
    List<PostModel> posts;

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

        if ( App.getRequestApi() == null) {Log.i(TAG," Облом ");

        } else{ Log.i(TAG," not null ");
            App.getRequestApi().getData(Constants.RESOURSENAME, Constants.COINT).enqueue(new Callback<List<PostModel>>() {
                @Override
                public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {

                    adapter.setPosts(response.body());
                }

                @Override
                public void onFailure(Call<List<PostModel>> call, Throwable t) {
                    //Toast.makeText(RetrofitClassActivity.this, "An error occurred during networking",Toast.LENGTH_SHORT).show();
                }
            });
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initRecyclerView() {

        adapter = new PostAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);
    }

}
