package com.sunnylow.notion_clone.service;

import com.sunnylow.notion_clone.dto.BlockDTO;

import java.util.List;
import java.util.Optional;

public interface BlockService<T extends BlockDTO> {

	T save(T dto);

	T update(Integer id, T dto);

	List<T> getAll();

	T getById(Integer id);

	void delete(Integer id);
}
