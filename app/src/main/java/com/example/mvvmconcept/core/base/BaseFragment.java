package com.example.mvvmconcept.core.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

/**
 * Fragment de base pour gérer l'observation des LiveData communes et faciliter l'implémentation.
 * Classe générique paramétrée par le ViewModel associé.
 *
 * Exemple d'usage :
 * public class AuthFragment extends BaseFragment<AuthViewModel> { ... }
 */
public abstract class BaseFragment<VM extends BaseViewModel> extends Fragment {

    protected VM viewModel;

    /**
     * Fournir l'instance ViewModel depuis les sous-classes.
     * (Exemple : ViewModelProviders.of(this).get(AuthViewModel.class))
     */
    protected abstract VM getViewModel();

    /**
     * Fournir le layout resource id à inflater.
     */
    @LayoutRes
    protected abstract int getLayoutRes();

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        return inflater.inflate(getLayoutRes(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = getViewModel();

        observeViewModel();
    }

    /**
     * Observer les LiveData communes du ViewModel (chargement, erreur, etc.).
     * Peut être surchargé dans les fragments enfants.
     */
    protected void observeViewModel() {
        viewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
            // Implémenter la gestion de chargement (ex: afficher un ProgressBar)
            if (isLoading != null) {
                if (isLoading) {
                    showLoading();
                } else {
                    hideLoading();
                }
            }
        });

        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), error -> {
            if (error != null && !error.isEmpty()) {
                showError(error);
            }
        });
    }

    /**
     * Méthode à implémenter pour afficher un loader.
     */
    protected void showLoading() {
        // Par défaut, rien, à override dans le fragment si besoin
    }

    /**
     * Méthode à implémenter pour cacher un loader.
     */
    protected void hideLoading() {
        // Par défaut, rien, à override dans le fragment si besoin
    }

    /**
     * Méthode à implémenter pour afficher une erreur (ex: Toast, Snackbar, Dialog).
     */
    protected void showError(String message) {
        // Par défaut, rien, à override dans le fragment si besoin
    }
}
