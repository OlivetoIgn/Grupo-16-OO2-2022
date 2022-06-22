package com.unla.aulas.service;

import java.util.ArrayList;
import java.util.Optional;

import com.unla.aulas.dto.ClassroomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.aulas.entity.ClassroomEntity;
import com.unla.aulas.repository.ClassroomRepository;

@Service
public class ClassroomService  {
    @Autowired
    ClassroomRepository classroomRepository;


    public ArrayList<ClassroomEntity> getAllClassrooms() {
        return (ArrayList<ClassroomEntity>) classroomRepository.findAll();
    }


    public ClassroomEntity insertOrUpdateClassroom(ClassroomEntity classroomEntity) {
        return classroomRepository.save(classroomEntity);
    }

    public Optional<ClassroomEntity> getClassroomEntityById(int id) {
        return classroomRepository.findById(id);
    }

    public ClassroomEntity getClassroomEntityByNumber(int number) {
        return classroomRepository.findByNumber(number);
    }

    public Optional<ClassroomEntity> getClassroom(int id){
        return classroomRepository.findById(id);
    }

    public ClassroomDto getClassroomDtoById(int id) {
        ClassroomDto classroomDto = new ClassroomDto();
        Optional<ClassroomEntity> classroomEntity = classroomRepository.findById(id);
        if(classroomEntity==null){
            return null;
        }
        classroomDto.setBlackboard(classroomEntity.get().isBlackboard());
        classroomDto.setNumber(classroomEntity.get().getNumber());
        classroomDto.setQuantityOfPC(classroomEntity.get().getQuantityOfPC());
        classroomDto.setQuantityOfChairs(classroomEntity.get().getQuantityOfChairs());
        classroomDto.setId(classroomEntity.get().getId());
        return classroomDto;
    }


    public boolean deleteClassroom(int id) {
        try {
            classroomRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //public ClassroomEntity getClassroomEntityByNumber(int number) {
    //    return classroomRepository.findByNumber(number);
    //}

    /*public ArrayList<ReservationDto> getClassroomReservations(int classroomId)
    {
        ArrayList<ReservationDto> lstReservations = new ArrayList<>();
        ClassroomEntity classroomEntity = getClassroomEntityById(classroomId);
        for (ReservationEntity reservation: classroomEntity.getReservations()) {
            ReservationDto reservationDto = new ReservationDto();
            reservationDto.setDateFrom(reservation.getDateFrom());
            reservationDto.setDateTo(reservation.getDateTo());
            reservationDto.setTaken(reservation.isTaken());
            reservationDto.setTurn(reservation.getTurn());
            reservationDto.setId(reservation.getId());
            lstReservations.add(reservationDto);
        }
        return lstReservations;
    }*/
}
