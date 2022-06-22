package com.unla.aulas.dto;

import com.unla.aulas.entity.CarrerEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {

    private int id;
    private int subjectCode;
    private String subject;
    private CarrerDto carrer;

    public SubjectDto(int id) {
        this.id = id;
    }
}
