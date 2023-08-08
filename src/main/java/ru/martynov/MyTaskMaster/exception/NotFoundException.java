package ru.martynov.MyTaskMaster.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class NotFoundException extends RuntimeException {
    private final LocalDateTime timestamp;


    public NotFoundException(String message) {
        super(message);
        this.timestamp = LocalDateTime.now();
    }
}
