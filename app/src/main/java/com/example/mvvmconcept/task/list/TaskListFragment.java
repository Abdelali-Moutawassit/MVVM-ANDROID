package com.example.mvvmconcept.task.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmconcept.R;
import com.example.mvvmconcept.model.Task;

import java.util.List;

public class TaskListFragment extends Fragment {

    private TaskListViewModel viewModel;
    private TaskListAdapter adapter;
    private ProgressBar progressBar;
    private TextView errorTextView;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progressBar);
        errorTextView = view.findViewById(R.id.tv_empty_list);
        recyclerView = view.findViewById(R.id.recyclerViewTasks);

        adapter = new TaskListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(TaskListViewModel.class);

        viewModel.getTaskListUiState().observe(getViewLifecycleOwner(), new Observer<TaskListUiState>() {
            @Override
            public void onChanged(TaskListUiState state) {
                progressBar.setVisibility(state.isLoading() ? View.VISIBLE : View.GONE);

                if (state.getTasks() != null) {
                    adapter.setTasks(state.getTasks());
                    errorTextView.setVisibility(View.GONE);
                }

                if (state.getErrorMessage() != null) {
                    errorTextView.setText(state.getErrorMessage());
                    errorTextView.setVisibility(View.VISIBLE);
                } else {
                    errorTextView.setVisibility(View.GONE);
                }
            }
        });

        adapter.setOnItemClickListener(task -> {
            // TODO: gérer le clic sur un item (navigation vers detail par ex.)
        });
    }
}
