package com.unla.aulas.repository;

import com.unla.aulas.entity.ShiftEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.aulas.entity.ReservationEntity;

import java.time.LocalDate;
import java.util.ArrayList;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer>{

    public abstract  ArrayList<ReservationEntity> findByReservationDate(LocalDate reservationDate);

    public abstract ArrayList<ReservationEntity> findByShiftEntity(ShiftEntity shiftEntity);
}
