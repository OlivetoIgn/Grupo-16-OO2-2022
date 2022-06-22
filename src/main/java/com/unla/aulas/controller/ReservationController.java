package com.unla.aulas.controller;

import java.util.ArrayList;
import java.util.Optional;

import com.unla.aulas.entity.ClassroomEntity;
import com.unla.aulas.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unla.aulas.dto.ReservationDto;
import com.unla.aulas.entity.ReservationEntity;
import com.unla.aulas.service.ReservationService;

@RestController
@RequestMapping("/reservation/")
public class ReservationController {
	@Autowired
	ReservationService reservationService;

	@Autowired
	ClassroomService classroomService;
	
	@GetMapping()
	public ArrayList<ReservationEntity> getAll() {
		return reservationService.getAllReservations();
	}
	
	@PostMapping()
	public ReservationEntity insertOrUpdate(@RequestBody ReservationEntity reservationDto) {
		return reservationService.insertOrUpdateReservation(reservationDto);
	}
	
	@GetMapping(path = "{id}")
	public Optional<ReservationEntity> getById(@PathVariable("id") int id) {
		return reservationService.getReservationById(id);
	}
	
	@DeleteMapping(path ="{id}")
	public String delete(@PathVariable("id") int id) {
		String message;
		boolean response = reservationService.deleteReservation(id);
		if (response)
			message = "Se ha eliminado la reservacion";
		else
			message = "No existe el id " + id;
		return message;
	}

	@GetMapping("reservationbyclass")
	public ArrayList<ReservationEntity> getClassReservations(@RequestBody ReservationEntity reservationEntity) {
		ClassroomEntity classroomEntity = classroomService.getClassroomEntityById(reservationEntity.getClassroomEntity().getId()).get();
		if(classroomEntity != null){
			return reservationService.getReservationByClassroom(classroomEntity);
		}
		return null;
	}
}
