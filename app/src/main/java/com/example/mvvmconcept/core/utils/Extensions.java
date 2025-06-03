package com.example.mvvmconcept.core.utils;

import android.widget.Toast;
import android.content.Context;

/**
 * Classe utilitaire pour méthodes d'extension simulées.
 */
public class Extensions {

    /**
     * Affiche un toast court.
     */
    public static void showToast(Context context, String message) {
        if (context != null && message != null && !message.isEmpty()) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

}
