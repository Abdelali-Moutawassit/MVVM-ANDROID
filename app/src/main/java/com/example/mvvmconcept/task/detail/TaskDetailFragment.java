package com.example.mvvmconcept.task.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvmconcept.R;
import com.example.mvvmconcept.model.Task;

public class TaskDetailFragment extends Fragment {

    private TaskDetailViewModel viewModel;
    private TextView titleTextView;
    private TextView completedTextView;
    private ProgressBar progressBar;
    private TextView errorTextView;

    private static final String ARG_TASK_ID = "task_id";

    public static TaskDetailFragment newInstance(int taskId) {
        TaskDetailFragment fragment = new TaskDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TASK_ID, taskId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titleTextView = view.findViewById(R.id.textViewTitle);
        completedTextView = view.findViewById(R.id.textViewCompleted);
        progressBar = view.findViewById(R.id.progressBar);
        errorTextView = view.findViewById(R.id.textViewError);

        viewModel = new ViewModelProvider(this).get(TaskDetailViewModel.class);

        int taskId = getArguments() != null ? getArguments().getInt(ARG_TASK_ID, -1) : -1;
        if (taskId != -1) {
            viewModel.fetchTaskById(taskId);
        }

        viewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> {
            progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        });

        viewModel.getTask().observe(getViewLifecycleOwner(), this::displayTaskDetails);

        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), error -> {
            errorTextView.setText(error);
            errorTextView.setVisibility(error != null ? View.VISIBLE : View.GONE);
        });
    }

    private void displayTaskDetails(Task task) {
        if (task != null) {
            titleTextView.setText(task.getTitle());
            completedTextView.setText(task.isCompleted() ? "Complétée" : "Non complétée");
        }
    }
}
