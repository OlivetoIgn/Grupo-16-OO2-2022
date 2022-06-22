package com.unla.aulas.dto;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


import com.unla.aulas.entity.ShiftEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
	private int id;
	private LocalDate reservationDate;
	@Enumerated(value = EnumType.STRING)
	private ShiftEntity shiftDto;
	private boolean isTaken;
	private ClassroomDto classroomDto;
}
