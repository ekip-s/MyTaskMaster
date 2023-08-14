package ru.martynov.MyTaskMaster.services;

import ru.martynov.MyTaskMaster.models.Task;


import java.util.List;

public interface TaskService {
    void createTask(long personId, Task task);
    void deleteTask(long personId, long taskId);
    void upTask(long personId, long taskId);
    void lowTask(long personId, long taskId);
    void doneTask(long personId, long taskId);
    List<Task> searchTasks(long personId);
}
