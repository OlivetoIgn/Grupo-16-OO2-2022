package com.unla.aulas.dto;

import com.unla.aulas.entity.ShiftEntity;
import com.unla.aulas.entity.SolicitudeTypeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudeDto {
    private int id;
    private LocalDate solicitudeDate;
    private int studentsQuantity;
    private SubjectDto subjectDto;
    private String observations;
    private int solicitudeCode;
    @Enumerated(value = EnumType.STRING)
    private SolicitudeTypeEntity typeSolicitude;
    @Enumerated(value = EnumType.STRING)
    private ShiftEntity shiftDto;
    private LocalDate startDate;
    private LocalDate endDate;
    private ClassroomDto classroomDto;

}
