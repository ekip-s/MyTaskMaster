package ru.martynov.MyTaskMaster.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.martynov.MyTaskMaster.models.Task;
import ru.martynov.MyTaskMaster.models.TaskCategory;
import ru.martynov.MyTaskMaster.repository.TaskRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    @Autowired
    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createTask(Task task) {

    }

    @Override
    public void deleteTask(long id) {

    }

    @Override
    public void upTask(long id) {

    }

    @Override
    public void performTask(long id) {

    }

    @Override
    public List<Task> searchTasks(long personId) {
        return null;
    }
}
