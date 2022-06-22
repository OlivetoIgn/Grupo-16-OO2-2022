package com.unla.aulas.entity;

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
@Entity
@Table(name = "solicitude")
public class SolicitudeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "solicitude_date")
    private LocalDate solicitudeDate;

    @Column(name = "students_quantity")
    private int studentsQuantity;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
    private SubjectEntity subjectEntity;

    @Column(name = "observations")
    private String observations;

    @Column(name = "solicitude_code")
    private int solicitudeCode;

    @Column(name = "solicitude_type")
    @Enumerated(value = EnumType.STRING)
    private SolicitudeTypeEntity typeSolicitude;

    @Column(name = "shift_type")
    @Enumerated(value = EnumType.STRING)
    private ShiftEntity shiftEntity;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "classroom_id")
    private ClassroomEntity classroomEntity;

}
