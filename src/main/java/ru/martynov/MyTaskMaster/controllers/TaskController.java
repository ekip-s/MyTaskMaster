package ru.martynov.MyTaskMaster.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.martynov.MyTaskMaster.models.Task;
import ru.martynov.MyTaskMaster.services.TaskService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
@Tag(name="TaskController", description="Управление заданиями")
public class TaskController {

    private final TaskService taskService;

    @Operation(
            summary = "Создаем задание",
            description = "Создаем задание"
    )
    @PostMapping("/{personId}")
    public void createTask(@PathVariable @Parameter(description = "id пользователя") Long personId,
                           @RequestBody Task task) {
        taskService.createTask(personId, task);
    }

    @Operation(
            summary = "Лист заданий",
            description = "Получаем все задания пользователя"
    )
    @GetMapping("/{personId}")
    public List<Task> getTask(@PathVariable @Parameter(description = "id пользователя") Long personId) {
        return taskService.searchTasks(personId);
    }

    @Operation(
            summary = "Удаление задания",
            description = "Удаляет задание по id"
    )
    @DeleteMapping("/{personId}/delete/{taskId}")
    public void deleteTask(@PathVariable @Parameter(description = "id пользователя") Long personId,
                           @PathVariable @Parameter(description = "id задания") Long taskId
                           ) {
        taskService.deleteTask(personId, taskId);
    }

    @Operation(
            summary = "Повышает приоритет задания",
            description = "Увеличивает приоритет на +1"
    )
    @PatchMapping("/{personId}/up/{taskId}")
    public void upTask(@PathVariable @Parameter(description = "id пользователя") Long personId,
                           @PathVariable @Parameter(description = "id задания") Long taskId
    ) {
        taskService.upTask(personId, taskId);
    }
}
