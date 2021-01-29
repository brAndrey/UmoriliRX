package com.example.umorili2.ui.recycler_view_provision;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umorili2.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView postModel;
    TextView site;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        postModel = (TextView) itemView.findViewById(R.id.postitem_post);
        site = (TextView) itemView.findViewById(R.id.postitem_site);
    }
}
