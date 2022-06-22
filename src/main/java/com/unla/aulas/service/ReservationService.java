package com.unla.aulas.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.unla.aulas.dto.ClassroomDto;
import com.unla.aulas.entity.ClassroomEntity;
import com.unla.aulas.entity.ShiftEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.unla.aulas.dto.ReservationDto;
import com.unla.aulas.entity.ReservationEntity;
import com.unla.aulas.repository.ReservationRepository;

@Service
public class ReservationService  {
	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	ClassroomService classroomService;

	public ArrayList<ReservationEntity> getAllReservations() {
		return (ArrayList<ReservationEntity>) reservationRepository.findAll();
	}


	/*public ReservationDto insertOrUpdateReservation(ReservationDto reservationDto) {
		ReservationEntity reservationEntity = new ReservationEntity();
		reservationEntity.setReservationDate(reservationDto.getReservationDate());
		reservationEntity.setShiftEntity(reservationDto.getShiftDto());
		reservationEntity.setTaken(reservationDto.isTaken());
		reservationEntity.setClassroomEntity(classroomService.getClassroomById(reservationDto.getClassroomDto().getId()));

		ArrayList<ReservationEntity> lstReserves = reservationRepository.findByReservationDate(reservationDto.getReservationDate());
		if(lstReserves.isEmpty()){
			reservationRepository.save(reservationEntity);
		}
		for (ReservationEntity reservEnti:lstReserves) {
			if (!reservEnti.isTaken() && !reservEnti.getShiftEntity().equals(reservationDto.getShiftDto())) {
				deleteReservation(reservEnti.getId());
				reservationRepository.save(reservationEntity);
			}
		}
		return reservationDto;
	}*/

	public ReservationEntity insertOrUpdateReservation(ReservationEntity reservationEntity) {
		/*ArrayList<ReservationEntity> lstReserves = reservationRepository.findByReservationDate(reservationEntity.getReservationDate());
		if(lstReserves.isEmpty()){
			reservationRepository.save(reservationEntity);
		}
		for (ReservationEntity reservEnti:lstReserves) {
			if (!reservEnti.isTaken() && !reservEnti.getShiftEntity().equals(reservationEntity.getShiftEntity())) {
				deleteReservation(reservEnti.getId());
				reservationRepository.save(reservationEntity);
			}
		}*/
		return reservationRepository.save(reservationEntity);
	}


	public Optional<ReservationEntity> getReservationById(int id) {
		return reservationRepository.findById(id);
	}

	public ReservationDto getReservationDtoById(int id) {
		Optional<ReservationEntity> reservationEntity = reservationRepository.findById(id);
		if(reservationEntity!=null){
			ReservationDto reservationDto = new ReservationDto();
			reservationDto.setId(reservationEntity.get().getId());
			reservationDto.setReservationDate(reservationEntity.get().getReservationDate());
			reservationDto.setShiftDto(reservationEntity.get().getShiftEntity());
			reservationDto.setTaken(reservationEntity.get().isTaken());
			reservationDto.setClassroomDto(classroomService.getClassroomDtoById(reservationEntity.get().getClassroomEntity().getId()));
			return reservationDto;
		}
		return null;
	}
	public ArrayList<ReservationEntity> getReservationByDate(LocalDate date){
		return reservationRepository.findByReservationDate(date);
	}

	public ArrayList<ReservationEntity> getReservationByShift(ShiftEntity shift){
		return reservationRepository.findByShiftEntity(shift);
	}
	public boolean deleteReservation(int id) {
		try {
			reservationRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<ReservationEntity> getReservations(List<ReservationEntity> list) {
		return (List<ReservationEntity>) getAllReservations();
	}

	public ArrayList<ReservationEntity> getReservationByClassroom(ClassroomEntity classroomEntity){
		ArrayList<ReservationEntity> lstReservations = getAllReservations();
		ArrayList<ReservationEntity> lstReservationsFiltered = new ArrayList<>();
		for (ReservationEntity reserved: lstReservations) {
			if(reserved.getClassroomEntity()!=null && reserved.getClassroomEntity().getId() == (classroomEntity.getId())){
				lstReservationsFiltered.add(reserved);
			}
		}
		return lstReservationsFiltered;
	}

}
