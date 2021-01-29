package com.example.umorili2.utils;

import android.os.Build;
import android.text.Html;
import android.util.Log;

import com.example.umorili2.model.PostModel;
import com.example.umorili2.model.RecordingModel;

import java.util.ArrayList;
import java.util.List;

public class Functionss {
    public static RecordingModel ConvertPostToRecording(PostModel postModel){
        RecordingModel recordingModel=new RecordingModel();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            recordingModel.setElementPureHtml(postModel.getElementPureHtml());

            recordingModel.setSite(postModel.getDesc() );
        }
//            else {
//                holder.postModel.setText(Html.fromHtml(postModel.getElementPureHtml()));
//            }
        return recordingModel;

    }

    public static List<RecordingModel> ConvertPostToRecordingList(List<PostModel> postModels){
        List<RecordingModel> listRecordins = new ArrayList<>();

        for (PostModel postModel: postModels ){

         RecordingModel recordingModel=ConvertPostToRecording(postModel);

         listRecordins.add(recordingModel);
        }

        return listRecordins;
    }

    public static void PrintRecordingList( List<RecordingModel> recordingModelList){
        for (RecordingModel recordingModel:recordingModelList){
            Log.e("PrintRecordingList",recordingModel.toString());
        }
    }
}
