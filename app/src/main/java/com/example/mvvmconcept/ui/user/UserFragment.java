package com.example.mvvmconcept.ui.user;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvmconcept.R;


public class UserFragment extends Fragment {

    private UserViewModel userViewModel;
    private TextView textView;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        textView = view.findViewById(R.id.textViewUsers);
        progressBar = view.findViewById(R.id.progressBar);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        observeViewModel();

        userViewModel.fetchUsers();

        return view;
    }

    private void observeViewModel() {
        userViewModel.getUsers().observe(getViewLifecycleOwner(), users -> {
            StringBuilder builder = new StringBuilder();
            for (var user : users) {
                builder.append(user.getName()).append(" (").append(user.getEmail()).append(")\n");
            }
            textView.setText(builder.toString());
        });

        userViewModel.getLoadingState().observe(getViewLifecycleOwner(), isLoading -> {
            progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        });

        userViewModel.getError().observe(getViewLifecycleOwner(), error -> {
            if (error != null) {
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
