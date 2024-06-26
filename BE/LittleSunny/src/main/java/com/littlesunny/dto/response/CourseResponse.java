package com.littlesunny.dto.response;

import com.littlesunny.entity.Class;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseResponse {
	long courseId;
	String courseName;
	double coursePrice;
	List<ClassResponseForCourse> classes;
}
