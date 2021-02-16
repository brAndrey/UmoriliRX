package com.example.umorili2.ui.recycler_view_provision;


import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.umorili2.R;
import com.example.umorili2.model.PostModel;
import com.example.umorili2.model.RecordingModel;
import com.example.umorili2.utils.Functionss;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<ViewHolder> {

    //private List<PostModel> posts;

    private List<RecordingModel> posts;

//    public PostAdapter(List<PostModel> posts) {
//        this.posts = posts;
//    }

    @NonNull
    @Override
    //  возвращает объект ViewHolder, который будет хранить данные по одному объекту
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    // выполняет привязку объекта ViewHolder к объекту  по определенной позиции
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //PostModel postModel = posts.get(position);
        RecordingModel recordingModel=posts.get(position);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.postModel.setText(Html.fromHtml(recordingModel.getElementPureHtml(), Html.FROM_HTML_MODE_LEGACY));
            holder.site.setText(Html.fromHtml(recordingModel.getSite(),Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.postModel.setText(Html.fromHtml(recordingModel.getElementPureHtml()));
        }

        // было 29.01.2020
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            holder.postModel.setText(Html.fromHtml(postModel.getElementPureHtml(), Html.FROM_HTML_MODE_LEGACY));
//            holder.site.setText(Html.fromHtml(postModel.getDesc(),Html.FROM_HTML_MODE_LEGACY));
//        } else {
//            holder.postModel.setText(Html.fromHtml(postModel.getElementPureHtml()));
//        }

    }

    @Override
    // возвращает количество объектов в списке
    public int getItemCount() {
        if (posts == null) return 0;
        return posts.size();
    }

    public void setPosts(List<RecordingModel> recordings){
        posts=recordings;
        notifyDataSetChanged();
    }

    public void updatePost(RecordingModel recordingModel){

        Log.e("updatePost",recordingModel.toString());
        posts.set(posts.indexOf(recordingModel), recordingModel);
        notifyItemChanged(posts.indexOf(recordingModel));
    }

}
