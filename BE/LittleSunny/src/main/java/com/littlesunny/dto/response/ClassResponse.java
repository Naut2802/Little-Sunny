package com.littlesunny.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClassResponse {
	long classId;
	String className;
	String courseName;
	int limitQuantity;
	List<StudentClassResponse> students;
}
