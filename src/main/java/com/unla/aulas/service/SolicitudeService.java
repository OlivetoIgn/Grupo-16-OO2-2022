package com.unla.aulas.service;

import com.unla.aulas.dto.*;
import com.unla.aulas.entity.ReservationEntity;
import com.unla.aulas.entity.SolicitudeEntity;
import com.unla.aulas.repository.SolicitudeRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitudeService {

    @Autowired
    SolicitudeRepository solicitudeRepository;
    @Autowired
    SubjectService subjectService;
    @Autowired
    ClassroomService classroomService;

    @Autowired
    ReservationService reservationService;

    public ArrayList<SolicitudeEntity> getSolicitudesEntity(){
        return (ArrayList<SolicitudeEntity>) solicitudeRepository.findAll();
    }

    public ArrayList<SolicitudeEntity> getSolicitudes(){
        List<SolicitudeEntity> lstSolicitudEntities = solicitudeRepository.findAll();
        return (ArrayList<SolicitudeEntity>) solicitudeRepository.findAll();
    }

    public Optional<SolicitudeEntity> getSolicitudeById(int id){
        return solicitudeRepository.findById(id);
    }

    public ArrayList<SolicitudeEntity> getSolicitudeBySolicitudDate(LocalDate solicitudeDate){
        return (ArrayList<SolicitudeEntity>)solicitudeRepository.findSolicitudeBySolicitudeDate(solicitudeDate);
    }

    public SolicitudeEntity insertorupdate(SolicitudeEntity solicitudeEntity) {
        solicitudeEntity.setSolicitudeDate(Funciones.traerFecha());
        if (solicitudeEntity.getSubjectEntity() != null){
            if(solicitudeEntity.getSubjectEntity().getId() > 0) {
            solicitudeEntity.setSubjectEntity(subjectService.getSubjectEntityById(solicitudeEntity.getSubjectEntity().getId()).get());
        } else if (solicitudeEntity.getSubjectEntity().getSubjectCode() > 0) {
            solicitudeEntity.setSubjectEntity(subjectService.getSubjectBySubjectCode(solicitudeEntity.getSubjectEntity().getSubjectCode()));
        }
        }
        if (solicitudeEntity.getClassroomEntity() != null) {
            if(solicitudeEntity.getClassroomEntity().getId() > 0){
            solicitudeEntity.setClassroomEntity(classroomService.getClassroomEntityById(solicitudeEntity.getClassroomEntity().getId()).get());}
        }
        ArrayList<ReservationEntity> lstReservations = reservationService.getReservationByDate(solicitudeEntity.getStartDate());
        for (ReservationEntity reserved: lstReservations) {
            if(reserved.isTaken() && reserved.getClassroomEntity().equals(solicitudeEntity.getClassroomEntity()) && reserved.getShiftEntity().equals(solicitudeEntity.getShiftEntity())){
                return null;
            }
            if(!reserved.isTaken() && reserved.getClassroomEntity().equals(solicitudeEntity.getClassroomEntity()) && reserved.getShiftEntity().equals(solicitudeEntity.getShiftEntity())){
                reservationService.deleteReservation(reserved.getId());
            }
        }
        ArrayList<LocalDate> lstDaysToReserve = new ArrayList<>();
        LocalDate aux = solicitudeEntity.getStartDate();
        if(aux.equals(solicitudeEntity.getEndDate())){
            lstDaysToReserve.add(aux);
        }
        while (!aux.isEqual(solicitudeEntity.getEndDate()))
        {
            lstDaysToReserve.add(aux);
            aux = Funciones.traerFechaProximo(aux, 7);
        }
        for(LocalDate date:lstDaysToReserve) {
            if(Funciones.esDiaHabil(date)){
                ReservationEntity reservationEntity = new ReservationEntity();
                reservationEntity.setReservationDate(date);
                reservationEntity.setShiftEntity(solicitudeEntity.getShiftEntity());
                reservationEntity.setTaken(true);
                reservationEntity.setClassroomEntity(solicitudeEntity.getClassroomEntity());
                reservationService.insertOrUpdateReservation(reservationEntity);
            }
        }
        return solicitudeRepository.save(solicitudeEntity);
    }

    public boolean deleteSolicitude(int id){
        try{
            solicitudeRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
