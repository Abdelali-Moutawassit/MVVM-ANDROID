package com.example.mvvmconcept.task.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmconcept.task.TaskRepository;

public class TaskListViewModel extends ViewModel {

    private final TaskRepository taskRepository;
    private final MutableLiveData<TaskListUiState> taskListUiState = new MutableLiveData<>(new TaskListUiState());

    public TaskListViewModel() {
        taskRepository = new TaskRepository();
        loadTasks();
    }

    public LiveData<TaskListUiState> getTaskListUiState() {
        return taskListUiState;
    }

    public void loadTasks() {
        TaskListUiState loadingState = new TaskListUiState();
        loadingState.setLoading(true);
        taskListUiState.setValue(loadingState);

        taskRepository.getTasks(new TaskRepository.TasksCallback() {
            @Override
            public void onSuccess(java.util.List<com.example.mvvmconcept.model.Task> tasks) {
                TaskListUiState successState = new TaskListUiState();
                successState.setLoading(false);
                successState.setTasks(tasks);
                taskListUiState.postValue(successState);
            }

            @Override
            public void onError(String message) {
                TaskListUiState errorState = new TaskListUiState();
                errorState.setLoading(false);
                errorState.setErrorMessage(message);
                taskListUiState.postValue(errorState);
            }
        });
    }
}
