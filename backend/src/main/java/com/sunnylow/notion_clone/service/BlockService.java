package com.sunnylow.notion_clone.service;

import com.sunnylow.notion_clone.dto.BlockDTO;

import java.util.List;

public interface BlockService<T extends BlockDTO> {

	T save(T dto);

	T update(Integer id, T dto);

	T updatePosition(Integer id, Integer newPosition);

	List<T> getAll();

	T getById(Integer id);

	List<T> getAllByPageId(Integer pageId);

	void delete(Integer id);
}
