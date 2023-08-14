package ru.martynov.MyTaskMaster.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.martynov.MyTaskMaster.models.Task;
import ru.martynov.MyTaskMaster.services.TaskService;

import java.util.List;

@Slf4j
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
        log.info("Получен POST запрос к URL: '/task/{}'. Задание: {}", personId, task.toString());
        taskService.createTask(personId, task);
    }

    @Operation(
            summary = "Лист заданий",
            description = "Получаем все задания пользователя"
    )
    @GetMapping("/{personId}")
    public List<Task> getTask(@PathVariable @Parameter(description = "id пользователя") Long personId) {
        log.info("Получен GET запрос к URL: '/task/{}.'", personId);
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
        log.info("Получен DELETE запрос к URL: '/task/{}/delete/{}'.", personId, taskId);
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
        log.info("Получен PATCH запрос к URL: '/task/{}/up/{}'.", personId, taskId);
        taskService.upTask(personId, taskId);
    }

    @Operation(
            summary = "Понизить приоритет задания",
            description = "Понизить приоритет на -1, но не менее 0"
    )
    @PatchMapping("/{personId}/low/{taskId}")
    public void lowTask(@PathVariable @Parameter(description = "id пользователя") Long personId,
                       @PathVariable @Parameter(description = "id задания") Long taskId
    ) {
        log.info("Получен PATCH запрос к URL: '/task/{}/low/{}'.", personId, taskId);
        taskService.lowTask(personId, taskId);
    }

    @Operation(
            summary = "Выполнить задание",
            description = "Задание помечается выполненным и приоритет падает до -1"
    )
    @PatchMapping("/{personId}/done/{taskId}")
    public void doneTask(@PathVariable @Parameter(description = "id пользователя") Long personId,
                        @PathVariable @Parameter(description = "id задания") Long taskId
    ) {
        log.info("Получен PATCH запрос к URL: '/task/{}/done/{}'.", personId, taskId);
        taskService.doneTask(personId, taskId);
    }
}
