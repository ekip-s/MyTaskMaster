package ru.martynov.MyTaskMaster.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.martynov.MyTaskMaster.exception.NotFoundException;
import ru.martynov.MyTaskMaster.models.Task;
import ru.martynov.MyTaskMaster.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final PersonService personService;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, PersonService personService) {
        this.taskRepository = taskRepository;
        this.personService=personService;
    }

    @Override
    @Transactional
    public void createTask(long personId, Task task) {
        task.setPerson(personService.getUserById(personId));
        task.setCreateDt(LocalDateTime.now());
        taskRepository.save(task);
    }

    @Override
    @Transactional
    public void deleteTask(long personId, long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    @Transactional
    public void upTask(long personId, long taskId) {
        Task task = getPersonById(taskId);
        if(!task.isDone() && task.getImportanceLevel() < 100) {
            task.setImportanceLevel(task.getImportanceLevel() + 1);
            taskRepository.save(task);
        }
    }

    @Override
    @Transactional
    public void lowTask(long personId, long taskId) {
        Task task = getPersonById(taskId);
        if(!task.isDone() && task.getImportanceLevel() > -100) {
            task.setImportanceLevel(task.getImportanceLevel() - 1);
            taskRepository.save(task);
        }
    }

    @Override
    @Transactional
    public void doneTask(long personId, long taskId) {
        Task task = getPersonById(taskId);
        if(!task.isDone()) {
            taskRepository.save(task.doneTask());
        }
    }

    @Override
    public List<Task> searchTasks(long personId) {
        return taskRepository.searchTasks();
        /*return taskRepository.findAll(Sort
                .by("importanceLevel").descending().and(Sort.by("createDt")));*/
    }

    private Task getPersonById(long id) {
        return taskRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Нет задания с id=" + id + "."));
    }
}
