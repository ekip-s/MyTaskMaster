package ru.martynov.MyTaskMaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.martynov.MyTaskMaster.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
