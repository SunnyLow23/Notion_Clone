package com.sunnylow.notion_clone.service;

import com.sunnylow.notion_clone.dto.UserDTO;

import java.util.List;

public interface UserService {

	UserDTO save(UserDTO userDTO);

	UserDTO update(Integer id, UserDTO userDTO);

	List<UserDTO> getAll();

	UserDTO getById(Integer id);

	void delete(Integer id);
}
