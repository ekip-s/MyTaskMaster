package ru.martynov.MyTaskMaster.services;

import ru.martynov.MyTaskMaster.models.Person;

import java.util.List;

public interface PersonService {

    void setUsername(Long id, String newUsername);
    String getUsername(Long id);
    List<Person> getPerson();
    Person getUserById(long id);
}
