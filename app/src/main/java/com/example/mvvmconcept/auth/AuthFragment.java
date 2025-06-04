package com.example.mvvmconcept.auth;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvmconcept.R;

public class AuthFragment extends Fragment {

    private AuthViewModel authViewModel;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private ProgressBar progressBar;
    private TextView errorTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_auth, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        usernameEditText = view.findViewById(R.id.et_email);
        passwordEditText = view.findViewById(R.id.et_password);
        loginButton = view.findViewById(R.id.btn_login);
        progressBar = view.findViewById(R.id.progress_bar);
        errorTextView = view.findViewById(R.id.tv_error);

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        authViewModel.getAuthUiState().observe(getViewLifecycleOwner(), new Observer<AuthUiState>() {
            @Override
            public void onChanged(AuthUiState state) {
                progressBar.setVisibility(state.isLoading() ? View.VISIBLE : View.GONE);
                loginButton.setEnabled(!state.isLoading());

                if (state.isSuccess()) {
                    Toast.makeText(getContext(), "Login successful!", Toast.LENGTH_SHORT).show();
                    errorTextView.setText("");
                    // Navigation vers la suite...
                }

                if (state.getErrorMessage() != null) {
                    errorTextView.setText(state.getErrorMessage());
                }
            }
        });

        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (TextUtils.isEmpty(username)) {
                usernameEditText.setError("Username required");
                return;
            }
            if (TextUtils.isEmpty(password)) {
                passwordEditText.setError("Password required");
                return;
            }

            authViewModel.login(username, password);
        });
    }
}