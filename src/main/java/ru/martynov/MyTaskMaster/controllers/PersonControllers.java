package ru.martynov.MyTaskMaster.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.martynov.MyTaskMaster.models.Person;
import ru.martynov.MyTaskMaster.services.PersonService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
@Tag(name="PersonControllers", description="Управление записями пользователей")
public class PersonControllers {

    private final PersonService personService;

    @Operation(
            summary = "Возвращает ник пользователя",
            description = "Возвращает ник-нейм пользователя, ник не влияет на вход или другие параметры. " +
                    "Используется тоько самим пользователем"
    )
    @GetMapping("/{personId}/username")
    public String getUsername(@PathVariable @Parameter(description = "id пользователя") Long personId) {
        return personService.getUsername(personId);
    }

    @Operation(
            summary = "Изменяет ник пользователя",
            description = "Изменяет ник. Может изменить и на пустой"
    )
    @PatchMapping("/{personId}/username")
    public void setUsername(@RequestParam(defaultValue = "")
                                @Parameter(description = "Новое имя пользователя") String newUsername,
                            @PathVariable @Parameter(description = "id пользователя") Long personId) {
        personService.setUsername(personId, newUsername);
    }

    @Operation(
            summary = "Лист пользователей",
            description = "Лист пользователей. Импользуется только в тесте."
    )
    @GetMapping()
    public List<Person> getPerson() {
        return personService.getPerson();
    }
}
