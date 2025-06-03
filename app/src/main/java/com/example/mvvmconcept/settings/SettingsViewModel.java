package com.example.mvvmconcept.settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SettingsViewModel extends ViewModel {

    private final MutableLiveData<Boolean> notificationsEnabled = new MutableLiveData<>(true);
    private final MutableLiveData<Boolean> darkModeEnabled = new MutableLiveData<>(false);

    public LiveData<Boolean> getNotificationsEnabled() {
        return notificationsEnabled;
    }

    public LiveData<Boolean> getDarkModeEnabled() {
        return darkModeEnabled;
    }

    public void toggleNotifications() {
        Boolean current = notificationsEnabled.getValue();
        notificationsEnabled.setValue(current != null && !current);
    }

    public void toggleDarkMode() {
        Boolean current = darkModeEnabled.getValue();
        darkModeEnabled.setValue(current != null && !current);
    }
}
