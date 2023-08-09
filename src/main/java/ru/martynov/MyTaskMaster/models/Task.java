package ru.martynov.MyTaskMaster.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Tasks")
public class Task {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description")
    private String content;
    @Column(name = "category")
    @Enumerated(EnumType.ORDINAL)
    private TaskCategory category;
    @Column(name = "is_done")
    private boolean isDone;
    @Column(name = "created_at")
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime createDt;
    @Column(name = "importance_level")
    private int importanceLevel;
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;


    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", category=" + category +
                ", isDone=" + isDone +
                ", create_dt=" + createDt +
                ", importanceLevel=" + importanceLevel +
                ", person=" + person +
                '}';
    }
}
