package ru.martynov.MyTaskMaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.martynov.MyTaskMaster.models.Person;


public interface PersonRepository extends JpaRepository<Person, Long> {
}
