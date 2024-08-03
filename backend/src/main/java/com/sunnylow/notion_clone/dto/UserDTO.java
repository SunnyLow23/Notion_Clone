package com.sunnylow.notion_clone.dto;

import com.sunnylow.notion_clone.model.User;
import com.sunnylow.notion_clone.model.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

	private Integer id;
	private String username;
	private String password;
	private String email;
	private UserRole role;

	public static User toUser(UserDTO dto) {
		final User user = new User();

		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setEmail(dto.getEmail());
		user.setRole(dto.getRole());

		return user;
	}

	public static UserDTO toUserDTO(User user) {
		return UserDTO.builder()
				.id(user.getId())
				.username(user.getUsername())
				.password(user.getPassword())
				.email(user.getEmail())
				.role(user.getRole())
				.build();
	}
}
