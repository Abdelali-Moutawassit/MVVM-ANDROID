package com.example.mvvmconcept.core.base;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * ViewModel de base avec gestion simple des états de chargement et d'erreur.
 * Peut être étendu par les ViewModels spécifiques pour partager la logique commune.
 */
public class BaseViewModel extends ViewModel {

    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>(null);

    /**
     * LiveData observée pour savoir si une opération est en cours.
     */
    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    /**
     * LiveData observée pour afficher un message d'erreur.
     */
    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    /**
     * Méthode utilitaire pour indiquer que le chargement commence.
     */
    protected void setLoading(boolean loading) {
        isLoading.postValue(loading);
    }

    /**
     * Méthode utilitaire pour publier un message d'erreur.
     */
    protected void setError(String message) {
        errorMessage.postValue(message);
    }

    /**
     * Réinitialiser le message d'erreur.
     */
    protected void clearError() {
        errorMessage.postValue(null);
    }
}
