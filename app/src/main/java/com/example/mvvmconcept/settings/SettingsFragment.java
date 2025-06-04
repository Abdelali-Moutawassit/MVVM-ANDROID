package com.example.mvvmconcept.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvmconcept.R;

public class SettingsFragment extends Fragment {

    private SettingsViewModel viewModel;
    private Switch notificationsSwitch;
    private Switch darkModeSwitch;
    private TextView titleText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        notificationsSwitch = view.findViewById(R.id.switch_notifications);
        darkModeSwitch = view.findViewById(R.id.switch_dark_mode);
        titleText = view.findViewById(R.id.tv_settings_title);

        viewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        viewModel.getNotificationsEnabled().observe(getViewLifecycleOwner(), enabled ->
                notificationsSwitch.setChecked(Boolean.TRUE.equals(enabled))
        );

        viewModel.getDarkModeEnabled().observe(getViewLifecycleOwner(), enabled ->
                darkModeSwitch.setChecked(Boolean.TRUE.equals(enabled))
        );

        notificationsSwitch.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) ->
                viewModel.toggleNotifications()
        );

        darkModeSwitch.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) ->
                viewModel.toggleDarkMode()
        );
    }
}
