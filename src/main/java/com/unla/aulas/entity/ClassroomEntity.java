package com.unla.aulas.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "classroom")
public class ClassroomEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "number")
	private int number;

	@Column(name = "quantity_of_chairs")
	private int quantityOfChairs;
	
	@Column(name = "quantity_of_pc")
	private int quantityOfPC;
	
	@Column(name="blackboard")
	private boolean isBlackboard;
	
	@Column(name = "classroom_type")
    @Enumerated(value = EnumType.STRING)
    private ClassroomTypeEntity typeClassroom;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "building_id")
	private BuildingEntity buildingEntity;
	
}
