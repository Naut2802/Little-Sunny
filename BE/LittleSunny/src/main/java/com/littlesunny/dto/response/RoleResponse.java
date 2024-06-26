package com.littlesunny.dto.response;

import com.littlesunny.entity.Permission;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponse {
	String roleName;
	String description;
	Set<Permission> permissions;
}
