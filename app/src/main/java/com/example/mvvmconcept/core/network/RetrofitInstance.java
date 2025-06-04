package com.example.mvvmconcept.core.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;
    private static ApiService apiService;

    /**
     * Initialise Retrofit avec la baseUrl passée.
     * À appeler dans Application.onCreate() ou avant premier usage.
     * @param baseUrl URL de base de l'API REST
     */
    public static void init(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiService = retrofit.create(ApiService.class);
        }
    }

    /**
     * Fournit l'instance ApiService singleton.
     * Appeler RetrofitInstance.init() avant pour initialiser Retrofit.
     * @return ApiService singleton
     */
    public static ApiService getApiService() {
        if (apiService == null) {
            throw new IllegalStateException("RetrofitInstance is not initialized. Call init() first.");
        }
        return apiService;
    }

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            throw new IllegalStateException("RetrofitInstance is not initialized. Call init() first.");
        }
        return retrofit;
    }

}
