package ru.martynov.MyTaskMaster.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.martynov.MyTaskMaster.models.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
