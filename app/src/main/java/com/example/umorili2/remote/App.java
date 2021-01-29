package com.example.umorili2.remote;

import android.app.Application;
import android.util.Log;


import com.example.umorili2.utils.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

   // private static UmoriliApi umoriliApi;

    private static Retrofit retrofit;

    // логизация
    private static OkHttpClient client(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

    };

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    //Базовая часть адреса
                    .baseUrl(Constants.API_BASE_URI)
                    // для Observerable
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    //Конвертер, необходимый для преобразования JSON'а в объекты
                    .addConverterFactory(GsonConverterFactory.create())
                    // логизация запроса
                    .client(client());

    private static Retrofit retrofit2 = retrofitBuilder.build();

    private static UmoriliApi umoriliApi = retrofit2.create(UmoriliApi.class);

    public static UmoriliApi getRequestApi() {

        if (umoriliApi == null) {
            Log.e("App", "umoriliApi==null");
        }
        return umoriliApi;
    }


}