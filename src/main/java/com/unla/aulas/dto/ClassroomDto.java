package com.unla.aulas.dto;
import java.util.List;

import com.unla.aulas.entity.ClassroomTypeEntity;
import com.unla.aulas.entity.ReservationEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomDto {
	private int id;
	private int number;
	private int quantityOfChairs;
	private int quantityOfPC;
	private boolean blackboard;
    private ClassroomTypeEntity typeSolicitude;
	public ClassroomDto(int id) {
		this.id = id;
	}
}
