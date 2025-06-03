package com.example.mvvmconcept;

import android.app.Application;
import android.util.Log;

import com.example.mvvmconcept.core.network.RetrofitInstance;

public class TaskifyApp extends Application {

    private static TaskifyApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        // Initialisation globale : par exemple, Retrofit singleton
//        RetrofitInstance.init(getBaseUrl());

        // Initialisation d'autres composants globaux si besoin
        Log.d("TaskifyApp", "Application started");
    }

    public static TaskifyApp getInstance() {
        return instance;
    }

    /**
     * URL de base pour les appels réseau,
     * récupérée ici depuis une constante globale ou fichier de config
     */
    private String getBaseUrl() {
        return "https://api.example.com/"; // remplacer par ta constante ou config
    }
}
