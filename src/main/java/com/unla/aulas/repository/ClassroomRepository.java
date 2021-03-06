package com.unla.aulas.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.unla.aulas.entity.ClassroomEntity;
import com.unla.aulas.entity.ReservationEntity;

@Repository
public interface ClassroomRepository extends JpaRepository<ClassroomEntity, Integer>{
   public ClassroomEntity findById(int id);
   public ClassroomEntity findByNumber(int number);
   public List<ReservationEntity> findByReservations(int id);
}
