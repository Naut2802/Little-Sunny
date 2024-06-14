package com.littlesunny.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentClass {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	@ManyToOne @JoinColumn(name = "student_id")
	Student student;
	@ManyToOne @JoinColumn(name = "class_id")
	Class Clazz;
	
	float score;
}