package com.unla.aulas.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unla.aulas.entity.ClassroomEntity;
import com.unla.aulas.service.ClassroomService;

@RestController
@RequestMapping("/classroom/")
public class ClassroomController {
	@Autowired
	ClassroomService classroomService;

	@GetMapping()
	public ArrayList<ClassroomEntity> getAll() {
		return classroomService.getAllClassrooms();
	}

	@PostMapping()
	public ClassroomEntity insertOrUpdate(@RequestBody ClassroomEntity classroomEntity) {
		return classroomService.insertOrUpdateClassroom(classroomEntity);
	}

	@GetMapping(path = "{id}")
	public Optional<ClassroomEntity> getById(@PathVariable("id") int id) {
		return classroomService.getClassroom(id);
	}

	@DeleteMapping(path = "{id}")
	public String delete(@PathVariable("id") int id) {
		String message;
		boolean response = classroomService.deleteClassroom(id);
		if (response)
			message = "Se ha eliminado el aula";
		else
			message = "No existe el id " + id;
		return message;
	}

}
