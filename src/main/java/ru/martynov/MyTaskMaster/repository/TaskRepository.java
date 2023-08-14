package ru.martynov.MyTaskMaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.martynov.MyTaskMaster.models.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "SELECT b from Task b WHERE b.isDone=false or (b.isDone=true and b.createDt > current_date) ORDER BY b.importanceLevel DESC, b.createDt")
    List<Task> searchTasks();
}
