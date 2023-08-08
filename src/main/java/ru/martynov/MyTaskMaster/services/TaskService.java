package ru.martynov.MyTaskMaster.services;

import ru.martynov.MyTaskMaster.models.Task;
import ru.martynov.MyTaskMaster.models.TaskCategory;

import java.util.List;

public interface TaskService {
    void createTask(Task task);
    void deleteTask(long id);
    void upTask(long id);
    void performTask(long id);
    List<Task> searchTasks(long personId);
}
