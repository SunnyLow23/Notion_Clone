package com.sunnylow.notion_clone.service.impl;

import com.sunnylow.notion_clone.dto.UserDTO;
import com.sunnylow.notion_clone.model.User;
import com.sunnylow.notion_clone.repository.UserRepository;
import com.sunnylow.notion_clone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl  implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDTO save(UserDTO dto) {
		User user = UserDTO.toUser(dto);

		PasswordEncoder encoder = new BCryptPasswordEncoder(10);
		user.setPassword(encoder.encode(dto.getPassword()));

		return UserDTO.toUserDTO(userRepository.save(user));
	}

	@Override
	public UserDTO update(Integer id, UserDTO dto) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found"));

		user.setUsername(dto.getUsername());
		user.setEmail(dto.getEmail());

		return UserDTO.toUserDTO(userRepository.save(user));
	}

	@Override
	public List<UserDTO> getAll() {
		return userRepository.findAll().stream()
				.map(UserDTO::toUserDTO).collect(Collectors.toList());
	}

	@Override
	public UserDTO getById(Integer id) {
		return userRepository.findById(id)
				.map(UserDTO::toUserDTO)
				.orElseThrow(() -> new RuntimeException("User not found"));
	}

	@Override
	public void delete(Integer id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found"));

		userRepository.delete(user);
	}
}
