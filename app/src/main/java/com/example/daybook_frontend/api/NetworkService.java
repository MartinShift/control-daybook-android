package com.example.daybook_frontend.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService networkService;
    private Retrofit retrofitJson;
    private Api api;
    private NetworkService() {
        retrofitJson = new Retrofit.Builder()
                .baseUrl("http://192.168.1.23:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofitJson.create(Api.class);

    }

    public static synchronized NetworkService getInstance() {
        if (networkService == null) {
            networkService = new NetworkService();
        }
        return networkService;
    }

    public Api getApi() {
        return api;
    }
}